package com.uleska.uleska.service.impl;

import com.uleska.uleska.model.job.BaseJob;
import com.uleska.uleska.model.job.JobRequest;
import com.uleska.uleska.model.job.JobStatusEnum;
import com.uleska.uleska.service.JobFactory;
import com.uleska.uleska.service.JobService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.uleska.uleska.model.job.JobStatusEnum.RUNNING;

@Service
public class JobServiceImpl implements JobService {

    Thread scheduler;
    private List<BaseJob> jobs = new ArrayList<>();

    @Override
    public BaseJob scheduleJob(JobRequest jobRequest) {
        BaseJob job = JobFactory.createJob(
                jobRequest.getScheduleMills(),
                jobRequest.getType(),
                jobRequest.getCommand(),
                jobRequest.getUserId());
        synchronized (jobs) {
            jobs.add(job);
            Collections.sort(jobs);
            jobs.notify();
        }
        return job;
    }

    @Override
    public void resumeJob(String jobId) {
        changeJobStatus(RUNNING, jobId);
    }

    @Override
    public void stopJob(String jobId) {
        changeJobStatus(JobStatusEnum.STOP, jobId);
    }

    @Override
    public void deleteJob(String jobId) {
        synchronized (jobs) {
            jobs.removeIf(job -> job.getJobId().equals(jobId));
        }
    }

    @Override
    public BaseJob getJob(String jobId) {
        for (BaseJob job : jobs) {
            if (job.equals(jobId)){
                return job;
            }
        }
        return null;
    }

    private void changeJobStatus(JobStatusEnum statusEnum, String jobId) {
        synchronized (jobs) {
            for (BaseJob job : jobs) {
                if (job.getJobId().equals(jobId)) {
                    job.setStatus(statusEnum);
                    break;
                }
            }
            Collections.sort(jobs);
        }
    }

    @PostConstruct
    void startScheduler() {
        scheduler = new MyScheduler();
        scheduler.start();
    }

    private class MyScheduler extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (jobs) {
                        //Nothing to do, Idling and waiting for a job to come
                        if (jobs.isEmpty()) {
                            jobs.wait();
                        }

                        //We have a new candidate for an execution.
                        // Code will wait for a right amount of time to wake up just before execution
                        BaseJob baseJob = jobs.get(0);
                        long timeToSleep = baseJob.getNextTimeExecution().getTime() - System.currentTimeMillis();

                        while (timeToSleep > 0) {
                            jobs.wait(timeToSleep);
                            // While we were sleeping a new job that has to be executed earlier may come.
                            // Need to adjust sleeping time accordingly.
                            baseJob = jobs.get(0);
                            timeToSleep = baseJob.getNextTimeExecution().getTime() - System.currentTimeMillis();
                        }

                        //Omitting job if it's not in running state
                        if (baseJob.getStatus() == RUNNING) {
                            baseJob.execute();
                        }
                        //Scheduling next time for the job to be executed
                        baseJob.resetNextTimeExecution();
                        Collections.sort(jobs);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println();
            }
        }
    }

}

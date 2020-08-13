package com.uleska.uleska.service;

import com.uleska.uleska.model.job.BaseJob;
import com.uleska.uleska.model.job.JobRequest;

public interface JobService {
    BaseJob scheduleJob(JobRequest jobRequest);

    void resumeJob(String jobId);

    void stopJob(String jobId);

    void deleteJob(String jobId);

    BaseJob getJob(String jobId);
}

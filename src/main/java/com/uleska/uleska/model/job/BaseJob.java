package com.uleska.uleska.model.job;

import java.util.Date;
import java.util.UUID;

public abstract class BaseJob implements Comparable{

    protected String source;
    private String userId;
    private String jobId;
    private long scheduleMills;
    private Date nextTimeExecution;
    private JobStatusEnum status;

    @Override
    public int compareTo(Object o) {
        BaseJob target = (BaseJob) o;
        return nextTimeExecution.compareTo(target.nextTimeExecution);
    }

    public BaseJob() {
    }

    public abstract void execute();

    public BaseJob(long scheduleMills, String source, String userId) {
        this.source = source;
        this.userId = userId;
        this.scheduleMills = scheduleMills;
        this.jobId = UUID.randomUUID().toString();

        nextTimeExecution = new Date(System.currentTimeMillis() + scheduleMills);
        status = JobStatusEnum.RUNNING;

    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public JobStatusEnum getStatus() {
        return status;
    }

    public void setStatus(JobStatusEnum status) {
        this.status = status;
    }

    public long getScheduleMills() {
        return scheduleMills;
    }

    public void setScheduleMills(long scheduleMills) {
        this.scheduleMills = scheduleMills;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getNextTimeExecution() {
        return nextTimeExecution;
    }

    public void setNextTimeExecution(Date nextTimeExecution) {
        this.nextTimeExecution = nextTimeExecution;
    }

    public void resetNextTimeExecution(){
        this.setNextTimeExecution(new Date(System.currentTimeMillis() + this.getScheduleMills()));
    }


}

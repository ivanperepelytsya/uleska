package com.uleska.uleska.model.job;

public class JobRequest {
    private JobTypeEnum type;
    private String command;
    private long scheduleMills;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getScheduleMills() {
        return scheduleMills;
    }

    public void setScheduleMills(long scheduleMills) {
        this.scheduleMills = scheduleMills;
    }

    public JobTypeEnum getType() {
        return type;
    }

    public void setType(JobTypeEnum type) {
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}

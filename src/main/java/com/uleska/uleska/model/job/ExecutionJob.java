package com.uleska.uleska.model.job;

import java.util.Date;

public class ExecutionJob extends BaseJob{


    public ExecutionJob(long scheduledMills, String source, String userId) {
        super(scheduledMills, source, userId);
    }

    public ExecutionJob() {
    }

    @Override
    public void execute() {
        System.out.println(new Date());
    }

}

package com.uleska.uleska.model.job;

public class PrintJob extends BaseJob{
    @Override
    public void execute() {
        System.out.println(getSource());
    }

    public PrintJob(long scheduledMills, String source, String userId) {
        super(scheduledMills, source, userId);
    }
}

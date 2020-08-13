package com.uleska.uleska.model.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemCallJob extends BaseJob{
    @Override
    public void execute() {
        String systemCall = executeSystemCall(getSource());
        System.out.println(systemCall);
    }

    public SystemCallJob() {
    }

    public SystemCallJob(long scheduledMills, String source, String userId) {
        super(scheduledMills, source, userId);
    }

    private String executeSystemCall(String code){
        Runtime r = Runtime.getRuntime();
        Process p;
        StringBuilder result = new StringBuilder();
        try {
            p = r.exec(code);
            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = b.readLine()) != null) {
                result.append(line);
            }
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}

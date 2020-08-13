package com.uleska.uleska.service;

import com.uleska.uleska.model.exception.UException;
import com.uleska.uleska.model.job.*;

public class JobFactory {

    public static BaseJob createJob(long scheduleMills, JobTypeEnum jobTypeEnum, String code, String userId){
        switch (jobTypeEnum){
            case PRINT:
                return new PrintJob(scheduleMills, code, userId);
            case SYSTEM:
                return new SystemCallJob(scheduleMills, code, userId);
            case EXECUTION:
                return new ExecutionJob(scheduleMills, code, userId);
        }
        throw new UException("Job Type Not Found");
    }
}

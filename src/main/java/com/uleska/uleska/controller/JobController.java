package com.uleska.uleska.controller;

import com.uleska.uleska.model.job.BaseJob;
import com.uleska.uleska.model.job.JobRequest;
import com.uleska.uleska.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public BaseJob scheduleJob(@RequestBody JobRequest jobRequest) {
        return jobService.scheduleJob(jobRequest);
    }

    @PutMapping("/stop/{jobId}")
    public void stopJob(@PathVariable String jobId) {
        jobService.stopJob(jobId);
    }

    @PutMapping("/resume/{jobId}")
    public void resumeJob(@PathVariable String jobId) {
        jobService.resumeJob(jobId);
    }

    @DeleteMapping("/{jobId}")
    public void deleteJob(@PathVariable String jobId) {
        jobService.deleteJob(jobId);
    }

    @GetMapping("/{jobId}")
    public BaseJob getJob(@PathVariable String jobId) {
        return jobService.getJob(jobId);
    }

}

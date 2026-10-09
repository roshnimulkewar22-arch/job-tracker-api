package com.jobtracker.job_tracker_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobtracker.job_tracker_api.exception.ResourceNotFoundException;
import com.jobtracker.job_tracker_api.model.JobApplication;
import com.jobtracker.job_tracker_api.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping
    public List<JobApplication> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobApplication getJobById(@PathVariable Long id) {
        return service.getJobById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found with id " + id));
    }

    @PostMapping
    public JobApplication createJob(@RequestBody JobApplication job) {
        return service.createJob(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateJob(@PathVariable Long id, @RequestBody JobApplication job) {
        JobApplication updated = service.updateJob(id, job);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        service.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<JobApplication> getJobsByStatus(@PathVariable String status) {
        return service.getJobsByStatus(status);
    }

    @GetMapping("/search")
    public List<JobApplication> searchJobsByCompany(@RequestParam(value = "company", required = false) String company) {
        if (company == null || company.trim().isEmpty()) {
            return service.getAllJobs();
        }
        return service.searchByCompany(company);
    }
}

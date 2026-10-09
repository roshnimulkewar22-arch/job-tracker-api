package com.jobtracker.job_tracker_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobtracker.job_tracker_api.model.JobApplication;
import com.jobtracker.job_tracker_api.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public List<JobApplication> getAllJobs() {
        return repository.findAll();
    }

    public Optional<JobApplication> getJobById(Long id) {
        return repository.findById(id);
    }

    public JobApplication createJob(JobApplication job) {
        return repository.save(job);
    }

    public JobApplication updateJob(Long id, JobApplication updatedJob) {
        return repository.findById(id).map(job -> {
            job.setCompanyName(updatedJob.getCompanyName());
            job.setJobTitle(updatedJob.getJobTitle());
            job.setStatus(updatedJob.getStatus());
            job.setAppliedDate(updatedJob.getAppliedDate());
            job.setJobDescription(updatedJob.getJobDescription());
            job.setMatchScore(updatedJob.getMatchScore());
            return repository.save(job);
        }).orElseThrow(() -> new RuntimeException("Job not found with id " + id));
    }

    public void deleteJob(Long id) {
        repository.deleteById(id);
    }

    public List<JobApplication> getJobsByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<JobApplication> searchByCompany(String companyName) {
        return repository.findByCompanyNameContainingIgnoreCase(companyName);
    }
}

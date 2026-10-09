package com.jobtracker.job_tracker_api.repository;

import java.util.List;
import com.jobtracker.job_tracker_api.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStatus(String status);
    List<JobApplication> findByCompanyNameContainingIgnoreCase(String companyName);
}

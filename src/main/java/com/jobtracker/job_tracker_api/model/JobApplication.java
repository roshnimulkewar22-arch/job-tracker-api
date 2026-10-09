package com.jobtracker.job_tracker_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String jobTitle;

    private String status; // APPLIED, INTERVIEWING, OFFER, REJECTED

    private LocalDate appliedDate;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;

    private Integer matchScore;
}
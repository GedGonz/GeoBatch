package com.geo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GeoBatchApplication  implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job kmzProcessingJob;

    public GeoBatchApplication(JobLauncher jobLauncher, Job kmzProcessingJob) {
        this.jobLauncher = jobLauncher;
        this.kmzProcessingJob = kmzProcessingJob;
    }

    public static void main(String[] args) {
        SpringApplication.run(GeoBatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new java.util.Date())
                .toJobParameters();

        jobLauncher.run(kmzProcessingJob, jobParameters);

    }
}
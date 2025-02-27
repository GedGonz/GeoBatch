package com.geo.batch.config;

import com.geo.batch.model.Apoyos;
import com.geo.batch.persistence.entity.ApoyosEnity;
import com.geo.batch.steps.KmzExtractTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    public Job kmzProcessingJob(JobRepository jobRepository,Step extractKmzStep, Step processKmzStep) {
        return new JobBuilder("kmzProcessingJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(extractKmzStep)
                .next(processKmzStep)
                .build();
    }

    @Bean
    public Step extractKmzStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, KmzExtractTasklet kmzExtractTasklet) {
        return new StepBuilder("extractKmzStep", jobRepository)
                .tasklet(kmzExtractTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step processKmzStep(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager,
                          ItemReader<Apoyos> kmzFileItemReader,
                          ItemProcessor<Apoyos, ApoyosEnity> kmzFileItemProcessor,
                          ItemWriter<ApoyosEnity> kmzFileItemWriter) {
        return new StepBuilder("processKmzStep", jobRepository)

                .<Apoyos, ApoyosEnity>chunk(5, transactionManager)
                .reader(kmzFileItemReader)
                .processor(kmzFileItemProcessor)
                .writer(kmzFileItemWriter)
                .build();
    }
}
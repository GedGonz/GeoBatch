package com.geo.batch.steps;

import com.geo.batch.persistence.entity.ApoyosEnity;
import com.geo.batch.persistence.repository.ApoyosRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


@Log4j2
@Component
public class KmzFileItemWriter implements ItemWriter<ApoyosEnity> {


    private final ApoyosRepository repository;

    public KmzFileItemWriter(ApoyosRepository repository) {
        this.repository = repository;
    }

    @Override
    public void write(Chunk<? extends ApoyosEnity> apoyos) throws Exception {
        repository.saveAll(apoyos.getItems());
        log.info("se han escrito {} registros", apoyos.getItems().size());
    }
}

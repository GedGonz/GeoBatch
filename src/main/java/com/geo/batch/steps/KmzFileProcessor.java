package com.geo.batch.steps;

import com.geo.batch.mapers.ApoyosMapper;
import com.geo.batch.model.Apoyos;
import com.geo.batch.persistence.entity.ApoyosEnity;
import lombok.extern.log4j.Log4j2;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KmzFileProcessor implements ItemProcessor<Apoyos, ApoyosEnity> {

    private final ApoyosMapper apoyosMapper;
    private final GeometryFactory geometryFactory;
    public KmzFileProcessor(ApoyosMapper apoyosMapper, GeometryFactory geometryFactory) {
        this.apoyosMapper = apoyosMapper;
        this.geometryFactory = geometryFactory;
    }

    @Override
    public ApoyosEnity process(Apoyos apoyo) {
        log.info("se han procesado el apoyo {}", apoyo.getName());
        return apoyosMapper.apoyosToApoyosEnity(apoyo,geometryFactory);// transforma el objeto Apoyos a ApoyosEnity
    }
}

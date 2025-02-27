package com.geo.batch.mapers;

import com.geo.batch.model.Apoyos;
import com.geo.batch.persistence.entity.ApoyosEnity;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ApoyosMapper {


    @Mapping(target = "coordinates", source = "coordinates", qualifiedByName = "stringArrayToPoint")
    ApoyosEnity apoyosToApoyosEnity(Apoyos apoyos, @Context GeometryFactory geometryFactory);

    @Named("stringArrayToPoint")
    static Point stringArrayToPoint(String[] coordinates, @Context GeometryFactory geometryFactory ) {
        if (coordinates.length < 1) {
            throw new IllegalArgumentException("Campo de coordenadas vacío.");
        }
        String[] coordinate = coordinates[0].split(",");
        if (coordinate.length < 2) {
            throw new IllegalArgumentException("Se requieren al menos dos coordenadas (latitud y longitud).");
        }
        double longitude = Double.parseDouble(coordinate[0]);
        double latitude = Double.parseDouble(coordinate[1]);
        return geometryFactory.createPoint(new Coordinate(longitude,latitude));
    }
}
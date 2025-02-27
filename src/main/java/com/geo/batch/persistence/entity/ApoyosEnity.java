package com.geo.batch.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "apoyos")
@Data
public class ApoyosEnity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "geography(Point, 4326)")
    private Point coordinates;
}

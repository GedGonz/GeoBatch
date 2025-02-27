package com.geo.batch.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Apoyos {

    private Long id;
    private String name;
    private String[] coordinates;
}

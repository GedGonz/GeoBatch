package com.geo.batch.persistence.repository;

import com.geo.batch.persistence.entity.ApoyosEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApoyosRepository extends JpaRepository<ApoyosEnity, Long> {
}

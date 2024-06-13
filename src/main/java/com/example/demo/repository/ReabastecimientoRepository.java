package com.example.demo.repository;

import com.example.demo.model.Reabastecimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReabastecimientoRepository extends CrudRepository<Reabastecimiento,Long> {
}

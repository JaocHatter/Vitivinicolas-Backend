package com.example.demo.repository;

import com.example.demo.model.Reporte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends CrudRepository<Reporte, Long>{
}

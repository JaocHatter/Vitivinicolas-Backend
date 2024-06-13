package com.example.demo.repository;

import com.example.demo.model.AdministradorEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
//implementaremos algunas operaciones CRUD que java ya tiene implementada
public interface AdministradorEmpresaRepository extends CrudRepository<AdministradorEmpresa, Long> {

}

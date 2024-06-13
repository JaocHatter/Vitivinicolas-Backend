package com.example.demo.repository;

import com.example.demo.model.TrabajadorAlmacen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorAlmacenRepository extends CrudRepository<TrabajadorAlmacen, Long> {

}

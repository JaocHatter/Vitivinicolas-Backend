package com.example.demo.service;

import com.example.demo.model.TrabajadorAlmacen;
import com.example.demo.repository.TrabajadorAlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorAlmacenService {
    @Autowired
    TrabajadorAlmacenRepository trabajadorAlmacenRepository;

    public List<TrabajadorAlmacen> getTrabajadorAlmacenes() {
        return (List<TrabajadorAlmacen>) trabajadorAlmacenRepository.findAll();
    }

    public Optional<TrabajadorAlmacen> getTrabajadorAlmacen(Long id) {
        return trabajadorAlmacenRepository.findById(id);
    }

    public TrabajadorAlmacen saveOrUpdate(TrabajadorAlmacen trabajadorAlmacen) {
        return trabajadorAlmacenRepository.save(trabajadorAlmacen);
    }

    public void delete(Long id) {
        trabajadorAlmacenRepository.deleteById(id);
    }
}


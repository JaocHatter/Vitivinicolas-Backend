package com.example.demo.service;

import com.example.demo.model.Proveedor;
import com.example.demo.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public List<Proveedor> getProveedores() {
        return (List<Proveedor>) proveedorRepository.findAll();
    }

    public Optional<Proveedor> getProveedor(Long id) {
        return proveedorRepository.findById(id);
    }

    public void saveOrUpdate(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    public void delete(Long id) {
        proveedorRepository.deleteById(id);
    }
}
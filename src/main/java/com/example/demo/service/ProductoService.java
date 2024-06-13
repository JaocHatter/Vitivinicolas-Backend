package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.model.Reporte;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    public List<Producto> getProductos() {
        return (List<Producto>) productoRepository.findAll();
    }
    public Optional<Producto> getProducto(String id) {
        return productoRepository.findById(id);
    }
    public void saveOrUpdate(Producto producto){ productoRepository.save(producto); }
}

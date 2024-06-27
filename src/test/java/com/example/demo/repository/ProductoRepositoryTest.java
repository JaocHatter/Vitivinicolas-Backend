/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jarex
 */
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        producto1 = Producto.builder()
                .idProducto(1L)
                .nombreProducto("Producto A")
                .stockProducto(100L)
                .build();

        producto2 = Producto.builder()
                .idProducto(2L)
                .nombreProducto("Producto B")
                .stockProducto(200L)
                .build();

        productoRepository.save(producto1);
        productoRepository.save(producto2);
    }

    @Test
    public void ProductoRepository_Listar() {
        List<Producto> productoList = (List<Producto>) productoRepository.findAll();

        Assertions.assertThat(productoList).isNotNull();
        Assertions.assertThat(productoList.size()).isEqualTo(2);
    }

    @Test
    public void ProductoRepository_Insert() {
        Producto producto = Producto.builder()
                .idProducto(3L)
                .nombreProducto("Producto C")
                .stockProducto(300L)
                .build();

        Producto newProducto = productoRepository.save(producto);

        Assertions.assertThat(newProducto).isNotNull();
        Assertions.assertThat(newProducto.getIdProducto()).isEqualTo(3L);
    }

    @Test
    public void ProductoRepository_Update() {
        // Obteniendo una entidad existente para actualizar
        Optional<Producto> optionalProducto = productoRepository.findById(producto1.getIdProducto());
        Assertions.assertThat(optionalProducto).isPresent();

        Producto producto = optionalProducto.get();
        producto.setNombreProducto("Updated Producto A");

        Producto updatedProducto = productoRepository.save(producto);

        Assertions.assertThat(updatedProducto.getNombreProducto()).isEqualTo("Updated Producto A");
    }

    @Test
    public void ProductoRepository_Delete() {
        // Obteniendo una entidad existente para eliminar
        Optional<Producto> optionalProducto = productoRepository.findById(producto1.getIdProducto());
        Assertions.assertThat(optionalProducto).isPresent();

        Producto producto = optionalProducto.get();

        productoRepository.delete(producto);

        Optional<Producto> deletedProducto = productoRepository.findById(producto.getIdProducto());
        Assertions.assertThat(deletedProducto).isEmpty();
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.AdministradorEmpresa;
import com.example.demo.model.Producto;
import com.example.demo.model.Proveedor;
import com.example.demo.model.Reabastecimiento;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jarex
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReabastecimientoRepositoryTest {

    @Autowired
    private ReabastecimientoRepository reabastecimientoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private AdministradorEmpresaRepository administradorEmpresaRepository;

    private Producto producto;
    private Proveedor proveedor;
    private Reabastecimiento reabastecimiento1;
    private Reabastecimiento reabastecimiento2;

    @BeforeEach
    public void setUp() {
        AdministradorEmpresa administradorEmpresa = AdministradorEmpresa.builder()
                .nombreAdmin("Admin")
                .apellidosAdmin("Apellido Admin")
                .build();

        administradorEmpresaRepository.save(administradorEmpresa);

        producto = Producto.builder()
                .nombreProducto("Producto 1")
                .stockProducto(100L)
                .build();

        productoRepository.save(producto);

        proveedor = Proveedor.builder()
                .nombreProveedor("Proveedor 1")
                .apellidosProveedor("Apellido 1")
                .administradorEmpresa(administradorEmpresa)
                .build();

        proveedorRepository.save(proveedor);

        reabastecimiento1 = Reabastecimiento.builder()
                .fechaReabastecimiento(new Date(System.currentTimeMillis()))
                .producto(producto)
                .cantidadProducto(50L)
                .proveedor(proveedor)
                .build();

        reabastecimiento2 = Reabastecimiento.builder()
                .fechaReabastecimiento(new Date(System.currentTimeMillis()))
                .producto(producto)
                .cantidadProducto(70L)
                .proveedor(proveedor)
                .build();

        reabastecimientoRepository.save(reabastecimiento1);
        reabastecimientoRepository.save(reabastecimiento2);
    }

    @Test
    public void ReabastecimientoRepository_Listar() {
        List<Reabastecimiento> reabastecimientoList = (List<Reabastecimiento>) reabastecimientoRepository.findAll();

        Assertions.assertThat(reabastecimientoList).isNotNull();
        Assertions.assertThat(reabastecimientoList.size()).isEqualTo(2);
    }

    @Test
    public void ReabastecimientoRepository_Insert() {
        Reabastecimiento reabastecimiento = Reabastecimiento.builder()
                .fechaReabastecimiento(new Date(System.currentTimeMillis()))
                .producto(producto)
                .cantidadProducto(50L)
                .proveedor(proveedor)
                .build();

        Reabastecimiento newReabastecimiento = reabastecimientoRepository.save(reabastecimiento);

        Assertions.assertThat(newReabastecimiento).isNotNull();
        Assertions.assertThat(newReabastecimiento.getIdReabastecimiento()).isGreaterThan(0);
    }

    @Test
    public void ReabastecimientoRepository_Update() {
        // Obteniendo una entidad existente para actualizar
        Optional<Reabastecimiento> optionalReabastecimiento = reabastecimientoRepository.findById(reabastecimiento1.getIdReabastecimiento());
        Assertions.assertThat(optionalReabastecimiento).isPresent();

        Reabastecimiento reabastecimiento = optionalReabastecimiento.get();
        reabastecimiento.setCantidadProducto(100L);

        Reabastecimiento updatedReabastecimiento = reabastecimientoRepository.save(reabastecimiento);

        Assertions.assertThat(updatedReabastecimiento.getCantidadProducto()).isEqualTo(100L);
    }

    @Test
    public void ReabastecimientoRepository_Delete() {
        // Obteniendo una entidad existente para eliminar
        Optional<Reabastecimiento> optionalReabastecimiento = reabastecimientoRepository.findById(reabastecimiento1.getIdReabastecimiento());
        Assertions.assertThat(optionalReabastecimiento).isPresent();

        Reabastecimiento reabastecimiento = optionalReabastecimiento.get();

        reabastecimientoRepository.delete(reabastecimiento);

        Optional<Reabastecimiento> deletedReabastecimiento = reabastecimientoRepository.findById(reabastecimiento.getIdReabastecimiento());
        Assertions.assertThat(deletedReabastecimiento).isEmpty();
    }
}
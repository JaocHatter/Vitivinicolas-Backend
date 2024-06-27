/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.AdministradorEmpresa;
import com.example.demo.model.Proveedor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jarex
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProveedorRepositoryTest {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private AdministradorEmpresaRepository administradorEmpresaRepository;

    private AdministradorEmpresa administradorEmpresa;
    private Proveedor proveedor1;
    private Proveedor proveedor2;

    @BeforeEach
    public void setUp() {
        administradorEmpresa = AdministradorEmpresa.builder()
                .nombreAdmin("Admin")
                .apellidosAdmin("Apellido Admin")
                .build();

        administradorEmpresaRepository.save(administradorEmpresa);

        proveedor1 = Proveedor.builder()
                .nombreProveedor("Proveedor A")
                .apellidosProveedor("Apellido A")
                .administradorEmpresa(administradorEmpresa)
                .build();

        proveedor2 = Proveedor.builder()
                .nombreProveedor("Proveedor B")
                .apellidosProveedor("Apellido B")
                .administradorEmpresa(administradorEmpresa)
                .build();

        proveedorRepository.save(proveedor1);
        proveedorRepository.save(proveedor2);
    }

    @Test
    public void ProveedorRepository_Listar() {
        List<Proveedor> proveedorList = (List<Proveedor>) proveedorRepository.findAll();

        Assertions.assertThat(proveedorList).isNotNull();
        Assertions.assertThat(proveedorList.size()).isEqualTo(2);
    }

    @Test
    public void ProveedorRepository_Insert() {
        Proveedor proveedor = Proveedor.builder()
                .nombreProveedor("Proveedor C")
                .apellidosProveedor("Apellido C")
                .administradorEmpresa(administradorEmpresa)
                .build();

        Proveedor newProveedor = proveedorRepository.save(proveedor);

        Assertions.assertThat(newProveedor).isNotNull();
        Assertions.assertThat(newProveedor.getIdProveedor()).isGreaterThan(0);
    }

    @Test
    public void ProveedorRepository_Update() {
        // Obteniendo una entidad existente para actualizar
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(proveedor1.getIdProveedor());
        Assertions.assertThat(optionalProveedor).isPresent();

        Proveedor proveedor = optionalProveedor.get();
        proveedor.setNombreProveedor("Updated Proveedor A");

        Proveedor updatedProveedor = proveedorRepository.save(proveedor);

        Assertions.assertThat(updatedProveedor.getNombreProveedor()).isEqualTo("Updated Proveedor A");
    }

    @Test
    public void ProveedorRepository_Delete() {
        // Obteniendo una entidad existente para eliminar
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(proveedor1.getIdProveedor());
        Assertions.assertThat(optionalProveedor).isPresent();

        Proveedor proveedor = optionalProveedor.get();

        proveedorRepository.delete(proveedor);

        Optional<Proveedor> deletedProveedor = proveedorRepository.findById(proveedor.getIdProveedor());
        Assertions.assertThat(deletedProveedor).isEmpty();
    }
}


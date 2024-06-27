/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.AdministradorEmpresa;
import com.example.demo.model.TrabajadorAlmacen;
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
public class TrabajadorAlmacenRepositoryTest {

    @Autowired
    private TrabajadorAlmacenRepository trabajadorAlmacenRepository;

    @Autowired
    private AdministradorEmpresaRepository administradorEmpresaRepository;

    private AdministradorEmpresa administradorEmpresa;
    private TrabajadorAlmacen trabajador1;
    private TrabajadorAlmacen trabajador2;

    @BeforeEach
    public void setUp() {
        administradorEmpresa = AdministradorEmpresa.builder()
                .nombreAdmin("Admin")
                .apellidosAdmin("Apellido Admin")
                .build();

        administradorEmpresaRepository.save(administradorEmpresa);

        trabajador1 = TrabajadorAlmacen.builder()
                .trabajadorNombre("Trabajador A")
                .trabajadorApellidos("Apellido A")
                .administradorEmpresa(administradorEmpresa)
                .build();

        trabajador2 = TrabajadorAlmacen.builder()
                .trabajadorNombre("Trabajador B")
                .trabajadorApellidos("Apellido B")
                .administradorEmpresa(administradorEmpresa)
                .build();

        trabajadorAlmacenRepository.save(trabajador1);
        trabajadorAlmacenRepository.save(trabajador2);
    }

    @Test
    public void TrabajadorAlmacenRepository_Listar() {
        List<TrabajadorAlmacen> trabajadorList = (List<TrabajadorAlmacen>) trabajadorAlmacenRepository.findAll();

        Assertions.assertThat(trabajadorList).isNotNull();
        Assertions.assertThat(trabajadorList.size()).isEqualTo(2);
    }

    @Test
    public void TrabajadorAlmacenRepository_Insert() {
        TrabajadorAlmacen trabajador = TrabajadorAlmacen.builder()
                .trabajadorNombre("Trabajador C")
                .trabajadorApellidos("Apellido C")
                .administradorEmpresa(administradorEmpresa)
                .build();

        TrabajadorAlmacen newTrabajador = trabajadorAlmacenRepository.save(trabajador);

        Assertions.assertThat(newTrabajador).isNotNull();
        Assertions.assertThat(newTrabajador.getIdTrabajadorAlmacen()).isGreaterThan(0);
    }

    @Test
    public void TrabajadorAlmacenRepository_Update() {
        // Obteniendo una entidad existente para actualizar
        Optional<TrabajadorAlmacen> optionalTrabajador = trabajadorAlmacenRepository.findById(trabajador1.getIdTrabajadorAlmacen());
        Assertions.assertThat(optionalTrabajador).isPresent();

        TrabajadorAlmacen trabajador = optionalTrabajador.get();
        trabajador.setTrabajadorNombre("Updated Trabajador A");

        TrabajadorAlmacen updatedTrabajador = trabajadorAlmacenRepository.save(trabajador);

        Assertions.assertThat(updatedTrabajador.getTrabajadorNombre()).isEqualTo("Updated Trabajador A");
    }

    @Test
    public void TrabajadorAlmacenRepository_Delete() {
        // Obteniendo una entidad existente para eliminar
        Optional<TrabajadorAlmacen> optionalTrabajador = trabajadorAlmacenRepository.findById(trabajador1.getIdTrabajadorAlmacen());
        Assertions.assertThat(optionalTrabajador).isPresent();

        TrabajadorAlmacen trabajador = optionalTrabajador.get();

        trabajadorAlmacenRepository.delete(trabajador);

        Optional<TrabajadorAlmacen> deletedTrabajador = trabajadorAlmacenRepository.findById(trabajador.getIdTrabajadorAlmacen());
        Assertions.assertThat(deletedTrabajador).isEmpty();
    }
}
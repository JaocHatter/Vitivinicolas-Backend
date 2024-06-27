/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.AdministradorEmpresa;
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
public class AdministradorEmpresaRepositoryTest {

    @Autowired
    private AdministradorEmpresaRepository administradorEmpresaRepository;
    private  AdministradorEmpresa admin1,admin2;
    @BeforeEach
    public void setUp() {
        admin1 = AdministradorEmpresa.builder()
                .nombreAdmin("Admin 1")
                .apellidosAdmin("Apellido 1")
                .build();

        admin2 = AdministradorEmpresa.builder()
                .nombreAdmin("Admin 2")
                .apellidosAdmin("Apellido 2")
                .build();

        administradorEmpresaRepository.save(admin1);
        administradorEmpresaRepository.save(admin2);
    }

    @Test
    public void AdministradorEmpresaRepository_Listar() {
        List<AdministradorEmpresa> adminList = (List<AdministradorEmpresa>) administradorEmpresaRepository.findAll();

        Assertions.assertThat(adminList).isNotNull();
        Assertions.assertThat(adminList.size()).isEqualTo(2);
    }

    @Test
    public void AdministradorEmpresaRepository_Insert() {
        AdministradorEmpresa admin = AdministradorEmpresa.builder()
                .nombreAdmin("Admin 3")
                .apellidosAdmin("Apellido 3")
                .build();

        AdministradorEmpresa newAdmin = administradorEmpresaRepository.save(admin);

        Assertions.assertThat(newAdmin).isNotNull();
        Assertions.assertThat(newAdmin.getIdAdministradorEmpresa()).isGreaterThan(0);
    }

    @Test
    public void AdministradorEmpresaRepository_Update() {
        // Obteniendo una entidad existente para actualizar
        Optional<AdministradorEmpresa> optionalAdmin = administradorEmpresaRepository.findById(admin1.getIdAdministradorEmpresa());
        Assertions.assertThat(optionalAdmin).isPresent();

        AdministradorEmpresa admin = optionalAdmin.get();
        admin.setNombreAdmin("Updated Admin");

        AdministradorEmpresa updatedAdmin = administradorEmpresaRepository.save(admin);

        Assertions.assertThat(updatedAdmin.getNombreAdmin()).isEqualTo("Updated Admin");
    }

    @Test
    public void AdministradorEmpresaRepository_Delete() {
        // Obteniendo una entidad existente para eliminar
        Optional<AdministradorEmpresa> optionalAdmin = administradorEmpresaRepository.findById(admin1.getIdAdministradorEmpresa());
        Assertions.assertThat(optionalAdmin).isPresent();

        AdministradorEmpresa admin = optionalAdmin.get();

        administradorEmpresaRepository.delete(admin);

        Optional<AdministradorEmpresa> deletedAdmin = administradorEmpresaRepository.findById(admin.getIdAdministradorEmpresa());
        Assertions.assertThat(deletedAdmin).isEmpty();
    }
}

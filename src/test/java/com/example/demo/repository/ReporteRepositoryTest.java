/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.AdministradorEmpresa;
import com.example.demo.model.Reporte;
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
public class ReporteRepositoryTest {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private TrabajadorAlmacenRepository trabajadorAlmacenRepository;

    @Autowired
    private AdministradorEmpresaRepository administradorEmpresaRepository;

    private TrabajadorAlmacen trabajadorAlmacen;
    private AdministradorEmpresa administradorEmpresa;
    private Reporte reporte1;
    private Reporte reporte2;

    @BeforeEach
    public void setUp() {
        administradorEmpresa = AdministradorEmpresa.builder()
                .nombreAdmin("Admin")
                .apellidosAdmin("Apellido Admin")
                .build();

        administradorEmpresaRepository.save(administradorEmpresa);

        trabajadorAlmacen = TrabajadorAlmacen.builder()
                .trabajadorNombre("Trabajador")
                .trabajadorApellidos("Apellido")
                .administradorEmpresa(administradorEmpresa)
                .build();

        trabajadorAlmacenRepository.save(trabajadorAlmacen);

        reporte1 = Reporte.builder()
                .descripcion("Descripción 1")
                .trabajadorAlmacen(trabajadorAlmacen)
                .administradorEmpresa(administradorEmpresa)
                .build();

        reporte2 = Reporte.builder()
                .descripcion("Descripción 2")
                .trabajadorAlmacen(trabajadorAlmacen)
                .administradorEmpresa(administradorEmpresa)
                .build();

        reporteRepository.save(reporte1);
        reporteRepository.save(reporte2);
    }

    @Test
    public void ReporteRepository_Listar() {
        List<Reporte> reporteList = (List<Reporte>) reporteRepository.findAll();

        Assertions.assertThat(reporteList).isNotNull();
        Assertions.assertThat(reporteList.size()).isEqualTo(2);
    }

    @Test
    public void ReporteRepository_Insert() {
        Reporte reporte = Reporte.builder()
                .descripcion("Descripción")
                .trabajadorAlmacen(trabajadorAlmacen)
                .administradorEmpresa(administradorEmpresa)
                .build();

        Reporte newReporte = reporteRepository.save(reporte);

        Assertions.assertThat(newReporte).isNotNull();
        Assertions.assertThat(newReporte.getIdReporte()).isGreaterThan(0);
    }

    @Test
    public void ReporteRepository_Update() {
        // Obteniendo una entidad existente para actualizar
        Optional<Reporte> optionalReporte = reporteRepository.findById(reporte1.getIdReporte());
        Assertions.assertThat(optionalReporte).isPresent();

        Reporte reporte = optionalReporte.get();
        reporte.setDescripcion("Updated Descripción 1");

        Reporte updatedReporte = reporteRepository.save(reporte);

        Assertions.assertThat(updatedReporte.getDescripcion()).isEqualTo("Updated Descripción 1");
    }

    @Test
    public void ReporteRepository_Delete() {
        // Obteniendo una entidad existente para eliminar
        Optional<Reporte> optionalReporte = reporteRepository.findById(reporte1.getIdReporte());
        Assertions.assertThat(optionalReporte).isPresent();

        Reporte reporte = optionalReporte.get();

        reporteRepository.delete(reporte);

        Optional<Reporte> deletedReporte = reporteRepository.findById(reporte.getIdReporte());
        Assertions.assertThat(deletedReporte).isEmpty();
    }
}
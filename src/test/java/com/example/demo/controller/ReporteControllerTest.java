package com.example.demo.controller;

import com.example.demo.model.Reporte;
import com.example.demo.model.AdministradorEmpresa;
import com.example.demo.model.TrabajadorAlmacen;
import com.example.demo.service.ReporteService;
import com.example.demo.service.AdministradorEmpresaService;
import com.example.demo.service.TrabajadorAlmacenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ReporteController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteService reporteService;

    @MockBean
    private AdministradorEmpresaService administradorEmpresaService;

    @MockBean
    private TrabajadorAlmacenService trabajadorAlmacenService;

    @Autowired
    private ObjectMapper objectMapper;

    private Reporte reporte1, reporte2;
    private AdministradorEmpresa administradorEmpresa;
    private TrabajadorAlmacen trabajadorAlmacen;

    @BeforeEach
    public void init() {
        administradorEmpresa = AdministradorEmpresa.builder()
                .idAdministradorEmpresa(1L)
                .nombreAdmin("Admin A")
                .apellidosAdmin("Apellido A")
                .build();

        trabajadorAlmacen = TrabajadorAlmacen.builder()
                .idTrabajadorAlmacen(1L)
                .trabajadorNombre("Trabajador A")
                .trabajadorApellidos("Apellido A")
                .administradorEmpresa(administradorEmpresa)
                .build();

        reporte1 = Reporte.builder()
                .idReporte(1L)
                .descripcion("Descripción 1")
                .trabajadorAlmacen(trabajadorAlmacen)
                .administradorEmpresa(administradorEmpresa)
                .build();

        reporte2 = Reporte.builder()
                .idReporte(2L)
                .descripcion("Descripción 2")
                .trabajadorAlmacen(trabajadorAlmacen)
                .administradorEmpresa(administradorEmpresa)
                .build();
    }

    @Test
    public void ReporteController_Insert() throws Exception {
        given(administradorEmpresaService.getAdministradorEmpresa(1L)).willReturn(Optional.of(administradorEmpresa));
        given(trabajadorAlmacenService.getTrabajadorAlmacen(1L)).willReturn(Optional.of(trabajadorAlmacen));
        given(reporteService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/v1/reporte/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reporte1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcion", CoreMatchers.is(reporte1.getDescripcion())));
    }

    @Test
    public void ReporteController_Listar() throws Exception {
        List<Reporte> reporteList = new ArrayList<>();
        reporteList.add(reporte1);
        reporteList.add(reporte2);

        when(reporteService.getReportes()).thenReturn(reporteList);

        ResultActions response = mockMvc.perform(get("/api/v1/reporte/listar")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(reporteList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
    }

    @Test
    public void ReporteController_Delete() throws Exception {
        Optional<Reporte> optionalReporte = Optional.of(reporte1);
        when(reporteService.getReporte(1L)).thenReturn(optionalReporte);

        ResultActions response = mockMvc.perform(delete("/api/v1/reporte/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reporte1)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

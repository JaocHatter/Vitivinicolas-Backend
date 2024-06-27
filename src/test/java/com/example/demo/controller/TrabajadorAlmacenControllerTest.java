package com.example.demo.controller;

import com.example.demo.model.TrabajadorAlmacen;
import com.example.demo.model.AdministradorEmpresa;
import com.example.demo.service.TrabajadorAlmacenService;
import com.example.demo.service.AdministradorEmpresaService;
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

@WebMvcTest(controllers = TrabajadorAlmacenController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class TrabajadorAlmacenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrabajadorAlmacenService trabajadorAlmacenService;

    @MockBean
    private AdministradorEmpresaService administradorEmpresaService;

    @Autowired
    private ObjectMapper objectMapper;

    private TrabajadorAlmacen trabajadorAlmacen1, trabajadorAlmacen2;
    private AdministradorEmpresa administradorEmpresa;

    @BeforeEach
    public void init() {
        administradorEmpresa = AdministradorEmpresa.builder()
                .idAdministradorEmpresa(1L)
                .nombreAdmin("Admin A")
                .apellidosAdmin("Apellido A")
                .build();

        trabajadorAlmacen1 = TrabajadorAlmacen.builder()
                .idTrabajadorAlmacen(1L)
                .trabajadorNombre("Trabajador A")
                .trabajadorApellidos("Apellido A")
                .administradorEmpresa(administradorEmpresa)
                .build();

        trabajadorAlmacen2 = TrabajadorAlmacen.builder()
                .idTrabajadorAlmacen(2L)
                .trabajadorNombre("Trabajador B")
                .trabajadorApellidos("Apellido B")
                .administradorEmpresa(administradorEmpresa)
                .build();
    }

    @Test
    public void TrabajadorAlmacenController_Insert() throws Exception {
        given(administradorEmpresaService.getAdministradorEmpresa(1L)).willReturn(Optional.of(administradorEmpresa));
        given(trabajadorAlmacenService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/v1/trabajadoralmacen/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trabajadorAlmacen1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trabajadorNombre", CoreMatchers.is(trabajadorAlmacen1.getTrabajadorNombre())));
    }

    @Test
    public void TrabajadorAlmacenController_Listar() throws Exception {
        List<TrabajadorAlmacen> trabajadorAlmacenList = new ArrayList<>();
        trabajadorAlmacenList.add(trabajadorAlmacen1);
        trabajadorAlmacenList.add(trabajadorAlmacen2);

        when(trabajadorAlmacenService.getTrabajadorAlmacenes()).thenReturn(trabajadorAlmacenList);

        ResultActions response = mockMvc.perform(get("/api/v1/trabajadoralmacen/listar")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(trabajadorAlmacenList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
    }

    @Test
    public void TrabajadorAlmacenController_Delete() throws Exception {
        Optional<TrabajadorAlmacen> optionalTrabajadorAlmacen = Optional.of(trabajadorAlmacen1);
        when(trabajadorAlmacenService.getTrabajadorAlmacen(1L)).thenReturn(optionalTrabajadorAlmacen);

        ResultActions response = mockMvc.perform(delete("/api/v1/trabajadoralmacen/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trabajadorAlmacen1)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

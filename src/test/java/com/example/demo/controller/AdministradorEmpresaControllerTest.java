package com.example.demo.controller;

import com.example.demo.model.AdministradorEmpresa;
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

@WebMvcTest(controllers = AdministradorEmpresaController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class AdministradorEmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdministradorEmpresaService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    private AdministradorEmpresa admin1, admin2;

    @BeforeEach
    public void init() {
        admin1 = AdministradorEmpresa.builder()
                .idAdministradorEmpresa(1L)
                .nombreAdmin("Admin A")
                .apellidosAdmin("Apellido A")
                .build();

        admin2 = AdministradorEmpresa.builder()
                .idAdministradorEmpresa(2L)
                .nombreAdmin("Admin B")
                .apellidosAdmin("Apellido B")
                .build();
    }

    @Test
    public void AdministradorEmpresaController_Insert() throws Exception {
        given(adminService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/v1/administradorempresa/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreAdmin", CoreMatchers.is(admin1.getNombreAdmin())));
    }

    @Test
    public void AdministradorEmpresaController_Listar() throws Exception {
        List<AdministradorEmpresa> adminList = new ArrayList<>();
        adminList.add(admin1);
        adminList.add(admin2);

        when(adminService.getAdministradoresEmpresa()).thenReturn(adminList);

        ResultActions response = mockMvc.perform(get("/api/v1/administradorempresa/listar")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(adminList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
    }

    @Test
    public void AdministradorEmpresaController_Update() throws Exception {
        Optional<AdministradorEmpresa> optionalAdmin = Optional.of(admin1);
        when(adminService.getAdministradorEmpresa(1L)).thenReturn(optionalAdmin);
        given(adminService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        admin1.setNombreAdmin("Updated Admin A");

        ResultActions response = mockMvc.perform(post("/api/v1/administradorempresa/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreAdmin", CoreMatchers.is("Updated Admin A")));
    }

    @Test
    public void AdministradorEmpresaController_Delete() throws Exception {
        Optional<AdministradorEmpresa> optionalAdmin = Optional.of(admin1);
        when(adminService.getAdministradorEmpresa(1L)).thenReturn(optionalAdmin);

        ResultActions response = mockMvc.perform(delete("/api/v1/administradorempresa/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin1)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

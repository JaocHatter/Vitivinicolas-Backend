package com.example.demo.controller;
import com.example.demo.model.Proveedor;
import com.example.demo.service.ProveedorService;
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
@WebMvcTest(controllers = ProveedorController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProveedorService proveedorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Proveedor proveedor1, proveedor2;

    @BeforeEach
    public void init() {
        proveedor1 = Proveedor.builder()
                .idProveedor(1L)
                .nombreProveedor("Proveedor A")
                .apellidosProveedor("Apellido A")
                .build();

        proveedor2 = Proveedor.builder()
                .idProveedor(2L)
                .nombreProveedor("Proveedor B")
                .apellidosProveedor("Apellido B")
                .build();
    }

    @Test
    public void ProveedorController_Insert() throws Exception {
        given(proveedorService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/v1/proveedor/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedor1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreProveedor", CoreMatchers.is(proveedor1.getNombreProveedor())));
    }

    @Test
    public void ProveedorController_Listar() throws Exception {
        List<Proveedor> proveedorList = new ArrayList<>();
        proveedorList.add(proveedor1);
        proveedorList.add(proveedor2);

        when(proveedorService.getProveedores()).thenReturn(proveedorList);

        ResultActions response = mockMvc.perform(get("/api/v1/proveedor/listar")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(proveedorList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
    }

    @Test
    public void ProveedorController_Update() throws Exception {
        Optional<Proveedor> optionalProveedor = Optional.of(proveedor1);
        when(proveedorService.getProveedor(1L)).thenReturn(optionalProveedor);
        given(proveedorService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        proveedor1.setNombreProveedor("Updated Proveedor A");

        ResultActions response = mockMvc.perform(post("/api/v1/proveedor/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedor1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreProveedor", CoreMatchers.is("Updated Proveedor A")));
    }

    @Test
    public void ProveedorController_Delete() throws Exception {
        Optional<Proveedor> optionalProveedor = Optional.of(proveedor1);
        when(proveedorService.getProveedor(1L)).thenReturn(optionalProveedor);

        ResultActions response = mockMvc.perform(delete("/api/v1/proveedor/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedor1)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
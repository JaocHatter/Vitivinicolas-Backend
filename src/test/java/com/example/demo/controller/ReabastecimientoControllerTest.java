package com.example.demo.controller;

import com.example.demo.model.Reabastecimiento;
import com.example.demo.model.Producto;
import com.example.demo.model.Proveedor;
import com.example.demo.service.ReabastecimientoService;
import com.example.demo.service.ProductoService;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ReabastecimientoController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class ReabastecimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReabastecimientoService reabastecimientoService;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private ProveedorService proveedorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Reabastecimiento reabastecimiento1, reabastecimiento2;
    private Producto producto;
    private Proveedor proveedor;

    @BeforeEach
    public void init() {
        producto = Producto.builder()
                .idProducto(1L)
                .nombreProducto("Producto A")
                .stockProducto(100L)
                .build();

        proveedor = Proveedor.builder()
                .idProveedor(1L)
                .nombreProveedor("Proveedor A")
                .apellidosProveedor("Apellido A")
                .build();

        reabastecimiento1 = Reabastecimiento.builder()
                .idReabastecimiento(1L)
                .fechaReabastecimiento(new Date(System.currentTimeMillis()))
                .producto(producto)
                .cantidadProducto(50L)
                .proveedor(proveedor)
                .build();

        reabastecimiento2 = Reabastecimiento.builder()
                .idReabastecimiento(2L)
                .fechaReabastecimiento(new Date(System.currentTimeMillis()))
                .producto(producto)
                .cantidadProducto(70L)
                .proveedor(proveedor)
                .build();
    }

    @Test
    public void ReabastecimientoController_Insert() throws Exception {
        given(proveedorService.getProveedor(1L)).willReturn(Optional.of(proveedor));
        given(productoService.getProducto(1L)).willReturn(Optional.of(producto));
        given(reabastecimientoService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/v1/reabastecimiento/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reabastecimiento1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantidadProducto", CoreMatchers.is(reabastecimiento1.getCantidadProducto().intValue())));
    }

    @Test
    public void ReabastecimientoController_Listar() throws Exception {
        List<Reabastecimiento> reabastecimientoList = new ArrayList<>();
        reabastecimientoList.add(reabastecimiento1);
        reabastecimientoList.add(reabastecimiento2);

        when(reabastecimientoService.getReabastecimientos()).thenReturn(reabastecimientoList);

        ResultActions response = mockMvc.perform(get("/api/v1/reabastecimiento/listar")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(reabastecimientoList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
    }

    @Test
    public void ReabastecimientoController_Update() throws Exception {
        Optional<Reabastecimiento> optionalReabastecimiento = Optional.of(reabastecimiento1);
        when(reabastecimientoService.getReabastecimiento(1L)).thenReturn(optionalReabastecimiento);
        given(reabastecimientoService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        reabastecimiento1.setCantidadProducto(100L);

        ResultActions response = mockMvc.perform(post("/api/v1/reabastecimiento/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reabastecimiento1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantidadProducto", CoreMatchers.is(100)));
    }

    @Test
    public void ReabastecimientoController_Delete() throws Exception {
        Optional<Reabastecimiento> optionalReabastecimiento = Optional.of(reabastecimiento1);
        when(reabastecimientoService.getReabastecimiento(1L)).thenReturn(optionalReabastecimiento);

        ResultActions response = mockMvc.perform(delete("/api/v1/reabastecimiento/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reabastecimiento1)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

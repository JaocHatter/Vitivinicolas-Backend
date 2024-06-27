package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(controllers = ProductoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto1, producto2;

    @BeforeEach
    public void init() {
        producto1 = Producto.builder()
                .idProducto(1L)
                .nombreProducto("Producto A")
                .stockProducto(100L)
                .build();

        producto2 = Producto.builder()
                .idProducto(2L)
                .nombreProducto("Producto B")
                .stockProducto(200L)
                .build();
    }

    @Test
    public void ProductoController_Insert() throws Exception {
        given(productoService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/v1/producto/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreProducto", CoreMatchers.is(producto1.getNombreProducto())));
    }

    @Test
    public void ProductoController_Listar() throws Exception {
        List<Producto> productoList = new ArrayList<>();
        productoList.add(producto1);
        productoList.add(producto2);

        when(productoService.getProductos()).thenReturn(productoList);

        ResultActions response = mockMvc.perform(get("/api/v1/producto/listar")
                .contentType(MediaType.APPLICATION_JSON));

        ((ResultActions) response).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(productoList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
    }

    @Test
    public void ProductoController_Update() throws Exception {
        Optional<Producto> optionalProducto = Optional.of(producto1);
        when(productoService.getProducto(1L)).thenReturn(optionalProducto);
        given(productoService.saveOrUpdate(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        producto1.setStockProducto(150L);

        ResultActions response = mockMvc.perform(post("/api/v1/producto/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto1)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockProducto", CoreMatchers.is(150)));
    }

    @Test
    public void ProductoController_Delete() throws Exception {
        Optional<Producto> optionalProducto = Optional.of(producto1);
        when(productoService.getProducto(1L)).thenReturn(optionalProducto);

        ResultActions response = mockMvc.perform(delete("/api/v1/producto/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto1)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

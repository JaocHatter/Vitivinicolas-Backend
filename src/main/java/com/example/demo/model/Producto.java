package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name="producto")
public class Producto {
    @Id
    @Column(name = "id_producto")
    private long idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "stock_producto")
    private Long stockProducto;

    public Producto() {

    }
    public Producto(long idProducto, String nombreProducto, Long stockProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.stockProducto = stockProducto;
    }
}

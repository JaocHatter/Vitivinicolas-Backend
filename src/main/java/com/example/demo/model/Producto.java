package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="producto")
public class Producto {
    @Id
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "stock_producto")
    private Long stockProducto;
}

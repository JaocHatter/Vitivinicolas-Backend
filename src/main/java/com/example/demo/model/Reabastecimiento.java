package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="reabastecimiento")
public class Reabastecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReabastecimiento;

    @Column(name = "fecha_reabastecimiento")
    private java.sql.Date fechaReabastecimiento;

    @ManyToOne
    @JoinColumn(name = "nombre_producto", referencedColumnName = "nombre_producto")
    private Producto producto;

    @Column(name = "cantidad_producto")
    private Long cantidadProducto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    private Proveedor proveedor;
}

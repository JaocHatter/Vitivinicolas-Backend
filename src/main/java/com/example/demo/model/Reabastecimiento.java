package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Builder
@Table(name="reabastecimiento")
public class Reabastecimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reabastecimiento")
    private Long idReabastecimiento;

    @Column(name = "fecha_reabastecimiento")
    private Date fechaReabastecimiento;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;

    @Column(name = "cantidad_producto")
    private Long cantidadProducto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    private Proveedor proveedor;

    public Reabastecimiento() {

    }
    public Reabastecimiento(Long idReabastecimiento, Date fechaReabastecimiento, Producto producto, Long cantidadProducto, Proveedor proveedor) {
        this.idReabastecimiento = idReabastecimiento;
        this.fechaReabastecimiento = fechaReabastecimiento;
        this.producto = producto;
        this.cantidadProducto = cantidadProducto;
        this.proveedor = proveedor;
    }
}

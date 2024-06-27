package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvedor;

    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Column(name = "apellidos_proveedor")
    private String apellidosProveedor;

    @ManyToOne
    @JoinColumn(name = "id_administrador_empresa", referencedColumnName = "id_administrador_empresa")
    private AdministradorEmpresa administradorEmpresa;
}

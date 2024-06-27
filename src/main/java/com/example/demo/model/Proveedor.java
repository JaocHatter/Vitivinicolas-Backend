package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name="proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Column(name = "apellidos_proveedor")
    private String apellidosProveedor;

    @ManyToOne
    @JoinColumn(name = "id_administrador_empresa", referencedColumnName = "id_administrador_empresa")
    private AdministradorEmpresa administradorEmpresa;

    public Proveedor(){}
    public Proveedor(Long idProveedor, String nombreProveedor, String apellidosProveedor, AdministradorEmpresa administradorEmpresa) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.apellidosProveedor = apellidosProveedor;
        this.administradorEmpresa = administradorEmpresa;
    }
}

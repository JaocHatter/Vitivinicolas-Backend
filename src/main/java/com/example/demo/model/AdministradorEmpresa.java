package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="administrador_empresa")
public class AdministradorEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador_empresa")
    private Long idAdministradorEmpresa;

    @Column(name = "nombre_admin", nullable = false)
    private String nombreAdmin;

    @Column(name = "apellidos_admin", nullable = false)
    private String apellidosAdmin;
}

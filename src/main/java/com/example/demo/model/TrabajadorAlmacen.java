package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Data;
import com.example.demo.model.AdministradorEmpresa;
@Data
@Entity
@Table(name="trabajador_almacen")
public class TrabajadorAlmacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajador_almacen")
    private Long idTrabajadorAlmacen;

    @Column(name = "trabajador_nombre", nullable = false)
    private String trabajadorNombre;

    @Column(name = "trabajador_apellidos", nullable = false)
    private String trabajadorApellidos;

    @ManyToOne
    @JoinColumn(name = "id_administrador_empresa", referencedColumnName = "id_administrador_empresa")
    private AdministradorEmpresa administradorEmpresa;
}

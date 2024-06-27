package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
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

    public TrabajadorAlmacen() {

    }
    public TrabajadorAlmacen(Long idTrabajadorAlmacen, String trabajadorNombre, String trabajadorApellidos, AdministradorEmpresa administradorEmpresa) {
        this.idTrabajadorAlmacen = idTrabajadorAlmacen;
        this.trabajadorNombre = trabajadorNombre;
        this.trabajadorApellidos = trabajadorApellidos;
        this.administradorEmpresa = administradorEmpresa;
    }
}

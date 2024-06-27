package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name="reporte")
public class Reporte {
    @Id
    @Column(name = "id_reporte")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    @ManyToOne
    @JoinColumn(name = "id_trabajador_almacen", referencedColumnName = "id_trabajador_almacen")
    private TrabajadorAlmacen trabajadorAlmacen;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_administrador_empresa", referencedColumnName = "id_administrador_empresa")
    private AdministradorEmpresa administradorEmpresa;

    public Reporte() {

    }
    public Reporte(Long idReporte, TrabajadorAlmacen trabajadorAlmacen, String descripcion, AdministradorEmpresa administradorEmpresa) {
        this.idReporte = idReporte;
        this.trabajadorAlmacen = trabajadorAlmacen;
        this.descripcion = descripcion;
        this.administradorEmpresa = administradorEmpresa;
    }
}

package uni.isw.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="proveedor")
public class Proveedor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proveedorId;

    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Column(name = "apellidos_proveedor")
    private String apellidosProveedor;

    @ManyToOne
    @JoinColumn(name = "administrador_id", foreignKey = @ForeignKey(name = "FK_ADMINISTRADOR_ID"))
    private AdministradorEmpresa administradorEmpresa;
}

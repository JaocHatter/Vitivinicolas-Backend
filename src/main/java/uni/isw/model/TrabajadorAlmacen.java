package uni.isw.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="trabajadoralmacen")
public class TrabajadorAlmacen{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trabajadorId;

    @Column(name = "trabajador_nombre")
    private String trabajadorNombre;

    @Column(name = "trabajador_apellidos")
    private String trabajadorApellidos;

    @ManyToOne
    @JoinColumn(name = "administrador_id", foreignKey = @ForeignKey(name = "FK_ADMINISTRADOR_ID"))
    private AdministradorEmpresa administradorEmpresa;

}

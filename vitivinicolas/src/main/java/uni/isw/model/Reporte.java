package uni.isw.model;
import jakarta.persistence.*;

@Table(name="reporte")
public class Reporte{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reporteId;

    @ManyToOne
    @JoinColumn(name = "trabajador_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TRABAJADOR_ID"))
    private TrabajadorAlmacen trabajadorAlmacen;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "administrador_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ADMINISTRADOR_ID"))
    private AdministradorEmpresa administradorEmpresa;

}

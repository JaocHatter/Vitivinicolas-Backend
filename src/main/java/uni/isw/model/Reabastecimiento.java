package uni.isw.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="reabastecimiento")
public class Reabastecimiento{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReabastecimiento;

    @Column(name = "fecha_reabastecimiento")
    private java.sql.Date fechaReabastecimiento;

    @ManyToOne
    @JoinColumn(name = "nombre_producto", foreignKey = @ForeignKey(name = "FK_NOMBRE_PRODUCTO"))
    private Producto producto;

    @Column(name = "cantidad_producto")
    private Long cantidadProducto;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", foreignKey = @ForeignKey(name = "FK_PROVEEDOR_ID"))
    private Proveedor proveedor;
}

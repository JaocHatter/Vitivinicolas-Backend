package uni.isw.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @Column(name = "fecha_pedido")
    private java.sql.Date fechaPedido;

    @Column(name = "cantidad_producto")
    private Long cantidadProducto;

    @ManyToOne
    @JoinColumn(name = "nombre_producto", foreignKey = @ForeignKey(name = "FK_NOMBRE_PRODUCTO"))
    private Producto producto;
}

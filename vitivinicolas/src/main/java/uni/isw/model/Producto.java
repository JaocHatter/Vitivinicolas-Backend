package uni.isw.model;

import jakarta.persistence.*;

@Table(name="producto")
public class Producto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nombreProducto;

    @Column(name = "stock_producto")
    private Long stockProducto;
}

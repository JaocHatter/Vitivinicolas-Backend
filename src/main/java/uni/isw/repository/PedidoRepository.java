package uni.isw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.isw.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}

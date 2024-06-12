package uni.isw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.isw.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,String> {
}

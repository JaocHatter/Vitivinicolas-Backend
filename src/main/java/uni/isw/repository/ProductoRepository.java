package uni.isw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import uni.isw.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto,String> {
}

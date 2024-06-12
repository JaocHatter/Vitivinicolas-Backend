package uni.isw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import uni.isw.model.TrabajadorAlmacen;

public interface TrabajadorAlmacenRepository extends CrudRepository<TrabajadorAlmacen, Long> {

}

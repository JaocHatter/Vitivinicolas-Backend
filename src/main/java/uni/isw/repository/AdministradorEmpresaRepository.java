package uni.isw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.isw.model.AdministradorEmpresa;

//implementaremos algunas operaciones CRUD que java ya tiene implementada
public interface AdministradorEmpresaRepository extends JpaRepository<AdministradorEmpresa, Long>{

}

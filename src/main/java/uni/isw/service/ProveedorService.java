package uni.isw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.Proveedor;
import uni.isw.repository.ProveedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public List<Proveedor> getProveedores() {
        return (List<Proveedor>) proveedorRepository.findAll();
    }

    public Optional<Proveedor> getProveedor(Long id) {
        return proveedorRepository.findById(id);
    }

    public void saveOrUpdate(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    public void delete(Long id) {
        proveedorRepository.deleteById(id);
    }
}
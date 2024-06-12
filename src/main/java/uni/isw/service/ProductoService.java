package uni.isw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.Producto;
import uni.isw.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    public List<Producto> getProductos() {
        return (List<Producto>) productoRepository.findAll();
    }
    public Optional<Producto> getProducto(String id) {
        return productoRepository.findById(id);
    }
}

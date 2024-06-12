package uni.isw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.TrabajadorAlmacen;
import uni.isw.repository.TrabajadorAlmacenRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorAlmacenService{
    @Autowired
    TrabajadorAlmacenRepository trabajadorAlmacenRepository;

    public List<TrabajadorAlmacen> getTrabajadorAlmacenes() {
        return (List<TrabajadorAlmacen>) trabajadorAlmacenRepository.findAll();
    }

    public Optional<TrabajadorAlmacen> getTrabajadorAlmacen(Long id) {
        return trabajadorAlmacenRepository.findById(id);
    }

    public void saveOrUpdate(TrabajadorAlmacen trabajadorAlmacen) {
        trabajadorAlmacenRepository.save(trabajadorAlmacen);
    }

    public void delete(Long id) {
        trabajadorAlmacenRepository.deleteById(id);
    }
}


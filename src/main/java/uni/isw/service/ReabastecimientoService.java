package uni.isw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.Reabastecimiento;
import uni.isw.repository.ReabastecimientoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReabastecimientoService{
    @Autowired
    private ReabastecimientoRepository reabastecimientoRepository;

    public List<Reabastecimiento> getReabastecimientos() {
        return (List<Reabastecimiento>) reabastecimientoRepository.findAll();
    }

    public Optional<Reabastecimiento> getReabastecimiento(Long id) {
        return reabastecimientoRepository.findById(id);
    }

    public void saveOrUpdate(Reabastecimiento reabastecimiento) {
        reabastecimientoRepository.save(reabastecimiento);
    }

    public void delete(Long id) {
        reabastecimientoRepository.deleteById(id);
    }
}

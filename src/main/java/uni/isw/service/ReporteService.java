package uni.isw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.Reporte;
import uni.isw.repository.ReporteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService{
    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> getReportes() {
        return (List<Reporte>) reporteRepository.findAll();
    }

    public Optional<Reporte> getReporte(Long id) {
        return reporteRepository.findById(id);
    }

    public void saveOrUpdate(Reporte reporte) {
        reporteRepository.save(reporte);
    }

    public void delete(Long id) {
        reporteRepository.deleteById(id);
    }
}

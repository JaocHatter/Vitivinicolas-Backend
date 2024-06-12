package uni.isw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.model.Reporte;
import uni.isw.service.ReporteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reporte")
public class ReporteController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReporteService reporteService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reporte>> getReportes() {
        logger.info("> listar");

        List<Reporte> listaReportes = null;
        try {
            listaReportes = reporteService.getReportes();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< listar");

        return new ResponseEntity<>(listaReportes, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reporte> getReporte(@RequestBody Optional<Reporte> reporte) {
        logger.info("> search " + reporte.toString());
        try {
            reporte = reporteService.getReporte(reporte.get().getReporteId());
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< search " + reporte.toString());
        return new ResponseEntity<>(reporte.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reporte> agregar(@RequestBody Reporte reporte) {
        logger.info("> agregar: " + reporte.toString());
        try {
            reporteService.saveOrUpdate(reporte);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< agregar: " + reporte.toString());
        return new ResponseEntity<>(reporte, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reporte> actualizar(@RequestBody Reporte reporte) {
        logger.info("> actualizar: " + reporte.toString());
        try {
            reporteService.saveOrUpdate(reporte);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< actualizar: " + reporte.toString());
        return new ResponseEntity<>(reporte, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reporte> delete(@RequestBody Optional<Reporte> reporte) {
        logger.info("> eliminar: " + reporte.toString());
        try {
            reporte = reporteService.getReporte(reporte.get().getReporteId());
            if (reporte.isPresent()) {
                reporteService.delete(reporte.get().getReporteId());
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< eliminar: " + reporte.toString());
        return new ResponseEntity<>(reporte.get(), HttpStatus.OK);
    }
}
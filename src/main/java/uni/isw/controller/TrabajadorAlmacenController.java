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
import uni.isw.model.TrabajadorAlmacen;
import uni.isw.service.TrabajadorAlmacenService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/trabajadoralmacen")
public class TrabajadorAlmacenController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TrabajadorAlmacenService trabajadorAlmacenService;

    //GET
    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrabajadorAlmacen>> getTrabajadorAlmacenes() {
        logger.info("> listar");

        List<TrabajadorAlmacen> listaTrabajadorAlmacenes = null;
        try {
            listaTrabajadorAlmacenes = trabajadorAlmacenService.getTrabajadorAlmacenes();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< listar");

        return new ResponseEntity<>(listaTrabajadorAlmacenes, HttpStatus.OK);
    }

    //GET BY ID
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrabajadorAlmacen> getTrabajadorAlmacen(@RequestBody Optional<TrabajadorAlmacen> trabajadorAlmacen) {
        logger.info("> search " + trabajadorAlmacen.toString());
        try {
            trabajadorAlmacen = trabajadorAlmacenService.getTrabajadorAlmacen(trabajadorAlmacen.get().getTrabajadorId());
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< search " + trabajadorAlmacen.toString());
        return new ResponseEntity<>(trabajadorAlmacen.get(), HttpStatus.OK);
    }

    //CREATE
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrabajadorAlmacen> agregar(@RequestBody TrabajadorAlmacen trabajadorAlmacen) {
        logger.info("> agregar: " + trabajadorAlmacen.toString());
        try {
            trabajadorAlmacenService.saveOrUpdate(trabajadorAlmacen);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< agregar: " + trabajadorAlmacen.toString());
        return new ResponseEntity<>(trabajadorAlmacen, HttpStatus.OK);
    }

    //DELETE
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrabajadorAlmacen> delete(@RequestBody Optional<TrabajadorAlmacen> trabajadorAlmacen) {
        logger.info("> eliminar: " + trabajadorAlmacen.toString());
        try {
            trabajadorAlmacen = trabajadorAlmacenService.getTrabajadorAlmacen(trabajadorAlmacen.get().getTrabajadorId());
            if (trabajadorAlmacen.isPresent()) {
                trabajadorAlmacenService.delete(trabajadorAlmacen.get().getTrabajadorId());
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< eliminar: " + trabajadorAlmacen.toString());
        return new ResponseEntity<>(trabajadorAlmacen.get(), HttpStatus.OK);
    }
}
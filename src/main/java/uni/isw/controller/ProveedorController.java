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
import uni.isw.model.Proveedor;
import uni.isw.service.ProveedorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/proveedor")
public class ProveedorController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProveedorService proveedorService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proveedor>> getProveedores() {
        logger.info("> listar");

        List<Proveedor> listaProveedores = null;
        try {
            listaProveedores = proveedorService.getProveedores();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< listar");

        return new ResponseEntity<>(listaProveedores, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedor> getProveedor(@RequestBody Optional<Proveedor> proveedor) {
        logger.info("> search " + proveedor.toString());
        try {
            proveedor = proveedorService.getProveedor(proveedor.get().getProveedorId());
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< search " + proveedor.toString());
        return new ResponseEntity<>(proveedor.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedor> agregar(@RequestBody Proveedor proveedor) {
        logger.info("> agregar: " + proveedor.toString());
        try {
            proveedorService.saveOrUpdate(proveedor);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< agregar: " + proveedor.toString());
        return new ResponseEntity<>(proveedor, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedor> actualizar(@RequestBody Proveedor proveedor) {
        logger.info("> actualizar: " + proveedor.toString());
        try {
            proveedorService.saveOrUpdate(proveedor);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< actualizar: " + proveedor.toString());
        return new ResponseEntity<>(proveedor, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedor> delete(@RequestBody Optional<Proveedor> proveedor) {
        logger.info("> eliminar: " + proveedor.toString());
        try {
            proveedor = proveedorService.getProveedor(proveedor.get().getProveedorId());
            if (proveedor.isPresent()) {
                proveedorService.delete(proveedor.get().getProveedorId());
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< eliminar: " + proveedor.toString());
        return new ResponseEntity<>(proveedor.get(), HttpStatus.OK);
    }
}

package com.example.demo.controller;

import com.example.demo.model.AdministradorEmpresa;
import com.example.demo.model.Producto;
import com.example.demo.model.Proveedor;
import com.example.demo.model.Reabastecimiento;
import com.example.demo.service.AdministradorEmpresaService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.ProveedorService;
import com.example.demo.service.ReabastecimientoService;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reabastecimiento")
public class ReabastecimientoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReabastecimientoService reabastecimientoService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProveedorService proveedorService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reabastecimiento>> getReabastecimientos() {
        logger.info("> listar");

        List<Reabastecimiento> listaReabastecimientos = null;
        try {
            listaReabastecimientos = reabastecimientoService.getReabastecimientos();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< listar");

        return new ResponseEntity<>(listaReabastecimientos, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reabastecimiento> getReabastecimiento(@RequestBody Optional<Reabastecimiento> reabastecimiento) {
        logger.info("> search " + reabastecimiento.toString());
        try {
            reabastecimiento = reabastecimientoService.getReabastecimiento(reabastecimiento.get().getIdReabastecimiento());
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< search " + reabastecimiento.toString());
        return new ResponseEntity<>(reabastecimiento.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reabastecimiento> agregar(@RequestBody Reabastecimiento reabastecimiento) {
        logger.info("> agregar: " + reabastecimiento.toString());
        try {
            Optional<Proveedor> provOpt = proveedorService.getProveedor(reabastecimiento.getProveedor().getIdProveedor());
            Optional<Producto> prodOpt = productoService.getProducto(reabastecimiento.getProducto().getNombreProducto());
            if (!provOpt.isPresent() || !prodOpt.isPresent()){
                logger.error("Se necesita un proveedor y producto existente para solicitar un Reabastecimiento: " + reabastecimiento.getProveedor().getIdProveedor());
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            reabastecimientoService.saveOrUpdate(reabastecimiento);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< agregar: " + reabastecimiento.toString());
        return new ResponseEntity<>(reabastecimiento, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reabastecimiento> actualizar(@RequestBody Reabastecimiento reabastecimiento) {
        logger.info("> actualizar: " + reabastecimiento.toString());
        try {
            reabastecimientoService.saveOrUpdate(reabastecimiento);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< actualizar: " + reabastecimiento.toString());
        return new ResponseEntity<>(reabastecimiento, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reabastecimiento> delete(@RequestBody Optional<Reabastecimiento> reabastecimiento) {
        logger.info("> eliminar: " + reabastecimiento.toString());
        try {
            reabastecimiento = reabastecimientoService.getReabastecimiento(reabastecimiento.get().getIdReabastecimiento());
            if (reabastecimiento.isPresent()) {
                reabastecimientoService.delete(reabastecimiento.get().getIdReabastecimiento());
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< eliminar: " + reabastecimiento.toString());
        return new ResponseEntity<>(reabastecimiento.get(), HttpStatus.OK);
    }
}

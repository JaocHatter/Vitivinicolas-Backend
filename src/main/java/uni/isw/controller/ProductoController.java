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
import uni.isw.model.Producto;
import uni.isw.service.ProductoService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductoService productoService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Producto>> getProductos() {
        logger.info("> listar");

        List<Producto> listaProductos = null;
        try {
            listaProductos = productoService.getProductos();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< listar");

        return new ResponseEntity<>(listaProductos, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> getProducto(@RequestBody Optional<Producto> producto) {
        logger.info("> search " + producto.toString());
        try {
            producto = productoService.getProducto(producto.get().getNombreProducto());
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< search " + producto.toString());
        return new ResponseEntity<>(producto.get(), HttpStatus.OK);
    }
}

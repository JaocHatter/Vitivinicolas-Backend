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
import uni.isw.model.Pedido;
import uni.isw.service.PedidoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/pedido")
public class PedidoController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pedido>> getPedidos() {
        logger.info("> listar");

        List<Pedido> listaPedidos = null;
        try {
            listaPedidos = pedidoService.getPedidos();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< listar");

        return new ResponseEntity<>(listaPedidos, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pedido> getPedido(@RequestBody Optional<Pedido> pedido) {
        logger.info("> search " + pedido.toString());
        try {
            pedido = pedidoService.getPedido(pedido.get().getIdPedido());
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< search " + pedido.toString());
        return new ResponseEntity<>(pedido.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pedido> agregar(@RequestBody Pedido pedido) {
        logger.info("> agregar: " + pedido.toString());
        try {
            pedidoService.saveOrUpdate(pedido);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< agregar: " + pedido.toString());
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pedido> actualizar(@RequestBody Pedido pedido) {
        logger.info("> actualizar: " + pedido.toString());
        try {
            pedidoService.saveOrUpdate(pedido);
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< actualizar: " + pedido.toString());
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pedido> delete(@RequestBody Optional<Pedido> pedido) {
        logger.info("> eliminar: " + pedido.toString());
        try {
            pedido = pedidoService.getPedido(pedido.get().getIdPedido());
            if (pedido.isPresent()) {
                pedidoService.delete(pedido.get().getIdPedido());
            }
        } catch (Exception e) {
            logger.error("Unexpected Exception caught. " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< eliminar: " + pedido.toString());
        return new ResponseEntity<>(pedido.get(), HttpStatus.OK);
    }
}

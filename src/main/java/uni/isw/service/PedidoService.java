package uni.isw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.Pedido;
import uni.isw.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    public List<Pedido> getPedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedido(Long id) {
        return pedidoRepository.findById(id);
    }

    public void saveOrUpdate(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}

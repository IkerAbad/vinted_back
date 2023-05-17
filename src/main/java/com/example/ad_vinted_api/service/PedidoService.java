package com.example.ad_vinted_api.service;

import com.example.ad_vinted_api.domain.Pedido;
import com.example.ad_vinted_api.repository.PedidoRepository;
import com.example.ad_vinted_api.repository.ProductoRepository;
import com.example.ad_vinted_api.repository.UsuarioRepository;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService extends BaseService<Pedido, Long, PedidoRepository>{
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProductoRepository productoRepository;

    public List<Pedido> findAllBynSeguimiento(String nseguimiento){
        List<Pedido> pedidos= (List<Pedido>)
                pedidoRepository.findAllBynSeguimiento(nseguimiento);
        return pedidos;
    }

    public void deleteById(Long id){
        if (findById(id).isPresent()){
            pedidoRepository.deleteById(id);
        }else {
            System.out.println("El pedido no existe");
        }
    }
    public Pedido addPedido(Pedido newPedido,Long producto_id, Long user_id) {
        Pedido pedido = null;
        if (usuarioRepository.findById(user_id).isPresent() && productoRepository.findById(producto_id).isPresent()) {
            pedido = newPedido;
            pedido.setProducto(productoRepository.findById(producto_id).get());
            pedido.setUsuario(usuarioRepository.findById(user_id).get());
            pedido = newPedido;
            pedidoRepository.save(pedido);
        }
        return pedido;
    }
    public Pedido updatePedidoById(Pedido newPedido){
        Optional<Pedido> pedido = null;
        try {
            pedido= pedidoRepository.findById(newPedido.getId());
            if (pedido.isPresent()) {
                pedidoRepository.save(newPedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedido.get();
    }
    public Page<Pedido> getPedido(Pageable pageable, @SearchSpec
    Specification<Pedido> specs){
        return pedidoRepository.findAll(specs, pageable);
    }
}

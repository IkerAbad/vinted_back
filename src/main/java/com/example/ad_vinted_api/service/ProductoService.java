package com.example.ad_vinted_api.service;

import com.example.ad_vinted_api.domain.Pedido;
import com.example.ad_vinted_api.domain.Producto;
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
public class ProductoService extends BaseService<Producto, Long, ProductoRepository> {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Producto> findAllByEstadoProducto(String estado){
        List<Producto> productos= (List<Producto>)
                productoRepository.findAllByEstadoProducto(estado);
        return productos;
    }

    public Producto addProducto(Producto newProducto, Long user_id) {
        Producto producto = null;
        if (usuarioRepository.findById(user_id).isPresent()) {
            producto = newProducto;
            producto.setUsuario(usuarioRepository.findById(user_id).get());
            producto = newProducto;
            productoRepository.save(producto);
        }
        return producto;
    }

    public Producto updateProductoById(Producto newProducto, Long id){
        Optional<Producto> producto = null;
        try {
            producto= productoRepository.findById(newProducto.getId());
            if (producto.isPresent()) {
                productoRepository.save(newProducto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto.get();
    }
    public Page<Producto> getProducto(Pageable pageable, @SearchSpec
    Specification<Producto> specs){
        return productoRepository.findAll(specs, pageable);
    }

}

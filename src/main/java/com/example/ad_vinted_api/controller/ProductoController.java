package com.example.ad_vinted_api.controller;
import com.example.ad_vinted_api.domain.Pedido;
import com.example.ad_vinted_api.domain.Producto;
import com.example.ad_vinted_api.domain.Usuario;
import com.example.ad_vinted_api.payload.request.ProductoDTO;
import org.modelmapper.ModelMapper;
import com.example.ad_vinted_api.payload.response.Response;
import com.example.ad_vinted_api.service.ProductoService;
import com.example.ad_vinted_api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping(path = "/producto")
    public ResponseEntity<Page<Producto>>
    listaProductos(@PageableDefault(size = 10, page = 0) Pageable pageable,
                   @SearchSpec Specification<Producto> specs) {
        //logger.info("inicio listar peticiones paginadas");
        Page<Producto> peticiones =
                productoService.getProducto(pageable, specs);
        if (pageable.getPageNumber() > peticiones.getTotalPages()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //logger.info("fin listar peticiones paginadas");
        return new ResponseEntity<>(peticiones, HttpStatus.OK);
    }

    @PostMapping(path = "/producto/create/{user_id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('ANONYMOUS')")
    public ResponseEntity<Producto> addProducto(@RequestBody Producto producto, @PathVariable Long user_id) {
        System.out.println(producto);
        productoService.save(producto);
        Usuario usuario = usuarioService.findById(user_id).get();
        List<Producto> lista = usuario.getProductoList();
        lista.add(producto);
        usuario.setProductoList(lista);
        usuarioService.save(usuario);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/producto/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('ANONYMOUS')" )
    public ResponseEntity<Response> deleteById(@PathVariable Long id) {
        productoService.deleteById(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @GetMapping("/producto/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('ANONYMOUS')")
    public ResponseEntity<Producto> findById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findById(id);
        return new ResponseEntity<>(producto.get(), HttpStatus.OK);
    }

    @PutMapping("/producto/edit/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('ANONYMOUS')")
    public ResponseEntity<Producto> updateProductoById(@RequestBody Producto newProducto, @PathVariable Long id) {
        Producto producto = new ModelMapper().map(newProducto, Producto.class);
        productoService.updateProductoById(producto, id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }
}


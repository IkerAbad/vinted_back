package com.example.ad_vinted_api.controller;

import com.example.ad_vinted_api.domain.Pedido;
import com.example.ad_vinted_api.domain.Producto;
import com.example.ad_vinted_api.payload.request.PedidoDto;
import com.example.ad_vinted_api.payload.response.Response;
import com.example.ad_vinted_api.repository.PedidoRepository;
import com.example.ad_vinted_api.repository.ProductoRepository;
import com.example.ad_vinted_api.repository.UsuarioRepository;
import com.example.ad_vinted_api.service.PedidoService;
import com.example.ad_vinted_api.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.Getter;
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
@CrossOrigin(origins = "*")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        Optional<Pedido> pedido= pedidoService.findById(id);
        return new ResponseEntity<>(pedido.get(), HttpStatus.OK);
    }

    @DeleteMapping("/pedido/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> deleteById(@PathVariable  Long id){
        pedidoService.deleteById(id);
        return new ResponseEntity<>(Response.noErrorResponse(),HttpStatus.OK);
    }

    @PostMapping("/pedido/create")
    public ResponseEntity<Pedido> addPedido(@RequestParam PedidoDto pedido) {
        //logger.info("buscar usuario asociado");
        System.out.println(pedido);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

//    @PutMapping("/pedido/edit/{id}")
////    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Pedido> updateProductoById(@RequestBody PedidoDto newPedido, @PathVariable Long id){
//        Pedido pedido= new ModelMapper().map(newPedido, destinationType: Pedido.class)
//        pedido.setProducto(pedidoService.f(newPeticion.getCategoria_id()));
//        pedidoService.updatePedidoById(pedido);
//        return new ResponseEntity<>(peticion,HttpStatus.OK);
//    }


    @GetMapping("/pedido")
    public ResponseEntity<Page<Pedido>>
    listaPedidos(@PageableDefault(size = 10, page = 0) Pageable pageable,
                 @SearchSpec Specification<Pedido>specs){
        Page<Pedido> pedidos=
                pedidoService.getPedido(pageable,specs);
        if( pageable.getPageNumber() > pedidos.getTotalPages()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

}

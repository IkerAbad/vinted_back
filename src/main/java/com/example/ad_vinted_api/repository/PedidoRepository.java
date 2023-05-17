package com.example.ad_vinted_api.repository;

import com.example.ad_vinted_api.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
    @Query("SELECT p from Pedido p where p.nseguimiento=:estado")
    List<Pedido>findAllBynSeguimiento(String estado);

//    @Query("SELECT p FROM Pedido p WHERE .id=:id")
//    List<Pedido> findPedidoByUsuario(Long id);
}

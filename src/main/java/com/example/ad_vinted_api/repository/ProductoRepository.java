package com.example.ad_vinted_api.repository;

import com.example.ad_vinted_api.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface ProductoRepository extends JpaRepository<Producto, Long>,  JpaSpecificationExecutor<Producto> {
    @Query("SELECT p from Producto p where p.estado=:estado")
    List<Producto>findAllByEstadoProducto(String estado);

    @Query("SELECT p FROM Producto p WHERE p.usuario.id=:id")
    List<Producto> findProductoByUsuario(Long id);
}

package com.example.ad_vinted_api.repository;

import com.example.ad_vinted_api.domain.Oferta;
import com.example.ad_vinted_api.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface OfertaRepository extends JpaRepository<Oferta, Long> ,JpaSpecificationExecutor {
    @Query("SELECT o from Oferta o where o.precio_oferta=:estado")
    List<Producto> findAllByPrecio_oferta(String estado);

    @Query("SELECT o FROM Oferta o WHERE o.usuario.id=:id")
    List<Producto> findOfertaByUsuario(Long id);
}

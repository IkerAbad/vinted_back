package com.example.ad_vinted_api.repository;

import com.example.ad_vinted_api.domain.RolUsuario;
import com.example.ad_vinted_api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RolUsuario name);
}

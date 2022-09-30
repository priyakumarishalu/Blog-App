package com.priya.repository;

import com.priya.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Roles,Long> {
    Optional<Roles> findByName(String name);
}

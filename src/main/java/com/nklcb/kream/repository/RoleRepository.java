package com.nklcb.kream.repository;

import com.nklcb.kream.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

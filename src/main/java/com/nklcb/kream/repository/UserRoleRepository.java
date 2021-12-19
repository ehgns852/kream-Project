package com.nklcb.kream.repository;

import com.nklcb.kream.entity.security.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

}

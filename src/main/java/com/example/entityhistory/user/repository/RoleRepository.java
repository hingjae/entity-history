package com.example.entityhistory.user.repository;

import com.example.entityhistory.user.entity.Role;
import com.example.entityhistory.user.entity.RoleType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Cacheable("roles")
    Role findByRoleType(RoleType roleType);
}

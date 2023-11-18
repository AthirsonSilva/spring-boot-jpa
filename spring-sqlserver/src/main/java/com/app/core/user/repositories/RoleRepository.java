package com.app.core.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.core.user.models.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}

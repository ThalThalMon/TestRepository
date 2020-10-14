package com.ideanet.admin.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.admin.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{
   Role findByName(String name);
}

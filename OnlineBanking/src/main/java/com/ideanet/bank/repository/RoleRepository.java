package com.ideanet.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.ideanet.bank.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{
   Role findByName(String name);
}

package org.springboot.blog.api.repository;

import org.springboot.blog.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);
}

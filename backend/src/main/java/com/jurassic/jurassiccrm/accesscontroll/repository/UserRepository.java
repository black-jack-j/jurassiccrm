package com.jurassic.jurassiccrm.accesscontroll.repository;

import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Set<User> findUsersByRolesName(String roleName);

}
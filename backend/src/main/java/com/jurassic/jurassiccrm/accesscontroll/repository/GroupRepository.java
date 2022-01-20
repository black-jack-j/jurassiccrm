package com.jurassic.jurassiccrm.accesscontroll.repository;

import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long>, CrudRepository<Group, Long> {

    Optional<Group> findByName(String name);

    boolean existsByName(String name);
}

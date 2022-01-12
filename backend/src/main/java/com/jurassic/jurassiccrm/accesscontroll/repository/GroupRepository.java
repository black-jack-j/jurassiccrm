package com.jurassic.jurassiccrm.accesscontroll.repository;

import com.jurassic.jurassiccrm.accesscontroll.model.Group;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, CrudRepository<Group, Long> {

    Optional<Group> findByName(String name);
    List<Group> findByUsersContains(User user);
}

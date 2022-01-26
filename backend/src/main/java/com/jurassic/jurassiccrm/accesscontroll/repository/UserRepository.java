package com.jurassic.jurassiccrm.accesscontroll.repository;

import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.accesscontroll.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("select u from User u inner join u.groups g inner join g.roles r where r in (:roles) group by u having count(distinct r) = (:size)")
    List<User> findUsersByRoles(@Param("roles") Set<Role> roles, @Param("size") long size);
}

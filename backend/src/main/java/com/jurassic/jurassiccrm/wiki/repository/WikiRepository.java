package com.jurassic.jurassiccrm.wiki.repository;

import com.jurassic.jurassiccrm.wiki.entity.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface WikiRepository extends JpaRepository<Wiki, Long>, CrudRepository<Wiki,Long> {

    Wiki findByTitle(String title);

    Wiki deleteByTitle(String title);
}

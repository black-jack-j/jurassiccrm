package com.jurassic.jurassiccrm.accesscontroll.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.Privilege;
import com.jurassic.jurassiccrm.accesscontroll.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Transactional
    public Privilege getOrCreatePrivilege(String name) {
        Optional<Privilege> privilegeSearchResult = privilegeRepository.findByName(name);

        return privilegeSearchResult.orElse(privilegeRepository.save(new Privilege(name)));
    }

}

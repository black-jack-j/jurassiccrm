package com.jurassic.jurassiccrm.accesscontroll.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.Privilege;
import com.jurassic.jurassiccrm.accesscontroll.entity.Resource;
import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.repository.PrivilegeRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.ResourceRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private PrivilegeService privilegeService;

    @Transactional
    public Role getOrCreateRole(String name, Collection<String> privileges) {
        Optional<Role> roleSearchResult = roleRepository.findByName(name);

        Collection<Privilege> savedPrivileges = privileges.stream()
                .map(privilegeService::getOrCreatePrivilege).collect(Collectors.toList());

        return roleSearchResult.orElse(roleRepository.save(new Role(name, savedPrivileges)));
    }

    public Role getBasicRole(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("cant find basic role with name '"+name+"' "));
    }

    @Transactional
    public Role addPrivilegeToRole(Long privilegeId, Long roleId) {
        Role targetRole = getRoleByIdOrThrowException(roleId);

        Privilege privilege = privilegeRepository.findById(privilegeId).orElseThrow(
                () -> new IllegalArgumentException("No Privilege with id '"+privilegeId+"'"));

        if (targetRole.addPrivilege(privilege)) {
            return roleRepository.save(targetRole);
        } else {
            return targetRole;
        }
    }

    @Transactional
    public Role addResourceToRole(Long resourceId, Long roleId) {
        Role targetRole = getRoleByIdOrThrowException(roleId);

        Resource resource = resourceRepository.findById(resourceId).orElseThrow(
                () -> new IllegalArgumentException("No Privilege with id '"+resourceId+"'"));
        targetRole.addResource(resource);
        return roleRepository.save(targetRole);
    }

    private Role getRoleByIdOrThrowException(Long roleId) {
        Optional<Role> roleSearchResult = roleRepository.findById(roleId);
        return roleSearchResult.orElseThrow(
                () -> new IllegalArgumentException("No Role with id '"+roleId+"'"));
    }

}

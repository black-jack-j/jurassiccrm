package com.jurassic.jurassiccrm.accesscontroll.service;

import com.jurassic.jurassiccrm.accesscontroll.entity.Group;
import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.GroupRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.RoleRepository;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void createGroup(Group group){
        groupRepository.save(group);
    }

    public List<User> getAvailableUsers(){
        return userRepository.findAll();
    }

    public List<Role> getAvailableRoles(){
        return roleRepository.findAll();
    }

}

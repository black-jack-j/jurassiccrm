package com.jurassic.jurassiccrm.group.controller;

import com.jurassic.jurassiccrm.accesscontroll.entity.Group;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.GroupService;
import com.jurassic.jurassiccrm.group.dto.CreateGroupDTO;
import com.jurassic.jurassiccrm.group.dto.SelectedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GroupController {

    private final String createGroupDtoKey = "createGroupDTO";

    private final Group group = new Group();

    @Autowired
    GroupService groupService;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/group/getUsers")
    public String getAvailableUsers(final CreateGroupDTO createGroupDTO, Model model) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        List<User> users = groupService.getAvailableUsers();
        users = users.stream()
                .filter(user -> !dto.getUsers().contains(user))
                .collect(Collectors.toList());
        model.addAttribute("availableUsers", users);
        model.addAttribute("addingUser", true);
        model.addAttribute("selectedUser", new SelectedUser());
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @GetMapping("/group/create")
    public String getCreateGroupForm(final CreateGroupDTO createGroupDTO, Model model) {
        CreateGroupDTO dto = new CreateGroupDTO();
        if (createGroupDTO != null)
            dto = createGroupDTO;
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @PostMapping(value = "/group/addUser")
    public String addUser(final CreateGroupDTO createGroupDTO, final SelectedUser selectedUser, Model model) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        String username = selectedUser.getUsername();
        List<User> users = dto.getUsers();
        User user = userRepository.findByUsername(username).orElse(null);
        users.add(user);
        dto.setUsers(users);
        syncDto(dto);
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @PostMapping(value = "/group/cancelAddUser")
    public String cancelAddUser(final CreateGroupDTO createGroupDTO, Model model) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }


    @PostMapping(value = "/group/users", params = {"removeUser"})
    public String removeUser(final CreateGroupDTO createGroupDTO, Model model, HttpServletRequest req) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        String username = req.getParameter("removeUser");
        List<User> users = dto.getUsers();
        users = users.stream()
                .filter(user -> !user.getUsername().equals(username))
                .collect(Collectors.toList());
        dto.setUsers(users);
        syncDto(dto);
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    private CreateGroupDTO syncDto(final CreateGroupDTO createGroupDTO) {
        if (createGroupDTO.getName() == null) createGroupDTO.setName(group.getName());
        else group.setName(createGroupDTO.getName());
        if (createGroupDTO.getUsers().isEmpty()) createGroupDTO.setUsers(new ArrayList<>(group.getUsers()));
        else group.setUsers(new HashSet<>(createGroupDTO.getUsers()));
        if (createGroupDTO.getRoles().isEmpty()) createGroupDTO.setRoles(new ArrayList<>(group.getRoles()));
        else group.setRoles(new HashSet<>(createGroupDTO.getRoles()));
        return createGroupDTO;
    }
}

package com.jurassic.jurassiccrm.group.controller;

import com.jurassic.jurassiccrm.accesscontroll.entity.Group;
import com.jurassic.jurassiccrm.accesscontroll.entity.Role;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.accesscontroll.repository.UserRepository;
import com.jurassic.jurassiccrm.accesscontroll.service.GroupService;
import com.jurassic.jurassiccrm.group.dto.CreateGroupDTO;
import com.jurassic.jurassiccrm.group.dto.SelectedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/group")
public class GroupController {

    private final String createGroupDtoKey = "createGroupDTO";

    private Group group = new Group();

    private final GroupService groupService;
    private final UserRepository userRepository;

    @Autowired
    public GroupController(GroupService groupService, UserRepository userRepository) {
        this.groupService = groupService;
        this.userRepository = userRepository;
    }

    @GetMapping("/create")
    public String getCreateGroupForm(final CreateGroupDTO createGroupDTO, Model model) {
        CreateGroupDTO dto = new CreateGroupDTO();
        if (createGroupDTO != null)
            dto = createGroupDTO;
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @PostMapping(value = "/getUsers")
    public String getAvailableUsers(final CreateGroupDTO createGroupDTO, Model model) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        List<User> users = groupService.getAvailableUsers();
        users = users.stream()
                .filter(user -> !dto.getUsers().contains(user))
                .collect(Collectors.toList());
        model.addAttribute("availableUsers", users);
        model.addAttribute("addingUser", true);
        model.addAttribute("selectedUser", new SelectedEntity());
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @PostMapping(value = "/addUser")
    public String addUser(final CreateGroupDTO createGroupDTO, final SelectedEntity selectedUser, Model model) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        String username = selectedUser.getValue();
        List<User> users = dto.getUsers();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            users.add(user);
            dto.setUsers(users);
            syncDto(dto);
        }
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @PostMapping(value = "/group/cancelAdd")
    public String cancelAddUser(final CreateGroupDTO createGroupDTO, Model model) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }


    @PostMapping(value = "/users", params = {"removeUser"})
    public String removeUser(final CreateGroupDTO createGroupDTO, Model model, HttpServletRequest req) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        String username = req.getParameter("removeUser");
        List<User> users = dto.getUsers();
        users = users.stream()
                .filter(user -> !user.getUsername().equals(username))
                .collect(Collectors.toList());
        dto.setUsers(users);
        group.setUsers(new HashSet<>(users));
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @PostMapping(value = "/getRoles")
    public String getAvailableRoles(final CreateGroupDTO createGroupDTO, Model model) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        List<Role> roles = groupService.getAvailableRoles();
        roles = roles.stream()
                .filter(role -> !dto.getRoles().contains(role))
                .collect(Collectors.toList());
        model.addAttribute("availableRoles", roles);
        model.addAttribute("addingRole", true);
        model.addAttribute("selectedRole", new SelectedEntity());
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @PostMapping(value = "/addRole")
    public String addRole(final CreateGroupDTO createGroupDTO, final SelectedEntity selectedRole, Model model) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        String selectedRoleName = selectedRole.getValue();
        Role role = Role.getByName(selectedRoleName).orElse(null);
        if (role != null) {
            List<Role> roles = dto.getRoles();
            roles.add(role);
            dto.setRoles(roles);
            syncDto(dto);
        }
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @PostMapping(value = "/roles", params = {"removeRole"})
    public String removeRole(final CreateGroupDTO createGroupDTO, Model model, HttpServletRequest req) {
        CreateGroupDTO dto = syncDto(createGroupDTO);
        String roleName = req.getParameter("removeRole");
        List<Role> roles = dto.getRoles();
        roles = roles.stream()
                .filter(role -> !role.name().equals(roleName))
                .collect(Collectors.toList());
        dto.setRoles(roles);
        group.setRoles(new HashSet<>(roles));
        model.addAttribute(createGroupDtoKey, dto);
        return "/group/create";
    }

    @PostMapping
    public String saveGroup(final @Valid CreateGroupDTO createGroupDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/group/create";
        }
        syncDto(createGroupDTO);
        if (!groupService.groupWithNameExists(group.getName())) {
            groupService.createGroup(group);
            group = new Group();
            model.addAttribute("createdSuccessfully", true);
        } else {
            model.addAttribute("duplicatedName", true);
        }
        model.addAttribute(createGroupDtoKey, syncDto(new CreateGroupDTO()));
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

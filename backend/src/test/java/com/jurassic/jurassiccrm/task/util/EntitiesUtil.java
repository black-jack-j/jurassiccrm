package com.jurassic.jurassiccrm.task.util;

import com.jurassic.jurassiccrm.accesscontroll.model.User;
import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.accesscontroll.model.Role;
import com.jurassic.jurassiccrm.aviary.model.AviaryType;
import com.jurassic.jurassiccrm.dinosaur.model.DinosaurType;
import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.model.Task;
import com.jurassic.jurassiccrm.task.model.TaskType;
import com.jurassic.jurassiccrm.task.model.aviary.CreateAviaryTask;
import com.jurassic.jurassiccrm.task.model.incubation.IncubationTask;
import com.jurassic.jurassiccrm.task.model.research.ResearchTask;
import com.jurassic.jurassiccrm.task.priority.model.TaskPriority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class EntitiesUtil {

    public static User getUser(String name, String password) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);

        return user;
    }

    public static Task getTask(String name, TaskType taskType) {
        Task task;
        switch (taskType) {
            case AVIARY_CREATION: {
                task = new CreateAviaryTask();
                break;
            }
            case INCUBATION: {
                task = new IncubationTask();
                break;
            }
            case RESEARCH: {
                task = new ResearchTask();
                break;
            }
            default: {
                throw new IllegalStateException("Unknown TaskType observed");
            }
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        task.setCreated(timestamp);
        task.setLastUpdated(timestamp);

        task.setName(name);
        return task;
    }

    public static TaskTO getTaskTO(String name, TaskType taskType) {
        return TaskTO.builder()
                .additionalParams(new HashMap<>())
                .name(name)
                .taskType(taskType).build();
    }

    public static TaskPriority getTaskPriority(String name, Long priorityValue) {
        TaskPriority priority = new TaskPriority();
        priority.setName(name);
        priority.setAbsolutePriority(priorityValue);

        return priority;
    }

    public static AviaryType getAviaryType(String name) {
        AviaryType aviaryType = new AviaryType();
        aviaryType.setName(name);

        return aviaryType;
    }

    public static DinosaurType getDinosaurType(String name) {
        DinosaurType dinosaurType = new DinosaurType();
        dinosaurType.setName(name);

        return dinosaurType;
    }

    public static UserDetails getUserDetails(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        GrantedAuthority authority = new SimpleGrantedAuthority(role.roleName());
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(authority);
        return new JurassicUserDetails(user, authorities);
    }
}

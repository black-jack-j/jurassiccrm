package com.jurassic.jurassiccrm.task.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.Resource;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import com.jurassic.jurassiccrm.document.entity.Document;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
public class Task extends Resource {

    @Id
    @GeneratedValue
    private Long id;

    private String summary;

    private String description;

    private Date dueDate;

    @ManyToOne
    private User assignee;
}

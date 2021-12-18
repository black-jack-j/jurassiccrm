package com.jurassic.jurassiccrm.document.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.Resource;
import com.jurassic.jurassiccrm.accesscontroll.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@DiscriminatorColumn
public class Document extends Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name = "test";

    private String type = "TestType";

    private String contentType = "text/plain";

    private String description;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    private long size;

    private Timestamp created;

    private Timestamp lastUpdate;

    @Lob
    private byte[] content;

}

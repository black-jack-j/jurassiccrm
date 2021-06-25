package com.jurassic.jurassiccrm.document.entity;

import com.jurassic.jurassiccrm.accesscontroll.entity.Resource;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@DiscriminatorColumn
public class Document extends Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name = "test";

    private String contentType = "text/plain";

    private String description;

    private String author;

    private long size;

    @Lob
    private byte[] content;

}

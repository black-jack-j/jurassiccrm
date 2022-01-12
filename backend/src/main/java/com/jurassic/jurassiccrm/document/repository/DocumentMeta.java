package com.jurassic.jurassiccrm.document.repository;

import com.jurassic.jurassiccrm.accesscontroll.model.User;

import java.sql.Timestamp;

public interface DocumentMeta {

    Long getId();

    String getName();

    String getType();

    String getContentType();

    String getDescription();

    User getAuthor();

    long getSize();

    Timestamp getCreated();

    Timestamp getLastUpdate();
}

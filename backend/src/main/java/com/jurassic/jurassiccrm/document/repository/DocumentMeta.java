package com.jurassic.jurassiccrm.document.repository;

public interface DocumentMeta {

    Long getId();

    String getName();

    String getType();

    String getContentType();

    String getDescription();

    String getAuthor();

    long getSize();

}

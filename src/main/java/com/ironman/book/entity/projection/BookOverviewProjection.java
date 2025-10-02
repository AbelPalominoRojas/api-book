package com.ironman.book.entity.projection;

public interface BookOverviewProjection {

    Integer getId();

    String getIsbn();

    String getTitle();

    String getAuthors();

    String getEdition();

    Integer getPublicationYear();

    Integer getPublisherId();

    String getPublisherCode();

    String getPublisherName();

}

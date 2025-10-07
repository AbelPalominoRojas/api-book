package com.ironman.book.entity.emuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


// Lombok and other imports can be added as needed
@RequiredArgsConstructor
@Getter
public enum BookSortField {
    ID("id", "id"),
    ISBN("isbn", "isbn"),
    TITLE("title", "title"),
    AUTHORS("authors", "authors"),
    EDITION("edition", "edition"),
    PUBLICATION_YEAR("publicationYear", "publication_year"),
    PUBLISHER_ID("publisherId", "publisher_id");

    private final String fieldName;
    private final String columnName;

    public static String getColumnName(String value){
        return Arrays.stream(values())
                .filter(e -> e.getFieldName().equals(value))
                .findFirst()
                .map(BookSortField::getColumnName)
                .orElse(ID.getColumnName());
    }

    public static String getFieldName(String value){
        return Arrays.stream(values())
                .filter(e -> e.getFieldName().equals(value))
                .findFirst()
                .map(BookSortField::getFieldName)
                .orElse(ID.getFieldName());
    }

}

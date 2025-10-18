package com.ironman.book.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Constant {

    public static final String PUBLISHER_NOT_FOUND_BY_ID = "Publisher not found with id: ";
    public static final String PUBLISHER_CODE_EXISTS = "Publisher code already exists: ";



    public static final String DATABASE_UNEXPECTED_ERROR = "An unexpected error occurred in the database service.";
    public static final String UNEXPECTED_ERROR = "An unexpected error occurred, please try again later.";
}

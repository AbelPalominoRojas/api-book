package com.ironman.book.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Lombok annotation
@Getter
@RequiredArgsConstructor
public enum LoanReturnedEnum {
    NOT_RETURNED(0),
    RETURNED(1);

    private final int value;
}

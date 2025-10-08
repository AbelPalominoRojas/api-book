package com.ironman.book.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Lombok annotation
@Getter
@RequiredArgsConstructor
public enum LoanStatusEnum {

    ACTIVE_LOAN(0),
    RETURNED(1),
    PARTIALLY_RETURNED(2);

    private final int value;

}

package com.ironman.book.exception;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.dao.DataIntegrityViolationException;

public class ExceptionManager {
    private ExceptionManager() {
    }

    public static AppBaseException handleException(Throwable ex) {
        if (
                ex instanceof DataIntegrityViolationException
                        || ex instanceof SQLServerException
        ) {
            return new UnexpectedException("An unexpected error occurred in the database service.");
        } else if (ex instanceof AppBaseException) {
            return (AppBaseException) ex;
        } else {
            return new UnexpectedException("An unexpected error occurred, please try again later.");
        }
    }
}

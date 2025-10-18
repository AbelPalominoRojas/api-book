package com.ironman.book.exception;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.dao.DataIntegrityViolationException;

import static com.ironman.book.util.Constant.DATABASE_UNEXPECTED_ERROR;
import static com.ironman.book.util.Constant.UNEXPECTED_ERROR;

public class ExceptionManager {
    private ExceptionManager() {
    }

    public static AppBaseException handleException(Throwable ex) {
        if (
                ex instanceof DataIntegrityViolationException
                        || ex instanceof SQLServerException
        ) {
            return new DatabaseUnexpectedException(DATABASE_UNEXPECTED_ERROR);
        } else if (ex instanceof AppBaseException) {
            return (AppBaseException) ex;
        } else {
            return new UnexpectedException(UNEXPECTED_ERROR);
        }
    }
}

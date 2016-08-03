package com.trs.exception;

/**
 * OM系统本身抛出的所有异常的超类。
 */
public class TRSOMException extends RuntimeException {

    public TRSOMException(String message, Throwable cause) {
        super(message, cause);
    }

    public TRSOMException(String message) {
        super(message);
    }

    public TRSOMException(Throwable cause) {
        super(cause);
    }

    public TRSOMException() {
        super();
    }

}

package biz.uoray.cucp.exception;

public class CucpSystemException extends RuntimeException {

    public CucpSystemException() {
        this("errors.SystemError");
    }

    public CucpSystemException(String msg) {
        super(msg);
    }
}

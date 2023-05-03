package cn.lishiyuan.jaria2.exception;

public class Aria2ActionException extends Exception{
    public Aria2ActionException() {
        super();
    }

    public Aria2ActionException(String message) {
        super(message);
    }

    public Aria2ActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public Aria2ActionException(Throwable cause) {
        super(cause);
    }

    protected Aria2ActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

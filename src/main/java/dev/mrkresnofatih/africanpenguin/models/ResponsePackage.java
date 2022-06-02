package dev.mrkresnofatih.africanpenguin.models;

public class ResponsePackage<T> {
    public ResponsePackage(T data, String errorCode) {
        Data = data;
        ErrorCode = errorCode;
    }

    private T Data;

    private String ErrorCode;

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }
}


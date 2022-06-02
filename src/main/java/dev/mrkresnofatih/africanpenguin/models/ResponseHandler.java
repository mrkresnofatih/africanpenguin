package dev.mrkresnofatih.africanpenguin.models;

public class ResponseHandler<T> {
    public ResponsePackage<T> WrapSuccess(T data) {
        return new ResponsePackage<>(data, null);
    }

    public ResponsePackage<T> WrapFailure(String error) {
        return new ResponsePackage<>(null, error);
    }
}

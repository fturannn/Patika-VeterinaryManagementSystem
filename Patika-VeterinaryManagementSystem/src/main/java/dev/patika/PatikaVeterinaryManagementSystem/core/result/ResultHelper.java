package dev.patika.PatikaVeterinaryManagementSystem.core.result;

import dev.patika.PatikaVeterinaryManagementSystem.core.utilies.Message;

public class ResultHelper {
    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Message.CREATED,"201",data);
    }
    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Message.OK,"200",data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, Message.VALIDATE_ERROR, "400", data);
    }

    public static Result notFoundError(String message) {
        return new Result(false, message, "404");
    }

    public static Result duplicationError(String message) {
        return new Result(false, message, "500");
    }
    public static Result vaccinationError(String message) {
        return new Result(false, message, "500");
    }
    public static Result hourTypeError(String message) {
        return new Result(false, message, "400");
    }
    public static Result doctorAppointmentError(String message) {
        return new Result(false, message, "500");
    }
}

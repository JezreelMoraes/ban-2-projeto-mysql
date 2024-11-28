package com.gymesc.infrastructure.exception;

public class ExceptionHelper {

    public static BusinessException userNotFound() {
        return new BusinessException("exception.message.user.not.found");
    }

    public static BusinessException invalidEmail() {
        return new BusinessException("exception.message.invalid.email");
    }

    public static BusinessException emailAlreadyExist() {
        return new BusinessException("exception.message.email.already.exists");
    }

    public static BusinessException createUserInvalid() {
        return new BusinessException("exception.message.create.user.invalid");
    }

    public static BusinessException workoutNotFound() {
        return new BusinessException("exception.message.workout.not.found");
    }

    public static ForbiddenException unauthorizedAction() {
        return new ForbiddenException("exception.message.unauthorized.action");
    }

    public static BusinessException exerciseNotFound() {
        return new BusinessException("exception.message.exercise.not.found");
    }
}

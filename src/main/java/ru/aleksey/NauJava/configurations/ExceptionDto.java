package ru.aleksey.NauJava.configurations;

public class ExceptionDto {
    private String message;
    private ExceptionDto(String message){
        this.message = message;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public static ExceptionDto create(Throwable e)
    {
        return new ExceptionDto(e.getMessage());
    }
    public static ExceptionDto create(String message)
    {
        return new ExceptionDto(message);
    }
}

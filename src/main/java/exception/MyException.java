package exception;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class MyException extends RuntimeException {
    private String exceptionMessage;
    private LocalDateTime exceptionDateTime;

    public String getMessage(){
        return "EXCEPTION: " + exceptionDateTime + " : " + exceptionMessage;
    }
}

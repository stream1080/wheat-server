package com.example.wheat.exception;

import com.example.wheat.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import static com.example.wheat.enums.ResponseEnum.ERROR;
import static com.example.wheat.enums.ResponseEnum.NEED_LOGIN;

@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseVo handle(RuntimeException e){
        return ResponseVo.error(ERROR,e.getMessage());
    }


    @ExceptionHandler(UserLoginException.class)
    @ResponseBody
    public ResponseVo userLoginException(){
        return ResponseVo.error(NEED_LOGIN);
    }
}

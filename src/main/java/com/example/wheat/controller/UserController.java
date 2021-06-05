package com.example.wheat.controller;


import com.example.wheat.entity.User;
import com.example.wheat.form.UserForm;
import com.example.wheat.service.UserService;
import com.example.wheat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.wheat.enums.ResponseEnum.PARAM_ERROR;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/register")
    public Object register(@Valid @RequestBody UserForm userForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("注册参数有误,{} {}",
                    bindingResult.getFieldError().getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(PARAM_ERROR,bindingResult);
        }

        User user = new User();

        BeanUtils.copyProperties(userForm, user);

        log.info("username:", userForm.getUsername());
        return userService.register(user);
    }
}


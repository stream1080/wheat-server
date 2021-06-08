package com.example.wheat.controller;


import com.example.wheat.conts.WheatConst;
import com.example.wheat.entity.User;
import com.example.wheat.form.UserLoginForm;
import com.example.wheat.form.UserRegisterForm;
import com.example.wheat.service.UserService;
import com.example.wheat.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.example.wheat.enums.ResponseEnum.NEED_LOGIN;
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
@Api(tags = "用户模块API")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/register")
    @ApiOperation(value = "用户注册")
    public Object register(@Valid @RequestBody UserRegisterForm userRegisterForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("注册参数有误,{} {}",
                    bindingResult.getFieldError().getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(PARAM_ERROR,bindingResult);
        }

        User user = new User();

        BeanUtils.copyProperties(userRegisterForm, user);

        log.info("username:", userRegisterForm.getUsername());
        return userService.register(user);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpSession session){
        if (bindingResult.hasErrors()){
            log.error("注册参数有误,{} {}",
                    bindingResult.getFieldError().getField(),
                    bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(PARAM_ERROR,bindingResult);
        }

        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(),userLoginForm.getPassword());

        //设置Session
        session.setAttribute(WheatConst.CURRENT_USER,userResponseVo.getData());
        log.info("login sessionId= {}",session.getId());

        return userResponseVo;
    }


    //改进版：token+redis分布式session
    //cookies 跨越
    @GetMapping("/getuser")
    @ApiOperation(value = "获取用户信息")
    public ResponseVo<User> userInfo(HttpSession session)  {
        log.info("getuser sessionId= {}",session.getId());
        User user = (User)session.getAttribute(WheatConst.CURRENT_USER);
        if (user == null) {
            return ResponseVo.error(NEED_LOGIN);
        }
        return ResponseVo.success(user);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录")
    public ResponseVo<User> logout(HttpSession session)  {
        log.info("getuser sessionId= {}",session.getId());
        User user = (User)session.getAttribute(WheatConst.CURRENT_USER);
        if (user == null) {
            return ResponseVo.error(NEED_LOGIN);
        }
        session.removeAttribute(WheatConst.CURRENT_USER);
        return ResponseVo.success();
    }

}


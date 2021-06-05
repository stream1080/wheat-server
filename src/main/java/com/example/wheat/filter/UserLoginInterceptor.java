package com.example.wheat.filter;


import com.example.wheat.conts.WheatConst;
import com.example.wheat.entity.User;
import com.example.wheat.exception.UserLoginException;
import com.example.wheat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.wheat.enums.ResponseEnum.NEED_LOGIN;

@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * true表示继续，false中断
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("getuser sessionId= {}");
        User user = (User)request.getSession().getAttribute(WheatConst.CURRENT_USER);
        if (user == null) {
            log.info("user = null");
            throw new UserLoginException();
//            return false;
        }
        return true;
    }

}

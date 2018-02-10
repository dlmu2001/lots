package com.lucky.lots.common;

import com.lucky.lots.anotation.AuthToken;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if(!handler.getClass().isAssignableFrom(HandlerMethod.class)){
            return true;
        }
        AuthToken authToken = ((HandlerMethod)handler).getMethod().getAnnotation(AuthToken.class);
        if(authToken==null){
            return true;
        }
        String token = httpServletRequest.getHeader("token");
        if(!TokenManager.isValidToken(token)){
            httpServletResponse.setStatus(401);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

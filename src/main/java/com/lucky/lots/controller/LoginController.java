package com.lucky.lots.controller;

import com.lucky.lots.service.LoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @ApiOperation(value = "用户登录",notes = "调用wx.login返回一个code以后，向后端发起登录请求，后端维护登录态",response = String.class)
    @ApiImplicitParam(name ="jscode" ,value="前端调用wx.login返回的js_code",paramType = "query",dataType = "String")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@ApiParam String jscode){
        return loginService.login(jscode);
    }
}

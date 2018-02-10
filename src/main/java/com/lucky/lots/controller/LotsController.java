package com.lucky.lots.controller;

import com.lucky.lots.anotation.AuthToken;
import com.lucky.lots.common.TokenManager;
import com.lucky.lots.entity.Lot;
import com.lucky.lots.entity.UserInfoParam;
import com.lucky.lots.entity.WxUserInfo;
import com.lucky.lots.service.LoginService;
import com.lucky.lots.service.LotsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LotsController {
    @Autowired
    private LotsService lotsService;
    @Autowired
    private LoginService loginService;
    @ApiOperation(value = "创建一个抓阄",notes = "新建一个抓阄，返回抓阄对应结构体")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "创建者的token", required = true, paramType = "header",dataType = "String"),
            @ApiImplicitParam(name = "title", value = "创建的抓阄的主题", required = true, paramType = "query",dataType="String"),
            @ApiImplicitParam(name="totalNum",value="创建的抓阄的参与人数",required = true,paramType = "query",dataType="int"),
            @ApiImplicitParam(name="requiredCheckNum",value="选中人数",required = true,paramType = "query",dataType="int")
    })
    @RequestMapping(value="/lots",method = RequestMethod.POST)
    @AuthToken
    public Lot creatLot(@RequestHeader("token") String token,
                         @RequestParam("title") String title,
                         @RequestParam("totalNum") int totalNum,
                         @RequestParam("requiredCheckNum") int requiredCheckNum){
        return lotsService.creatLot(token,title,totalNum,requiredCheckNum);
    }
    @ApiOperation(value = "获取某个用户参与的抓阄列表",notes = "创建或者抓阄都算")
    @ApiImplicitParam(name="token",value="用户的token", paramType = "header",dataType="String")
    @RequestMapping(value = "/mylots}",method = RequestMethod.GET)
    public List<Lot> getLotList(@RequestHeader("token") String token){
        return null;
    }
    @ApiOperation(value = "获取某个用户创建的所有抓阄",notes="只包含创建的，不包含参与的")
    @ApiImplicitParam(name="token",value="用户的token", paramType = "header",dataType="String")
    @RequestMapping(value = "/mycreatedlots",method = RequestMethod.GET)
    public List<Lot> getCreatLotList(@RequestHeader("token") String token){
        return lotsService.getCreatLotList(token);
    }
    @ApiOperation(value = "获取某个抓阄的信息",notes = "获取某个抓阄的信息，仅获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户的token", paramType = "header",dataType="String"),
            @ApiImplicitParam(name="id",value="抓阄的id", paramType = "path",dataType="int")
    })
    @RequestMapping(value = "/lots/{id}",method = RequestMethod.GET)
    public Lot getLotById(@PathVariable Long id,@RequestHeader("token") String token){
        return null;
    }
    @ApiOperation(value = "抓阄",notes = "如果用户没有抓过，进行抓阄，抓过不再重新抓，返回该抓阄的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户的token", paramType = "header",dataType="String"),
            @ApiImplicitParam(name="id",value="抓阄的id", paramType = "path",dataType="int"),
            @ApiImplicitParam(name = "userInfoParam",value = "用户信息",paramType = "body",dataType="UserInfoParam")
    })
    @RequestMapping(value = "/lots/{id}",method = RequestMethod.POST)
    public Lot drawLot(@PathVariable Long id, @RequestHeader("token") String token,
                       @RequestBody UserInfoParam userInfoParam){
        long userId = TokenManager.parseJWTToken(token);
        if(userId==-1){
            return null;
        }
        loginService.updateUser(userId,userInfoParam);
        return lotsService.drawLot(id,userId,userInfoParam);
    }
    @ApiOperation(value = "删除某个抓阄",notes = "只有创建者有权限删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name="token",value="用户的token", paramType = "header",dataType="String"),
            @ApiImplicitParam(name="id",value="抓阄的id", paramType = "path",dataType="int")
    })
    @RequestMapping(value="/lots/{id}",method = RequestMethod.DELETE)
    public String deleteLot(@PathVariable Long id,@RequestHeader("token") String token){
        return "OK";
    }
}

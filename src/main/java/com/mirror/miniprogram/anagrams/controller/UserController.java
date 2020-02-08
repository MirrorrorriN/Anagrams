package com.mirror.miniprogram.anagrams.controller;

import com.mirror.miniprogram.anagrams.common.json.JsonViewFactory;
import com.mirror.miniprogram.anagrams.domain.RiddleDTO;
import com.mirror.miniprogram.anagrams.domain.UserDTO;
import com.mirror.miniprogram.anagrams.pojo.RiddleUser;
import com.mirror.miniprogram.anagrams.service.http.Response.WxOpenIdResponse;
import com.mirror.miniprogram.anagrams.service.http.WxLoginService;
import com.mirror.miniprogram.anagrams.service.impl.RiddleUserMapperService;
import com.mirror.miniprogram.anagrams.service.impl.RiddleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("anagrams/spring/user")
@RestController
@Api(tags = "主页 Api")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Successful — 请求已完成，服务器成功返回网页"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器拒绝请求"), @ApiResponse(code = 404, message = "服务器找不到请求的资源，文档不存在"),
        @ApiResponse(code = 500, message = "服务器遇到错误，无法完成请求"), @ApiResponse(code = 503, message = "服务器目前无法使用（由于超载或停机维护）")})
public class UserController {

    @Autowired
    WxLoginService wxLoginService;
    @Autowired
    RiddleUserMapperService riddleUserMapperService;
    @Autowired
    RiddleUserService riddleUserService;

    @RequestMapping(method = RequestMethod.GET, value = "/getOpenId")
    public String getOpenId(String code) throws Exception {
        WxOpenIdResponse response=wxLoginService.getOpenId(code);
        if(response.getOpenid()!=null){
            return JsonViewFactory.create()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .put("data", response.getOpenid())
                    .toJson();
        }
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/correctCount")
    public String singleRiddle(@RequestParam("openid") String openid) throws Exception {
        UserDTO userDTO = new UserDTO();

        RiddleUser riddleUser = riddleUserService.getByOpenid(openid);
        userDTO.setCount(riddleUser.getCorrectCount());
        userDTO.setScore(riddleUser.getScore());

        return JsonViewFactory.create()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .put("data", userDTO)
                .toJson();
    }

}

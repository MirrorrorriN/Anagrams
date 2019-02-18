package com.mirror.miniprogram.anagrams.controller;

import com.mirror.miniprogram.anagrams.common.json.JsonViewFactory;
import com.mirror.miniprogram.anagrams.domain.RiddleDTO;
import com.mirror.miniprogram.anagrams.pojo.RiddleBaseInfo;
import com.mirror.miniprogram.anagrams.pojo.RiddleUser;
import com.mirror.miniprogram.anagrams.pojo.RiddleUserMapper;
import com.mirror.miniprogram.anagrams.service.impl.RiddleService;
import com.mirror.miniprogram.anagrams.service.impl.RiddleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("dingjing/spring/riddle")
@RestController
@Api(tags = "汉谜相关 Api")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Successful — 请求已完成，服务器成功返回网页"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器拒绝请求"), @ApiResponse(code = 404, message = "服务器找不到请求的资源，文档不存在"),
        @ApiResponse(code = 500, message = "服务器遇到错误，无法完成请求"), @ApiResponse(code = 503, message = "服务器目前无法使用（由于超载或停机维护）")})
public class RiddleController {

    @Autowired
    RiddleService riddleService;

    @Autowired
    RiddleUserService riddleUserService;

    @RequestMapping(method = RequestMethod.GET, value = "/single")
    public String singleRiddle(@RequestParam("openid") String openid) throws Exception {
        RiddleBaseInfo riddleBaseInfo = riddleService.getSingleRiddle(openid);
        riddleUserService.fetchRiddleSync(riddleBaseInfo, openid);
        RiddleDTO riddleDTO = new RiddleDTO();
        riddleDTO.setId(riddleBaseInfo.getId());
        riddleDTO.setQuestion(riddleBaseInfo.getRiddleQuestion());
        riddleDTO.setAnswer(riddleBaseInfo.getRiddleAnswer());
        riddleDTO.setHint(riddleBaseInfo.getRiddleHint());
        riddleDTO.setExplanation(riddleBaseInfo.getRiddleExplanation());
        return JsonViewFactory.create()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .put("data", riddleDTO)
                .toJson();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public String verifyAnswer(@RequestParam("answer") String answer,
                               @RequestParam("riddleId") Long riddleId, @RequestParam("openid") String openid) {
        RiddleBaseInfo riddleBaseInfo = riddleService.getById(riddleId);
        //TODO 异常前端如何同步处理
        if (riddleBaseInfo == null) {
            return JsonViewFactory.create().fail("该条谜语不存在，可能已被删除，请重新进入").toJson();
        }
        String rightAnswer = riddleBaseInfo.getRiddleAnswer();
        if (answer.equals(rightAnswer)) {
            riddleUserService.verifyRiddleSync(riddleBaseInfo, openid, true);
            return JsonViewFactory.create()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .put("data", true)
                    .toJson();
        }
        riddleUserService.verifyRiddleSync(riddleBaseInfo, openid, false);
        return JsonViewFactory.create()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .put("data", false)
                .toJson();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/queryAnswer")
    public String queryAnswer(@RequestParam("riddleId") Long riddleId, @RequestParam("openid") String openid) throws Exception {

        RiddleBaseInfo riddleBaseInfo = riddleService.getById(riddleId);
        if (riddleBaseInfo == null) {
            return JsonViewFactory.create().fail("该谜语不存在 \r\n 请刷新").toJson();
        }

        RiddleUser riddleUser = riddleUserService.getByOpenid(openid);
        if (riddleUser == null) {
            return JsonViewFactory.create().fail("用户不存在  \r\n 请退出重新登录").toJson();
        }
        if (riddleUser.getScore() < 10) {
            return JsonViewFactory.create().fail("积分不够用啦 \r\n 继续加油哦").toJson();
        }
        riddleUserService.queryAnswerSync(riddleBaseInfo, riddleUser);
        RiddleDTO riddleDTO = new RiddleDTO();
        riddleDTO.setAnswer(riddleBaseInfo.getRiddleAnswer());
        riddleDTO.setExplanation(riddleBaseInfo.getRiddleExplanation());
        return JsonViewFactory.create()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .put("data", riddleDTO)
                .toJson();
    }

}

package com.mirror.miniprogram.anagrams.controller;

import com.mirror.miniprogram.anagrams.common.json.JsonViewFactory;
import com.mirror.miniprogram.anagrams.domain.RiddleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("spring/riddle")
@RestController
@Api(tags = "汉谜相关 Api")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Successful — 请求已完成，服务器成功返回网页"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"), @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器拒绝请求"), @ApiResponse(code = 404, message = "服务器找不到请求的资源，文档不存在"),
        @ApiResponse(code = 500, message = "服务器遇到错误，无法完成请求"), @ApiResponse(code = 503, message = "服务器目前无法使用（由于超载或停机维护）")})
public class RiddleController {

    @RequestMapping(method = RequestMethod.GET, value = "/single")
    public String singleRiddle() throws Exception {
        RiddleDTO riddleDTO=new RiddleDTO();
        riddleDTO.setQuestion("日出北京城");
        riddleDTO.setHint("（打一字）");
        riddleDTO.setAnswer("景");
        riddleDTO.setExplanation("上北下南，日在京的上方，即为景");
        return JsonViewFactory.create()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .put("data", riddleDTO)
                .toJson();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public String verifyAnswer(@RequestParam("answer") String answer){
        String rightAnswer="景";
        if(answer.equals(rightAnswer)){
            return JsonViewFactory.create()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .put("data", true)
                    .toJson();
        }
        return JsonViewFactory.create()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .put("data", false)
                .toJson();
    }

}

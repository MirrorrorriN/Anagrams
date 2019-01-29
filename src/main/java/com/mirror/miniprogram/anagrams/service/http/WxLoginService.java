package com.mirror.miniprogram.anagrams.service.http;

import com.google.common.net.HttpHeaders;
import com.mirror.miniprogram.anagrams.service.http.Response.HttpBaseResponse;
import com.mirror.miniprogram.anagrams.service.http.Response.WxOpenIdResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WxLoginService {

    private static final Log logger=LogFactory.getLog(WxLoginService.class);

    @Autowired
    private HttpService httpService;

    @Value("${wx.get.openid.url:https://api.weixin.qq.com/sns/jscode2session}")
    private String WxOpenIdUrl;
    @Value("${anagrams.app.secret:8b79493e6f100dc58819c9fa9a83cf18}")
    private String AppSecret;
    @Value("${anagrams.appid:wxcdf378b6b7fb87d8}")
    private String AppID;

    public WxOpenIdResponse getOpenId(String code) {
        Map<String, String> headersMap = new HashMap<>();
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("appid", "" + AppID);
        paramsMap.put("secret", AppSecret);
        paramsMap.put("js_code", code);
        headersMap.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<WxOpenIdResponse> responseEntity = this.httpService.exchangeForGet(WxOpenIdUrl, headersMap, paramsMap, new ParameterizedTypeReference<WxOpenIdResponse>() {
        });
        if (responseEntity != null) {
            WxOpenIdResponse response = responseEntity.getBody();
            logger.info("wx openId request:" + response.toString());
            return response;
        }
        return new WxOpenIdResponse();
    }

}

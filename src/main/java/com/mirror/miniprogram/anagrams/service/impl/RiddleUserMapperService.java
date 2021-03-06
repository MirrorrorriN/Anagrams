package com.mirror.miniprogram.anagrams.service.impl;

import com.mirror.miniprogram.anagrams.mapper.one.RiddleUserMapperMapper;
import com.mirror.miniprogram.anagrams.pojo.RiddleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiddleUserMapperService {

    @Autowired
    RiddleUserMapperMapper riddleUserMapperMapper;

    public int countCorectViaOpenid(String openid){
        int count=riddleUserMapperMapper.selectCountByUserOpenid(openid);
        return count;
    }
}

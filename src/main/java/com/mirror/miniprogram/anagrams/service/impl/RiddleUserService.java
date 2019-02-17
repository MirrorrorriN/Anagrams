package com.mirror.miniprogram.anagrams.service.impl;

import com.mirror.miniprogram.anagrams.mapper.one.RiddleBaseInfoMapper;
import com.mirror.miniprogram.anagrams.mapper.one.RiddleUserMapper;
import com.mirror.miniprogram.anagrams.mapper.one.RiddleUserMapperMapper;
import com.mirror.miniprogram.anagrams.pojo.RiddleBaseInfo;
import com.mirror.miniprogram.anagrams.pojo.RiddleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RiddleUserService {

    @Autowired
    RiddleUserMapper riddleUserMapper;

    @Autowired
    RiddleBaseInfoMapper riddleBaseInfoMapper;

    @Autowired
    RiddleUserMapperMapper riddleUserMapperMapper;

    public RiddleUser getByOpenid(String openid){
        RiddleUser riddleUser=riddleUserMapper.selectByOpenid(openid);
        return riddleUser;
    }

    public void fetchRiddleSync(String openid){
        RiddleUser riddleUser=riddleUserMapper.selectByOpenid(openid);
        if(riddleUser==null){
            RiddleUser newRiddleUser=new RiddleUser();
            newRiddleUser.setOpenid(openid);
            newRiddleUser.setQueryCount(1L);
            riddleUserMapper.insertSelective(newRiddleUser);
        }else{
            riddleUser.setQueryCount(riddleUser.getCorrectCount()+1);
            riddleUserMapper.updateByPrimaryKey(riddleUser);
        }
    }

    @Transactional
    public void verifyRiddleSync(RiddleBaseInfo riddleBaseInfo, String openid, boolean flag){
        //TODO riddleUser为空处理
        RiddleUser riddleUser=riddleUserMapper.selectByOpenid(openid);
        if(flag){
            riddleBaseInfo.setSolvedCount(riddleBaseInfo.getSolvedCount()+1);
            riddleBaseInfoMapper.updateByPrimaryKey(riddleBaseInfo);
            riddleUser.setCorrectCount(riddleUser.getCorrectCount()+1);
            riddleUserMapper.updateByPrimaryKey(riddleUser);
            com.mirror.miniprogram.anagrams.pojo.RiddleUserMapper riddleUserMapper=new com.mirror.miniprogram.anagrams.pojo.RiddleUserMapper();
            riddleUserMapper.setRiddleId(riddleBaseInfo.getId());
            riddleUserMapper.setUserOpenid(openid);
            riddleUserMapperMapper.insertSelective(riddleUserMapper);
        }else{
            riddleUser.setIncorrectCount(riddleUser.getIncorrectCount()+1);
            riddleUserMapper.updateByPrimaryKey(riddleUser);
        }
    }
}

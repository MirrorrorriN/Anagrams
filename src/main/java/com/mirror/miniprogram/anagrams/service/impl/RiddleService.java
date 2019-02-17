package com.mirror.miniprogram.anagrams.service.impl;

import com.mirror.miniprogram.anagrams.mapper.one.RiddleBaseInfoMapper;
import com.mirror.miniprogram.anagrams.mapper.one.RiddleUserMapperMapper;
import com.mirror.miniprogram.anagrams.pojo.RiddleBaseInfo;
import com.mirror.miniprogram.anagrams.pojo.RiddleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RiddleService {

    @Autowired
    RiddleBaseInfoMapper riddleBaseInfoMapper;
    @Autowired
    RiddleUserMapperMapper riddleUserMapperMapper;

    //TODO 随机待优化
    @Transactional
    public RiddleBaseInfo getSingleRiddle(String openid){
        long total=riddleBaseInfoMapper.selectCountAll();
        long correctTotal=riddleUserMapperMapper.selectCountByUserOpenid(openid);
        long remTotal=total-correctTotal;
        long randomIndex=(long)(Math.random()*remTotal);
//        按顺序取
//        RiddleUserMapper riddleUserMapper=riddleUserMapperMapper.selectLatestRiddle(openid);
//        Long riddleId=0L;
//        if(riddleUserMapper!=null){
//            riddleId=riddleUserMapper.getRiddleId();
//        }
//        //TODO null处理 Set?
//        RiddleBaseInfo riddleBaseInfo=riddleBaseInfoMapper.selectRiddleAfterId(riddleId);
        List<Long> totalIdList=riddleBaseInfoMapper.selectAll();
        List<Long> finishedRiddleIdList=riddleBaseInfoMapper.selectUnfinishedWithOpenid(openid);
        totalIdList.remove(finishedRiddleIdList);
        if(randomIndex>=totalIdList.size()){
            randomIndex=totalIdList.get(totalIdList.size()-1);
        }
        //TODO 越界问题及海量数据问题（未来）
        RiddleBaseInfo riddleBaseInfo=riddleBaseInfoMapper.selectByPrimaryKey(totalIdList.get((int)randomIndex));

        return riddleBaseInfo;
    }

    public RiddleBaseInfo getById(Long id){
        return riddleBaseInfoMapper.selectByPrimaryKey(id);
    }
}

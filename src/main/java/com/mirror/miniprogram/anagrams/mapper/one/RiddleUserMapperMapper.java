package com.mirror.miniprogram.anagrams.mapper.one;

import com.mirror.miniprogram.anagrams.pojo.RiddleUserMapper;
import org.apache.ibatis.annotations.Param;

public interface RiddleUserMapperMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RiddleUserMapper record);

    int insertSelective(RiddleUserMapper record);

    RiddleUserMapper selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RiddleUserMapper record);

    int updateByPrimaryKey(RiddleUserMapper record);

    int selectCountByUserOpenid(@Param("openid") String openid);

    RiddleUserMapper selectLatestRiddle(@Param("openid") String openid);
}
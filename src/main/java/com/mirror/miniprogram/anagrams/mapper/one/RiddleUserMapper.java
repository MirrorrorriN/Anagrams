package com.mirror.miniprogram.anagrams.mapper.one;

import com.mirror.miniprogram.anagrams.pojo.RiddleUser;
import org.apache.ibatis.annotations.Param;

public interface RiddleUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RiddleUser record);

    int insertSelective(RiddleUser record);

    RiddleUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RiddleUser record);

    int updateByPrimaryKey(RiddleUser record);

    RiddleUser selectByOpenid(@Param("openid") String openid);
}
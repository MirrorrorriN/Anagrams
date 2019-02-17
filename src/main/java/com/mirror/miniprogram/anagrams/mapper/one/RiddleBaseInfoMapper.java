package com.mirror.miniprogram.anagrams.mapper.one;

import com.mirror.miniprogram.anagrams.pojo.RiddleBaseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiddleBaseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RiddleBaseInfo record);

    int insertSelective(RiddleBaseInfo record);

    RiddleBaseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RiddleBaseInfo record);

    int updateByPrimaryKey(RiddleBaseInfo record);

    RiddleBaseInfo selectRiddleAfterId(@Param("latestRiddleId") Long latestRiddleId);

    List<Long> selectUnfinishedWithOpenid(@Param("openid") String openid);

    List<Long> selectAll();

    long selectCountAll();
}
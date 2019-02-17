package com.mirror.miniprogram.anagrams.pojo;

import org.apache.ibatis.annotations.Param;

public class RiddleUserMapper {
    private Long id;

    private Long riddleId;

    private String userOpenid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRiddleId() {
        return riddleId;
    }

    public void setRiddleId(Long riddleId) {
        this.riddleId = riddleId;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

}
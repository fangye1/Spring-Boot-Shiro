package com.springboot.service;

import com.springboot.domain.vo.UserOnlineVo;

import java.util.List;

public interface SessionService {
    /**
     * 获取当前session用户集合
     */
    List<UserOnlineVo> list();

    /**
     * session过期
     */
    boolean forceLogout(String sessionId);
}

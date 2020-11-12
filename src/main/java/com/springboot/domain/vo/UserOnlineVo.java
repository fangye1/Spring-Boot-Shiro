package com.springboot.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserOnlineVo implements Serializable {
    private static final long serialVersionUID = 3828664348416633856L;
    /**
     * session id
     */
    private String sessionId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String realName;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户主机地址
     */
    private String host;
    /**
     * 用户登录时系统IP
     */
    private String systemHost;
    /**
     * 用户状态
     */
    private String userStatusStr;
    /**
     * session创建时间
     */
    private Date sessionStartTime;
    /**
     * session最后访问时间
     */
    private Date sessionLastAccessTime;
    /**
     * 超时时间
     */
    private Long timeout;
}

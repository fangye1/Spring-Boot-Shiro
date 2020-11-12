package com.springboot.service.impl;

import com.springboot.domain.pojo.User;
import com.springboot.domain.vo.UserOnlineVo;
import com.springboot.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    @Resource
    private SessionDAO sessionDAO;

    @Override
    public List<UserOnlineVo> list() {
        List<UserOnlineVo> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            UserOnlineVo userOnlineVo = new UserOnlineVo();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            }
            SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
                    .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            User user = (User) principalCollection.getPrimaryPrincipal();
            userOnlineVo.setUsername(user.getName());
            userOnlineVo.setRealName(user.getRealName());
            userOnlineVo.setUserId(user.getId().toString());
            userOnlineVo.setSessionId((String) session.getId());
            userOnlineVo.setHost(session.getHost());
            userOnlineVo.setSessionStartTime(session.getStartTimestamp());
            userOnlineVo.setSessionLastAccessTime(session.getLastAccessTime());
            Long timeout = session.getTimeout();
            if (timeout == 0L) {
                userOnlineVo.setUserStatusStr("离线");
            } else {
                userOnlineVo.setUserStatusStr("在线");
            }
            userOnlineVo.setTimeout(timeout);
            list.add(userOnlineVo);
        }
        return list;
    }

    @Override
    public boolean forceLogout(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        sessionDAO.delete(session);
        return true;
    }
}
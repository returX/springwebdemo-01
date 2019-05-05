package main.java.com.service;

import main.java.com.dao.impl.LoginLogDao;
import main.java.com.dao.impl.UserDao;
import main.java.com.domain.LoginLog;
import main.java.com.domain.User;
import main.java.com.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public LoginLogDao getLoginLogDao() {
        return loginLogDao;
    }
    @Autowired
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    @Override
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    @Override
    public void loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insetLoginLog(loginLog);
    }
}

package main.java.com.dao;

import main.java.com.dao.impl.LoginLogDao;
import main.java.com.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDaoImpl implements LoginLogDao {

    private JdbcTemplate jdbcTemplate;
    private final static String INSERT_LOGIN_LOG_SQL = "insert into " +
            " t_login_log(user_id,ip,login_datetime) values(?,?,?)";

    @Autowired
    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insetLoginLog(LoginLog loginLog) {
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,
                new Object[]{loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()});

    }
}

package main.java.com.dao.impl;

import main.java.com.domain.LoginLog;
import org.springframework.jdbc.core.JdbcTemplate;

public interface LoginLogDao {

    void insetLoginLog(LoginLog loginLog);

    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}

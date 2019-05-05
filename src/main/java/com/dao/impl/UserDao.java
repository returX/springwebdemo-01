package main.java.com.dao.impl;


import main.java.com.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

public interface UserDao {
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);

    int getMatchCount(String userName, String password);

    User findUserByUserName(final String userName);
    void updateLoginInfo(User user);



}

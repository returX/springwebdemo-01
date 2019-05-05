package main.java.com.dao;

import main.java.com.dao.impl.UserDao;
import main.java.com.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDapImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL = "select count(*) from "+
            " t_user where user_name = ? and password = ?";

    private final static String UPDATE_LOGIN_INTO_SQL = "update t_user set "+
            " last_visit = ?,last_ip = ?,credits = ? where user_id = ?";

    @Override
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getMatchCount(String userName, String password) {
        int matchCount = 0;
        matchCount = jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,password},Integer.class);
        return matchCount;
    }

    @Override
    public User findUserByUserName(String userName) {
        final User user = new User();
        jdbcTemplate.query(MATCH_COUNT_SQL, new Object[]{userName},
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(rs.getInt("credits"));
                    }
                });
        return user;
    }

    @Override
    public void updateLoginInfo(User user) {

        jdbcTemplate.update(UPDATE_LOGIN_INTO_SQL,new Object[]{user.getLastVisit(),
                user.getLastIp(),user.getCredits(),user.getUserId()});
    }
}

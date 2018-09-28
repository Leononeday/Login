package com.hello.model;

import com.hello.domain.User;
import com.hello.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserModel {


    public static User login(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        User existUser = queryRunner.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class), user.getUsername(),user.getPassword());
        return existUser;
    }
}

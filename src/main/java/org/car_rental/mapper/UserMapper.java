package org.car_rental.mapper;

import org.car_rental.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMapper implements IMapper<User>{

    private final String ID= "id";
    private final String USERNAME= "username";
    private final String PASSWORD= "password";


    @Override
    public List<User> ResultSetToList(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public User ResultSetToObject(ResultSet rs) throws SQLException {

        if(rs.next()) {
            return User.builder()
                    .Id(rs.getInt(ID))
                    .username(rs.getString(USERNAME))
                    .password(rs.getString(PASSWORD))
                    .build();
        }
        return null;
    }
}

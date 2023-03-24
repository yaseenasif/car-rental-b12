package org.car_rental.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IMapper <T>{

    List<T> ResultSetToList(ResultSet rs) throws SQLException;
    T ResultSetToObject(ResultSet rs) throws SQLException;

}

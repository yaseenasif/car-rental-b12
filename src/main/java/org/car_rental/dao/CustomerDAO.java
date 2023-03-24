package org.car_rental.dao;

import org.car_rental.domain.Customer;
import org.car_rental.mapper.CustomerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class CustomerDAO extends BaseDAO implements ICrud<Customer>{

    CustomerMapper customerMapper=new CustomerMapper();

    @Override
    public List<Customer> getAll() {
        try {
            Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_CUSTOMER);
            return customerMapper.ResultSetToList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Customer getById(Long id) {
        try {
            PreparedStatement ps=conn.prepareStatement(SqlQueryConstant.GET_CUSTOMER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs=ps.executeQuery();
            return customerMapper.ResultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Customer obj) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.INSERT_CUSTOMER);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getPhoneNumber());
            ps.setString(3, obj.getCnic());
            ps.setString(4, obj.getAddress());
            ps.setString(5, obj.getReferencePhoneNumber());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.DELETE_CUSTOMER_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Customer obj , Long id) {

        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.UPDATE_CUSTOMER_BY_ID);
            ps.setString(1,obj.getName());
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.car_rental.dao;

import org.car_rental.domain.Booking;
import org.car_rental.mapper.BookingMapper;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BookingDAO extends BaseDAO implements ICrud<Booking>{
    BookingMapper bookingMapper = new BookingMapper();
    @Override
    public List<Booking> getAll() {
        try {
            Statement stmt= conn.createStatement();
            ResultSet rs=stmt.executeQuery(SqlQueryConstant.GET_ALL_BOOKING);
            return bookingMapper.ResultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBookingComplete(Long id , Date date){
        try {
            PreparedStatement ps = conn.prepareStatement("update booking set status = 'Complete' , complete_date = ? where id = ? ");
            ps.setDate(1,date);
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Booking getById(Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.GET_BOOKING_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return bookingMapper.ResultSetToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Booking obj) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.INSERT_BOOKING);
            ps.setString(1,obj.getCustomer());
            ps.setString(2,obj.getVehicle());
            ps.setDate(3, obj.getBookingDate());
            ps.setFloat(4,obj.getAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.DELETE_BOOKING_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Booking obj, Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.UPDATE_BOOKING_BY_ID);
            ps.setString(1,obj.getCustomer());
            ps.setString(2,obj.getVehicle());
            ps.setDate(3,obj.getBookingDate());
            ps.setFloat(4,obj.getAmount());
            ps.setInt(5,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getByDate(Date date) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.GET_BY_DATE);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            return bookingMapper.ResultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Booking>  getByDateRange(Date startDate , Date endDate){
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.GET_BY_DATE_RANGE);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs=ps.executeQuery();
            return bookingMapper.ResultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Booking> getCommissionAndAmount(Date startDate , Date endDate){
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_COMMISSION_AND_AMOUNT);
            ps.setDate(1,startDate);
            ps.setDate(2,endDate);
            ResultSet rs = ps.executeQuery();
            return bookingMapper.ResultSetToListOfCommissionAndAmount(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBookingInactive(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("update booking set status = 'Inactive' where id = ? ");
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

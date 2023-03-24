package org.car_rental.dao;

import org.car_rental.domain.Booking;
import org.car_rental.mapper.BookingMapper;

import java.sql.*;
import java.util.List;

public class BookingDAO extends BaseDAO implements ICrud<Booking>{
    BookingMapper bookingMapper=new BookingMapper();
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
            ps.setLong(1,obj.getCustomerId());
            ps.setLong(2,obj.getVehicleId());
            ps.setDate(3, (Date) obj.getBookingDate());
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
            ps.setFloat(1,obj.getAmount());
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.car_rental.dao;

import org.car_rental.domain.Vehicle;
import org.car_rental.mapper.CustomerMapper;
import org.car_rental.mapper.VehicleMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class VehicleDAO extends BaseDAO implements ICrud<Vehicle>{
    VehicleMapper vehicleMapper=new VehicleMapper();
    @Override
    public List<Vehicle> getAll() {
        try {
            Statement stmt= conn.createStatement();
            ResultSet rs=stmt.executeQuery(SqlQueryConstant.GET_ALL_VEHICLE);
            return vehicleMapper.ResultSetToList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Vehicle getById(Long id) {

        try {
            PreparedStatement ps=conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs=ps.executeQuery();
            return vehicleMapper.ResultSetToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void insert(Vehicle obj) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.INSERT_VEHICLE);
            ps.setString(1, obj.getVehicleName());
            ps.setString(2, obj.getColor());
            ps.setLong(3,obj.getYear());
            ps.setString(3, obj.getBrand());
            ps.setLong(4, obj.getOwnerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.DELETE_VEHICLE_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Vehicle obj, Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.UPDATE_VEHICLE_BY_ID);
            ps.setString(1,obj.getVehicleName());
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

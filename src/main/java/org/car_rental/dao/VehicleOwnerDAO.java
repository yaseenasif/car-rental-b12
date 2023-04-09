package org.car_rental.dao;

import org.car_rental.domain.VehicleOwner;
import org.car_rental.mapper.VehicleOwnerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class VehicleOwnerDAO extends BaseDAO implements ICrud<VehicleOwner>{
    VehicleOwnerMapper vehicleOwnerMapper=new VehicleOwnerMapper();
    @Override
    public List<VehicleOwner> getAll() {
        try {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(SqlQueryConstant.GET_ALL_VEHICLE_OWNER);
            return vehicleOwnerMapper.ResultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public VehicleOwner getById(Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_OWNER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.ResultSetToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(VehicleOwner obj) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.INSERT_VEHICLE_OWNER);
            ps.setString(1, obj.getOwnerName());
            ps.setString(2, obj.getOwnerNumber());
            ps.setString(3, obj.getAddress());
            ps.setFloat(4,obj.getCommission());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.DELETE_VEHICLE_OWNER_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(VehicleOwner obj, Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.UPDATE_VEHICLE_OWNER_BY_ID);
            ps.setString(1, obj.getOwnerName());
            ps.setString(2,obj.getOwnerNumber());
            ps.setString(3,obj.getAddress());
            ps.setFloat(4,obj.getCommission());
            ps.setInt(5,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<VehicleOwner> getByName(String name) {
        try {
            PreparedStatement ps= conn.prepareStatement("select * from vehicle_owner where owner_name like '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.ResultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

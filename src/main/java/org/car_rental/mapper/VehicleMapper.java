package org.car_rental.mapper;

import org.car_rental.domain.Customer;
import org.car_rental.domain.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapper implements IMapper<Vehicle>{

    private final static String ID = "id";
    private final static String COLOR = "color";
    private final static String VEHICLENAME = "vehicle_name";
    private final static String YEAR = "v_year";
    private final static String BRAND = "brand";
    private final static String OWNERID = "owner_id";
    @Override
    public List<Vehicle> ResultSetToList(ResultSet rs) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .id(rs.getLong(ID))
                    .vehicleName(rs.getString(VEHICLENAME))
                    .color(rs.getString(COLOR))
                    .year(rs.getLong(YEAR))
                    .brand(rs.getString(BRAND))
                    .ownerId(rs.getLong(OWNERID))
                    .build();
            vehicleList.add(vehicle);
        }
return vehicleList;
    }

    @Override
    public Vehicle ResultSetToObject(ResultSet rs) throws SQLException {
        if (rs.next()){
        return Vehicle.builder()
                .id(rs.getLong(ID))
                .vehicleName(rs.getString(VEHICLENAME))
                .color(rs.getString(COLOR))
                .year(rs.getLong(YEAR))
                .brand(rs.getString(BRAND))
                .ownerId(rs.getLong(OWNERID))
                .build();
        }
        return null;
    }
}

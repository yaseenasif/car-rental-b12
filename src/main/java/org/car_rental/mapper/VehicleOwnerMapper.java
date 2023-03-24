package org.car_rental.mapper;

import org.car_rental.domain.Customer;
import org.car_rental.domain.Vehicle;
import org.car_rental.domain.VehicleOwner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleOwnerMapper implements IMapper<VehicleOwner> {
    private final static String ID = "id";
    private final static String OWNERNAME = "owner_name";
    private final static String OWNERNUMBER = "owner_number";
    private final static String ADDRESS = "address";
    private final static String COMMISSION = "commission";
    @Override
    public List<VehicleOwner> ResultSetToList(ResultSet rs) throws SQLException {

        List<VehicleOwner> vehicleOwnerList = new ArrayList<>();
        while (rs.next()) {
            VehicleOwner vehicleOwner = VehicleOwner.builder()
                    .id(rs.getLong(ID))
                    .ownerName(rs.getString(OWNERNAME))
                    .ownerNumber(rs.getString(OWNERNUMBER))
                    .address(rs.getString(ADDRESS))
                    .commission(rs.getFloat(COMMISSION))
                    .build();
            vehicleOwnerList.add(vehicleOwner);
        }
        return vehicleOwnerList;
    }

    @Override
    public VehicleOwner ResultSetToObject(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return VehicleOwner.builder()
                    .id(rs.getLong(ID))
                    .ownerName(rs.getString(OWNERNAME))
                    .ownerNumber(rs.getString(OWNERNUMBER))
                    .address(rs.getString(ADDRESS))
                    .commission(rs.getFloat(COMMISSION))
                    .build();
        }
        return null;

    }
}

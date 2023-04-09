package org.car_rental.mapper;

import org.car_rental.domain.Booking;
import org.car_rental.domain.VehicleOwner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapper implements IMapper<Booking> {

    private final static String ID = "id";
    private final static String CUSTOMERID = "c_id";
    private final static String VEHICLEID = "v_id";
    private final static String BOOKINGDATE = "booking_date";
    private final static String AMOUNT = "amount";

    @Override
    public List<Booking> ResultSetToList(ResultSet rs) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while (rs.next()) {
          Booking booking= Booking.builder()
                    .id(rs.getLong(ID))
                    .customerId(rs.getLong(CUSTOMERID))
                    .vehicleId(rs.getLong(VEHICLEID))
                    .bookingDate(rs.getDate(BOOKINGDATE))
                    .amount(rs.getFloat(AMOUNT))
                    .build();
          bookingList.add(booking);

        }
        return bookingList;
    }
    @Override
    public Booking ResultSetToObject(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Booking.builder()
                    .id(rs.getLong(ID))
                    .customerId(rs.getLong(CUSTOMERID))
                    .vehicleId(rs.getLong(VEHICLEID))
                    .bookingDate(rs.getDate(BOOKINGDATE))
                    .amount(rs.getFloat(AMOUNT))
                    .build();
        }
        return null;
    }
}

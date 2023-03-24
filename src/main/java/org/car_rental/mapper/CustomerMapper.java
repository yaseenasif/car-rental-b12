package org.car_rental.mapper;

import org.car_rental.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerMapper implements IMapper<Customer> {

    private final static String ID = "id";
    private final static String CNIC = "cnic";
    private final static String NAME = "customer_name";
    private final static String PHONENUMBER = "phone_number";
    private final static String ADDRESS = "address";
    private final static String REFERENCEPHONENUMBER = "ref_number";

    @Override
    public List<Customer> ResultSetToList(ResultSet rs) throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        while (rs.next()) {
            Customer customer = Customer.builder()
                    .id( rs.getLong(ID))
                    .name(rs.getString(NAME))
                    .phoneNumber(rs.getString(PHONENUMBER))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .referencePhoneNumber(rs.getString(REFERENCEPHONENUMBER))
                    .build();
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer ResultSetToObject(ResultSet rs) throws SQLException {

        if (rs.next()) {
            return Customer.builder()
                    .id(rs.getLong(ID))
                    .name(rs.getString(NAME))
                    .phoneNumber(rs.getString(PHONENUMBER))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .referencePhoneNumber(rs.getString(REFERENCEPHONENUMBER))
                    .build();
        }
return null;
    }
}
package org.car_rental.dao;

public class SqlQueryConstant {

    public final static String GET_ALL_CUSTOMER = "select * from customer";
    public final static String GET_ALL_VEHICLE = "select * from vehicle";
    public final static String GET_ALL_VEHICLE_OWNER = "select * from vehicle_owner";
    public final static String GET_ALL_BOOKING = "select * from booking";
    public final static String GET_CUSTOMER_BY_ID = "select * from customer where id=?";
    public final static String GET_VEHICLE_BY_ID = "select * from vehicle where id=?";
    public final static String GET_VEHICLE_OWNER_BY_ID = "select * from vehicle_owner where id=?";
    public final static String GET_BOOKING_BY_ID = "select * from booking where id=?";
    public final static String DELETE_CUSTOMER_BY_ID = "delete from customer where id =?";
    public final static String DELETE_VEHICLE_ID = "delete from vehicle where id =?";
    public final static String DELETE_VEHICLE_OWNER_ID = "delete from vehicle_owner where id =?";
    public final static String DELETE_BOOKING_ID = "delete from booking where id =?";
    public final static String UPDATE_CUSTOMER_BY_ID = "update customer set customer_name = ? where id = ? ";
    public final static String UPDATE_VEHICLE_BY_ID = "update vehicle set vehicle_name = ? where id = ? ";
    public final static String UPDATE_VEHICLE_OWNER_BY_ID = "update vehicle_owner set owner_name = ? where id = ? ";
    public final static String UPDATE_BOOKING_BY_ID = "update booking set amount = ? where id = ? ";
    public final static String INSERT_CUSTOMER = "insert into customer(customer_name,phone_number,cnic,address,ref_number)" +
            "values(?,?,?,?,?)";
    public final static String INSERT_VEHICLE = "insert into vehicle(vehicle_name,color,v_year,brand,owner_id)" +
            "values(?,?,?,?,?)";
    public final static String INSERT_VEHICLE_OWNER = "insert into vehicle_owner(owner_name,owner_number,address,commission)" +
            "values(?,?,?,?)";
    public final static String INSERT_BOOKING = "insert into booking(cid,vid,booking_date,amount)" +
            "values(?,?,?,?)";

 }

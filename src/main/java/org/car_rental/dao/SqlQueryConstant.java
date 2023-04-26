package org.car_rental.dao;

public class SqlQueryConstant {

    public final static String GET_ALL_CUSTOMER = "select * from customer where status = 'Active' ";
    public final static String GET_ALL_VEHICLE = "select * from vehicle where status = 'Active' ";
    public final static String GET_ALL_VEHICLE_OWNER = "select * from vehicle_owner where status = 'Active' ";
    public final static String GET_ALL_BOOKING = "select b.id , concat(c.id,\",\",c.customer_name) as c_id, concat(v.id,\",\",v.vehicle_name) as v_id , b.booking_date , b.complete_date , b.amount , b.status\n" +
            "from booking b\n" +
            "inner join customer c on b.c_id = c.id\n" +
            "inner join vehicle v on b.v_id = v.id\n" +
            "where b.status = 'open' or b.status = 'complete'";
    public final static String GET_BY_DATE = "select b.id , concat(c.id,\",\",c.customer_name) as c_id , concat(v.id,\",\",v.vehicle_name) as v_id , b.booking_date , b.complete_date , b.amount , b.status\n" +
            "from booking b\n" +
            "inner join customer c on b.c_id = c.id\n" +
            "inner join vehicle v on b.v_id = v.id\n" +
            "where b.booking_date = ? ";
    public final static  String GET_BY_DATE_RANGE = "select b.id , c.customer_name as c_id , v.vehicle_name as v_id , b.booking_date , b.complete_date , (DATEDIFF(b.complete_date , b.booking_date))*b.amount as amount, b.status\n" +
            "from booking b\n" +
            "inner join customer c on b.c_id = c.id\n" +
            "inner join vehicle v on b.v_id = v.id\n" +
            "where (b.booking_date Between ? And ?) AND b.status = 'Complete'";
    public final static String GET_COMMISSION_AND_AMOUNT = "select sum(DATEDIFF(b.complete_date , b.booking_date)*b.amount) as total_amount , Sum(o.commision*(DATEDIFF(b.complete_date , b.booking_date)*b.amount)/100) as total_commission  from booking b \n" +
            "    inner join vehicle v on v.id=b.v_id\n" +
            "    inner join vehicle_owner o on o.id = v.owner_id\n" +
            "    where (b.booking_date Between ? And ?) AND b.status = 'Complete' ";
    public final static String GET_CUSTOMER_BY_ID = "select * from customer where id=?";
    public final static String GET_VEHICLE_BY_ID = "select * from vehicle where id=?";
    public final static String GET_VEHICLE_OWNER_BY_ID = "select * from vehicle_owner where id=?";
    public final static String GET_BOOKING_BY_ID = "select * from booking where id=?";
    public final static String DELETE_CUSTOMER_BY_ID = "delete from customer where id =?";
    public final static String DELETE_VEHICLE_ID = "delete from vehicle where id =?";
    public final static String DELETE_VEHICLE_OWNER_ID = "delete from vehicle_owner where id =?";
    public final static String DELETE_BOOKING_ID = "delete from booking where id =?";
    public final static String UPDATE_CUSTOMER_BY_ID = "update customer set customer_name = ? , phone_number = ? , cnic = ? , address = ? ,ref_number = ? where id = ? ";
    public final static String UPDATE_VEHICLE_BY_ID = "update vehicle set vehicle_name = ? , color = ? , v_year = ? , brand = ? , owner_id = ?  where id = ? ";
    public final static String UPDATE_VEHICLE_OWNER_BY_ID = "update vehicle_owner set owner_name = ? , owner_number = ? , address = ? , commision = ? where id = ? ";
    public final static String UPDATE_BOOKING_BY_ID = "update booking set c_id = ? , v_id = ? , booking_date = ? , amount = ? where id = ? ";
    public final static String INSERT_CUSTOMER = "insert into customer(customer_name,phone_number,cnic,address,ref_number)" +
            "values(?,?,?,?,?)";
    public final static String INSERT_VEHICLE = "insert into vehicle(vehicle_name,color,v_year,brand,owner_id)" +
            "values(?,?,?,?,?)";
    public final static String INSERT_VEHICLE_OWNER = "insert into vehicle_owner(owner_name,owner_number,address,commision)" +
            "values(?,?,?,?)";
    public final static String INSERT_BOOKING = "insert into booking(c_id,v_id,booking_date,amount)" +
            "values(?,?,?,?)";

    public final static String GET_USER_BY_USERNAME_AND_PASSWORD = "select * from user where username =? AND password=?";

    public static final String GET_TOTAL_COMMISSION = "select o.id , o.owner_name , o.owner_number, o.address , sum(b.amount*o.commision/100) as commision from booking b\n" +
            "inner join vehicle v on v.id=b.v_id\n" +
            "inner join vehicle_owner o on v.owner_id = o.id\n" +
            "where (b.booking_date between ? and ?) and b.status = 'complete'\n" +
            "group by o.id, o.owner_name";

    public static final String GET_AVAILABLE_VEHICLE ="select v.id , v.vehicle_name , v.color , v.v_year ,v.brand , owner_id  from vehicle v\n" +
            "left join booking b on b.v_id = v.id\n" +
            "where (b.v_id is null or b.status = 'Complete') and v.status = 'Active'\n" +
            "group by v.id";
}

package org.car_rental.service;

import org.car_rental.dao.BookingDAO;
import org.car_rental.dao.CustomerDAO;
import org.car_rental.dao.VehicleDAO;
import org.car_rental.domain.Booking;
import org.car_rental.domain.Customer;
import org.car_rental.domain.Vehicle;

import java.sql.Date;
import java.util.List;

public class BookingService {

    BookingDAO bookingDAO=new BookingDAO();
    VehicleDAO vehicleDAO=new VehicleDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    public void delete(Long id){

        bookingDAO.deleteById(id);
    }

    public void update(String id , String customer , String vehicle , String bookingDate , String amount){

        Booking booking = Booking.builder()
                .customer(customer)
                .vehicle(vehicle)
                .bookingDate(Date.valueOf(bookingDate))
                .amount(Float.valueOf(amount))
                .build();

        bookingDAO.update(booking, Long.valueOf(id));
    }


    public String[][] getByDateRange(Date startDate , Date endDate){

        List<Booking> bookingList = bookingDAO.getByDateRange(startDate,endDate);
        return transformToJTable(bookingList,7);
    }

    public List<Booking> getCommissionAndAmount(Date startDate , Date endDate){
        return bookingDAO.getCommissionAndAmount(startDate,endDate);
    }

    public String[][] getAllForJTable() {
        List<Booking> bookingList = bookingDAO.getAll();
        return transformToJTable(bookingList,7);
    }

    public String[][] searchByDate(Date date){
        List<Booking> bookingList = bookingDAO.getByDate(date);
       return transformToJTable(bookingList,7);
    }

    public String[][] transformToJTable(List<Booking> bookingList , Integer columnSize){

        String[][] data = new String[bookingList.size()][columnSize];

        for (int i=0 ; i < bookingList.size() ; i++){
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getCustomer());
            data[i][2] = String.valueOf(bookingList.get(i).getVehicle());
            data[i][3] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][4] = String.valueOf(bookingList.get(i).getCompleteDate());
            data[i][5] = String.valueOf(bookingList.get(i).getAmount());
            data[i][6] = bookingList.get(i).getStatus();

        }
        return data;
    }

    public void save(String cId, String vId, String date, String amount) {
        Booking booking= Booking.builder()
                .customer(cId)
                .vehicle(vId)
                .bookingDate(Date.valueOf(date))
                .amount(Float.valueOf(amount))
                .build();
        bookingDAO.insert(booking);
    }

    public String[] getVehicleDataForComboBox(){
       List<Vehicle> vehicleList =vehicleDAO.getDataForComboBox();
        return convertVehicleListToArray(vehicleList);
    }

    public String[] getCustomerDataForComboBox(){
        List<Customer> customerList =customerDAO.getDataForComboBox();
        return convertCustomerListToArray(customerList);
    }

    private String[] convertVehicleListToArray(List<Vehicle> vehicleList){
        String[] data = new String[vehicleList.size()];

        for (int i = 0; i < vehicleList.size(); i++) {
            data[i] = vehicleList.get(i).getId()+","+vehicleList.get(i).getVehicleName();
        }
        return data;
    }


    private String[] convertCustomerListToArray(List<Customer> customerList){
        String[] data = new String[customerList.size()];

        for (int i = 0; i < customerList.size(); i++) {
            data[i] = customerList.get(i).getId()+","+customerList.get(i).getName();
        }
        return data;
    }

    public void setBookingInactive(Long id) {
        bookingDAO.setBookingInactive(id);
    }

    public void setBookingComplete(Long id , Date date){

        bookingDAO.setBookingComplete(id,date);
    }
}

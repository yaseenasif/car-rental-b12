package org.car_rental.service;

import org.car_rental.dao.BookingDAO;
import org.car_rental.domain.Booking;

import java.sql.Date;
import java.util.List;

public class BookingService {

    BookingDAO bookingDAO=new BookingDAO();
    public void delete(Long id){

        bookingDAO.deleteById(id);
    }


    public String[][] getAllForJTable() {
    List<Booking> bookingList = bookingDAO.getAll();
    return transformToJTable(bookingList,5);
    }

    public String[][] searchByDate(Date date){
        List<Booking> bookingList = bookingDAO.getByDate(date);
       return transformToJTable(bookingList,5);
    }

    public String[][] transformToJTable(List<Booking> bookingList , Integer columnSize){
        String[][] data = new String[bookingList.size()][columnSize];

        for (int i=0 ; i< bookingList.size() ; i++){
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getCustomerId());
            data[i][2] = String.valueOf(bookingList.get(i).getVehicleId());
            data[i][3] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][4] = String.valueOf(bookingList.get(i).getAmount());

        }
        return data;
    }

    public void save(String cId, String vId, String date, String amount) {
        Booking booking= Booking.builder()
                .customerId(Long.valueOf(cId))
                .vehicleId(Long.valueOf(vId))
                .bookingDate(Date.valueOf(date))
                .amount(Float.valueOf(amount))
                .build();
        bookingDAO.insert(booking);
    }

}

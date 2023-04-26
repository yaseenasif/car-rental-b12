package org.car_rental.ui;


import org.car_rental.service.BookingService;
import org.car_rental.service.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.Properties;

public class CompleteBookingUi {

    BookingService bookingService = new BookingService();
    public CompleteBookingUi(String id , String date){


        JFrame frame=new JFrame("COMPLETE BOOKING");

        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        JButton completeBookingBtn = new JButton("Complete Booking");
        JButton backBtn = new JButton("Back");
        frame.add(datePicker);
        frame.add(completeBookingBtn);
        frame.add(backBtn);
        datePicker.getJFormattedTextField().setText(date);


        completeBookingBtn.addActionListener(e->{
//                if( ) {
                    bookingService.setBookingComplete(Long.valueOf(id), Date.valueOf(datePicker.getJFormattedTextField().getText()));
                    JOptionPane.showMessageDialog(frame, "Booking Completed successfully");
                    frame.dispose();
                    new BookingUi();
//                }
        });

        backBtn.addActionListener(e->{
            new BookingUi();
            frame.dispose();
        });

        frame.setLayout(new FlowLayout());
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}


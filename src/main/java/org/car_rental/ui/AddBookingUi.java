package org.car_rental.ui;

import org.car_rental.service.BookingService;
import org.car_rental.service.DateLabelFormatter;
import org.jdatepicker.impl.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class AddBookingUi {

    BookingService bookingService=new BookingService();

    public static void main(String[] args) {
        new AddBookingUi();
    }
    public AddBookingUi(){

        JFrame frame=new JFrame("ADD BOOKING");

        JLabel customerLbl= new JLabel("Select Customer :");
        JLabel vehicleLbl= new JLabel("Select Vehicle :");
        JLabel dateLbl= new JLabel("Select Date :");
        JLabel amountLbl= new JLabel("Amount :");
        JTextField amountTf =new JTextField();

        String[] customerOptions = bookingService.getCustomerDataForComboBox();
        JComboBox<String> customerComboBox = new JComboBox<>(customerOptions);
        customerComboBox.setSize(20,10);


        String[] vehicleOptions = bookingService.getVehicleDataForComboBox();
        JComboBox<String> vehicleComboBox = new JComboBox<>(vehicleOptions);
        vehicleComboBox.setSize(20,10);


            UtilDateModel model = new UtilDateModel();
            model.setSelected(true);
            Properties properties = new Properties();
            properties.put("text.today", "Today");
            properties.put("text.month", "Month");
            properties.put("text.year", "Year");
            JDatePanelImpl datePanel = new JDatePanelImpl(model,properties);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
            JButton bookBtn = new JButton("Book");
            JButton backBtn = new JButton("Back");

        frame.add(customerLbl);
        frame.add(customerComboBox);
        frame.add(vehicleLbl);
        frame.add(vehicleComboBox);
        frame.add(dateLbl);
        frame.add(datePicker);
        frame.add(amountLbl);
        frame.add(amountTf);
        frame.add(bookBtn);
        frame.add(backBtn);

        bookBtn.addActionListener(e->{
            String vehicleInput = (String) vehicleComboBox.getSelectedItem();
            String[] vehicleParts = vehicleInput.split(",");
            String vehicleId = vehicleParts[0];

            String customerInput = (String) customerComboBox.getSelectedItem();
            String[] customerParts = customerInput.split(",");
            String customerId = customerParts[0];


            if(!amountTf.getText().isEmpty() || datePicker.getJFormattedTextField() == null ) {
                if(amountTf.getText().matches("[0-9]*\\.?[0-9]+")) {
                    bookingService.save(customerId, vehicleId, String.valueOf(datePicker.getJFormattedTextField().getText()), amountTf.getText());
                    JOptionPane.showMessageDialog(frame,"Booking Added");
                }else{
                    JOptionPane.showMessageDialog(frame,"PLease enter the correct amount");
                }
            }else{
                JOptionPane.showMessageDialog(frame,"Please fill all the fields");
            }
        });

        backBtn.addActionListener(e->{
            frame.dispose();
            new BookingUi();
        });


        frame.setLayout(new GridLayout(8,3,10,30));
        frame.setSize(500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
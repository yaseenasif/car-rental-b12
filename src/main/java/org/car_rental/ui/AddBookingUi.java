package org.car_rental.ui;

import org.car_rental.service.BookingService;
import org.car_rental.service.VehicleOwnerService;

import javax.swing.*;
import java.awt.*;

public class AddBookingUi {

    BookingService bookingService=new BookingService();
    public AddBookingUi(){

        JFrame frame=new JFrame("ADD BOOKING");


        JTextField cIdTf = new JTextField(20);
        JTextField vIdTf = new JTextField(20);
        JTextField dateTf = new JTextField(20);
        JTextField amountTf = new JTextField(20);

        JLabel cIdLb = new JLabel("C_ID");
        JLabel vIdLb = new JLabel("V_ID");
        JLabel dateLb = new JLabel("BOOKING DATE");
        JLabel amountLb = new JLabel("AMOUNT");

        JButton saveBtn =new JButton("SAVE");
        JButton backBtn =new JButton("BACK");

        frame.add(cIdLb);
        frame.add(cIdTf);
        frame.add(vIdLb);
        frame.add(vIdTf);
        frame.add(dateLb);
        frame.add(dateTf);
        frame.add(amountLb);
        frame.add(amountTf);
        frame.add(saveBtn);
        frame.add(backBtn);


        backBtn.addActionListener(e->{
            frame.dispose();
            new BookingUi();
        });

        saveBtn.addActionListener(e->{

            if(cIdTf.getText().isEmpty() || vIdTf.getText().isEmpty() || dateTf.getText().isEmpty() || amountTf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame,"Please fill all the fields");

            }else{
                bookingService.save(cIdTf.getText(),vIdTf.getText(),dateTf.getText(),amountTf.getText());
                JOptionPane.showMessageDialog(frame,"Record has been saved successfully");

            }
        });


        frame.setLayout(new GridLayout(8,3,0,50));
        frame.setSize(500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
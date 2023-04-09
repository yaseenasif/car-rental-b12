package org.car_rental.ui;

import org.car_rental.service.VehicleService;

import javax.swing.*;
import java.awt.*;

public class AddVehicleUi {

    VehicleService vehicleService = new VehicleService();
    public AddVehicleUi(){

        JFrame frame=new JFrame("ADD VEHICLE");


        JTextField nameTf = new JTextField(20);
        JTextField colorTf = new JTextField(20);
        JTextField yearTf = new JTextField(20);
        JTextField brandTf = new JTextField(20);
        JTextField ownerTf = new JTextField(20);

        JLabel nameLb = new JLabel("Name");
        JLabel colorLb = new JLabel("COLOR");
        JLabel yearLb = new JLabel("YEAR");
        JLabel brandLb = new JLabel("BRAND");
        JLabel ownerLb = new JLabel("OWNER ID");

        JButton saveBtn =new JButton("SAVE");
        JButton backBtn =new JButton("BACK");

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(colorLb);
        frame.add(colorTf);
        frame.add(yearLb);
        frame.add(yearTf);
        frame.add(brandLb);
        frame.add(brandTf);
        frame.add(ownerLb);
        frame.add(ownerTf);
        frame.add(saveBtn);
        frame.add(backBtn);


        saveBtn.addActionListener(e->{
            if(nameTf.getText().isEmpty() || colorTf.getText().isEmpty() || yearTf.getText().isEmpty() || brandTf.getText().isEmpty() || ownerTf.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame,"Please fill all the fields");
            }else {

                vehicleService.save(nameTf.getText(), colorTf.getText(), yearTf.getText(), brandTf.getText(), ownerTf.getText());
                JOptionPane.showMessageDialog(frame, "Record has been saved successfully");
                nameTf.setText("");
                colorTf.setText("");
                yearTf.setText("");
                brandTf.setText("");
                ownerTf.setText("");
            }

        });

        backBtn.addActionListener(e->{
            frame.dispose();
            new VehicleUi();
        });

        frame.setLayout(new GridLayout(8,3,0,50));
        frame.setSize(500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

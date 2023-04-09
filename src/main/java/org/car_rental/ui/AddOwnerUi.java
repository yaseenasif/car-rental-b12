package org.car_rental.ui;

import org.car_rental.service.VehicleOwnerService;

import javax.swing.*;
import java.awt.*;

public class AddOwnerUi {

    VehicleOwnerService vehicleOwnerService=new VehicleOwnerService();
    public AddOwnerUi(){


        JFrame frame=new JFrame("ADD OWNER");


        JTextField nameTf = new JTextField(20);
        JTextField phoneTf = new JTextField(20);
        JTextField addressTf = new JTextField(20);
        JTextField commissionTf = new JTextField(20);

        JLabel nameLb = new JLabel("Name");
        JLabel phoneLb = new JLabel("PHONE NUMBER");
        JLabel addressLb = new JLabel("ADDRESS");
        JLabel commissionLb = new JLabel("Commission");

        JButton saveBtn =new JButton("SAVE");
        JButton backBtn =new JButton("BACK");

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(phoneLb);
        frame.add(phoneTf);
        frame.add(addressLb);
        frame.add(addressTf);
        frame.add(commissionLb);
        frame.add(commissionTf);
        frame.add(saveBtn);
        frame.add(backBtn);


        backBtn.addActionListener(e->{
            frame.dispose();
            new VehicleOwnerUi();
        });

        saveBtn.addActionListener(e->{

            if(nameTf.getText().isEmpty() || phoneTf.getText().isEmpty() || commissionTf.getText().isEmpty() || addressTf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame,"Please fill all the fields");

            }else{
                vehicleOwnerService.save(nameTf.getText(), phoneTf.getText(), addressTf.getText(), commissionTf.getText());
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
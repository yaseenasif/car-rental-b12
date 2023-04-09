package org.car_rental.ui;

import org.car_rental.service.VehicleOwnerService;

import javax.swing.*;
import java.awt.*;

public class EditOwnerUi {

    VehicleOwnerService vehicleOwnerService=new VehicleOwnerService();

    public  EditOwnerUi(String id,String name,String phone, String address,String commission){

            JFrame frame=new JFrame("ADD CUSTOMER");

            JTextField nameTf = new JTextField(20);
            JTextField phoneTf = new JTextField(20);
            JTextField addressTf = new JTextField(20);
            JTextField commissionTf = new JTextField(20);

            JLabel nameLb = new JLabel("Name");
            JLabel phoneLb = new JLabel("PHONE NUMBER");
            JLabel addressLb = new JLabel("ADDRESS");
            JLabel commissionLb = new JLabel("Commission");

            JButton updateBtn =new JButton("UPDATE");
            JButton backBtn =new JButton("BACK");

            frame.add(nameLb);
            frame.add(nameTf);
            frame.add(phoneLb);
            frame.add(phoneTf);
            frame.add(addressLb);
            frame.add(addressTf);
            frame.add(commissionLb);
            frame.add(commissionTf);
            frame.add(updateBtn);
            frame.add(backBtn);

            nameTf.setText(name);
            phoneTf.setText(phone);
            addressTf.setText(address);
            commissionTf.setText(commission);

            backBtn.addActionListener(e->{
                frame.dispose();
                new VehicleOwnerUi();
            });

            updateBtn.addActionListener(e->{

                if(nameTf.getText().isEmpty() || phoneTf.getText().isEmpty() || commissionTf.getText().isEmpty() || addressTf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame,"Please fill all the fields");

                }else{
                    vehicleOwnerService.update(id, nameTf.getText(), phoneTf.getText(), addressTf.getText(), commissionTf.getText());
                    JOptionPane.showMessageDialog(frame,"Record has been updated successfully");
                    new VehicleOwnerUi();
                    frame.dispose();
                }
            });

            frame.setLayout(new GridLayout(8,3,0,50));
            frame.setSize(500,1000);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

}

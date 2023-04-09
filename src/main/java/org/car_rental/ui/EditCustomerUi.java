package org.car_rental.ui;

import org.car_rental.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class EditCustomerUi {

    CustomerService customerService = new CustomerService();

    public EditCustomerUi(String id , String name , String phone , String cnic , String address, String refPhone){

        JFrame frame=new JFrame("EDIT CUSTOMER");


        JTextField nameTf = new JTextField(20);
        JTextField phoneTf = new JTextField(20);
        JTextField addressTf = new JTextField(20);
        JTextField cnicTf = new JTextField(20);
        JTextField refTf = new JTextField(20);

        JLabel nameLb = new JLabel("Name");
        JLabel phoneLb = new JLabel("PHONE NUMBER");
        JLabel cnicLb = new JLabel("CNIC");
        JLabel addressLb = new JLabel("ADDRESS");
        JLabel refLb = new JLabel("REFERENCE PHONE");

        JButton updateBtn =new JButton("UPDATE");
        JButton backBtn =new JButton("BACK");


        nameTf.setText(name);
        phoneTf.setText(phone);
        cnicTf.setText(cnic);
        addressTf.setText(address);
        refTf.setText(refPhone);

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(phoneLb);
        frame.add(phoneTf);
        frame.add(cnicLb);
        frame.add(cnicTf);
        frame.add(addressLb);
        frame.add(addressTf);
        frame.add(refLb);
        frame.add(refTf);
        frame.add(updateBtn);
        frame.add(backBtn);

        updateBtn.addActionListener(e->{
            if(nameTf.getText().isEmpty() || phoneTf.getText().isEmpty() || cnicTf.getText().isEmpty() || addressTf.getText().isEmpty() || refTf.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame,"Please file all the fields");
            }else {
                customerService.updateCustomer(Long.valueOf(id), nameTf.getText(), phoneTf.getText(), cnicTf.getText(), addressTf.getText(), refTf.getText());
                JOptionPane.showMessageDialog(frame, "Record has been updated successfully");
                frame.dispose();
                new CustomerUi();
            }
        });


        backBtn.addActionListener(e->{
            frame.dispose();
            new CustomerUi();
        });

        frame.setLayout(new GridLayout(8,3,0,50));
        frame.setSize(500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}

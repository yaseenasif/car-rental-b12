package org.car_rental.ui;

import org.car_rental.domain.Customer;
import org.car_rental.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class AddCustomerUi {

    CustomerService customerService =new CustomerService();
    Customer customer=new Customer();

    public AddCustomerUi(){

        JFrame frame=new JFrame("ADD CUSTOMER");


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

        JButton saveBtn =new JButton("SAVE");
        JButton backBtn =new JButton("BACK");

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
        frame.add(saveBtn);
        frame.add(backBtn);


        backBtn.addActionListener(e->{
            frame.dispose();
            new CustomerUi();
        });

        saveBtn.addActionListener(e->{

            if(nameTf.getText().isEmpty() || phoneTf.getText().isEmpty() || cnicTf.getText().isEmpty() || addressTf.getText().isEmpty() || refTf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame,"Please fill all the fields");

            }else{
                customerService.save(nameTf.getText(), phoneTf.getText(), cnicTf.getText(), addressTf.getText(), refTf.getText());
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

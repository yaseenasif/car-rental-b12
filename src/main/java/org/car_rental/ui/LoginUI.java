package org.car_rental.ui;

import org.car_rental.service.AuthenticationService;

import javax.swing.*;
import java.awt.*;

public class LoginUI  {

    private final AuthenticationService authenticationService =new AuthenticationService();

    public LoginUI(){

        JFrame frame=new JFrame("Car Rental");
        frame.setSize(500,500);

        JButton LoginBtn=new JButton("Login");
        LoginBtn.setBounds(150,350,150,30);

        JLabel userLbl=new JLabel("Username");
        userLbl.setBounds(50,100,100,40);

        JLabel passLbl=new JLabel("Password");
        passLbl.setBounds(50,150,100,40);

        JTextField userTf=new JTextField();
        userTf.setBounds(160,110,150,30);

        JTextField passTf=new JTextField();
        passTf.setBounds(160,160,150,30);



        LoginBtn.addActionListener((event)->{

            if(authenticationService.checkLogin(userTf.getText(),passTf.getText())){
                frame.dispose();
                new HomeUi();
            }else{

                JOptionPane.showMessageDialog(frame,"Incorrect credentials");
            }


        });


        frame.add(LoginBtn);
        frame.add(userTf);
        frame.add(passTf);
        frame.add(passLbl);
        frame.add(userLbl);


        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

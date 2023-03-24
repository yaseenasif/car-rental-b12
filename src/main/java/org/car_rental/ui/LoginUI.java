package org.car_rental.ui;

import javax.swing.*;
import java.awt.*;

public class LoginUI  {

    public LoginUI(){

        JFrame frame=new JFrame("Login");
        frame.setSize(500,500);

        JButton button=new JButton("OK");
        button.setBounds(150,200,150,50);

        JLabel label=new JLabel();
        label.setBounds(150,300,150,50);

        JTextField textField=new JTextField();
        textField.setBounds(150,50,150,100);


        frame.add(button);
        frame.add(label);
        frame.add(textField);


        button.addActionListener((b)->{
            label.setText(textField.getText());
            label.setForeground(Color.blue);

        });



        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

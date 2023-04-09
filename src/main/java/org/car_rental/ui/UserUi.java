package org.car_rental.ui;

import javax.swing.*;
import java.awt.*;

public class UserUi {


    public UserUi(){


        JFrame frame=new JFrame();
        frame.setSize(1500,1000);


        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,200));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

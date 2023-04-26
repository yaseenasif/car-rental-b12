package org.car_rental.ui;

import javax.swing.*;
import java.awt.*;

public class HomeUi {

    public static void main(String[] args) {
        new HomeUi();
    }
        public HomeUi() {

            JFrame frame = new JFrame("Home");
            frame.setSize(1500, 1000);

            JPanel buttonPanel=new JPanel();
            buttonPanel.setLayout(new GridLayout(3,3,10,10));

            JButton customerBtn=new JButton("Customer");
            JButton vehicleBtn=new JButton("Vehicle");
            JButton vehicleOwnerBtn=new JButton("Owner");
            JButton bookingBtn=new JButton("Booking");
            JButton userBtn=new JButton("User");
            JButton reportBtn = new JButton("Report");
            JButton logoutBtn=new JButton("Logout");
            buttonPanel.add(customerBtn);
            buttonPanel.add(vehicleBtn);
            buttonPanel.add(bookingBtn);
            buttonPanel.add(vehicleOwnerBtn);
            buttonPanel.add(userBtn);
            buttonPanel.add(reportBtn);
            buttonPanel.add(logoutBtn);

            addImageOnButton(customerBtn,"src/main/resources/return-customer-icon.png",50,50);
            addImageOnButton(vehicleBtn,"src/main/resources/car-vehicle-insurance-icon.png",50,50);
            addImageOnButton(vehicleOwnerBtn,"src/main/resources/owner.png",50,50);
            addImageOnButton(bookingBtn,"src/main/resources/booking-reservation-icon.png",50,50);
            addImageOnButton(userBtn,"src/main/resources/employees-users-icon.png",50,50);
            addImageOnButton(logoutBtn,"src/main/resources/shutdown-icon.png",50,50);

            frame.add(buttonPanel);

            reportBtn.addActionListener(e->{
                new ReportUi();
                frame.dispose();
            });


            customerBtn.addActionListener((event)->{

                frame.dispose();
                new CustomerUi();
            });

            vehicleBtn.addActionListener((event)->{

                frame.dispose();
                new VehicleUi();

            });

            vehicleOwnerBtn.addActionListener((event)->{

                frame.dispose();
                new VehicleOwnerUi();

            });

            bookingBtn.addActionListener((event)->{

                frame.dispose();
                new BookingUi();

            });

            userBtn.addActionListener((event)->{

                frame.dispose();
                new UserUi();

            });

            logoutBtn.addActionListener((event)->{

                frame.dispose();
                new LoginUI();

            });


            frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,200));
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}

    private static void addImageOnButton(JButton button,String imagePath,int height,int width){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(newImage));

    }

}

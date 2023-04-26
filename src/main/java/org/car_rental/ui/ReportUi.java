package org.car_rental.ui;

import javax.swing.*;
import java.awt.*;

public class ReportUi {

    public static void main(String[] args) {
        new ReportUi();
    }
    public ReportUi(){

        JFrame frame =new JFrame("REPORT");

        JButton monthlyBtn = new JButton("Monthly Report");
        JButton commissionBtn = new JButton("Commission Report");
        JButton carAvailabilityBtn = new JButton("Car Availability Report");
        JButton generatePdfBtn = new JButton("Generate Pdf");
        JButton backBtn = new JButton("BACK");
        frame.add(monthlyBtn);
        frame.add(commissionBtn);
        frame.add(carAvailabilityBtn);
        frame.add(generatePdfBtn);
        frame.add(backBtn);

        monthlyBtn.addActionListener(e->{
            new MonthlyReportUi();
            frame.dispose();
        });

        commissionBtn.addActionListener(e->{
            new CommissionReportUi();
            frame.dispose();
        });

        carAvailabilityBtn.addActionListener(e->{
            new CarAvailabilityReportUi();
            frame.dispose();
        });


        backBtn.addActionListener(e->{
            new HomeUi();
            frame.dispose();
        });

        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,200));
        frame.setSize(1500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}

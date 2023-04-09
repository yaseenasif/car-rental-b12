package org.car_rental.ui;

import org.car_rental.service.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingUi {

    BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        new BookingUi();
    }
    public BookingUi(){

        JFrame frame=new JFrame("Booking");
        frame.setSize(1500,1000);


        JPanel tblAndSearchPanel = new JPanel();
        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JTextField searchTf = new JTextField(30);

        String[][] data = bookingService.getAllForJTable();
        String[] column={"BOOKING ID","C_ID","V_ID","BOOKING DATE","AMOUNT"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(data,column);
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        JButton searchBtn = new JButton("Search");

        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(sp);
        tblAndSearchPanel.add(searchBtn);

        searchBtn.addActionListener(e->{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date =  dateFormat.parse(searchTf.getText());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                String[][] data1 = bookingService.searchByDate(sqlDate);
                DefaultTableModel defaultTableModel1 = new DefaultTableModel(data1,column);
                jt.setModel(defaultTableModel1);

            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        });


        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JButton addBookingButton = new JButton("ADD");
        JButton editBookingButton = new JButton("EDIT");
        JButton deleteBookingButton = new JButton("DELETE");
        JButton backBtn = new JButton("BACK");

        actionButtonPanel.add(addBookingButton);
        actionButtonPanel.add(editBookingButton);
        actionButtonPanel.add(deleteBookingButton);
        actionButtonPanel.add(backBtn);


        addBookingButton.addActionListener(e->{
            new AddBookingUi();
            frame.dispose();
        });

        deleteBookingButton.addActionListener(e->{
            int selectedRowIndex = jt.getSelectedRow();
            if(selectedRowIndex < 0){
                JOptionPane.showMessageDialog(frame,"Please select a record from the table");
            }else {
//                String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
//                defaultTableModel.removeRow(selectedRowIndex);
//                vehicleService.delete(Long.valueOf(selectedId));
//                DefaultTableModel defaultTableModel1=new DefaultTableModel(vehicleService.getAllForJTable(),column);
//                jt.setModel(defaultTableModel1);
//                JOptionPane.showMessageDialog(frame,"Record has been updated successfully");
            }
        });

        editBookingButton.addActionListener(e->{
            int selectedRowIndex = jt.getSelectedRow();
            if(selectedRowIndex < 0){
                JOptionPane.showMessageDialog(frame,"Please select a record from table");
            }else {
//                String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
//                String selectedName = (String) jt.getValueAt(selectedRowIndex,1);
//                String selectedColor = (String) jt.getValueAt(selectedRowIndex,2);
//                String selectedYear = (String) jt.getValueAt(selectedRowIndex,3);
//                String selectedBrand = (String) jt.getValueAt(selectedRowIndex,4);
//                String selectedOwner = (String) jt.getValueAt(selectedRowIndex,5);
//                new EditVehicleUi(selectedId,selectedName,selectedColor,selectedYear,selectedBrand,selectedOwner);
//                DefaultTableModel defaultTableModel1 = new DefaultTableModel(vehicleService.getAllForJTable(),column);
//                jt.setModel(defaultTableModel1);
//                frame.dispose();
            }
        });

        backBtn.addActionListener(e->{
            new HomeUi();
            frame.dispose();
        });


        frame.add(tblAndSearchPanel);
        frame.add(actionButtonPanel);
        frame.setLayout(new GridLayout(1,2,150,5));
        frame.setSize(1500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}

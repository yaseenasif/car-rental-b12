package org.car_rental.ui;

import org.car_rental.service.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
        String[] column={"ID","Customer","Vehicle","BOOKING DATE","COMPLETE DATE","AMOUNT","STATUS"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(data,column);
        JTable jt=new JTable(data,column);
        jt.setBounds(50,50,300,300);
        JScrollPane sp=new JScrollPane(jt);
        sp.setSize(100,100);
        JButton searchBtn = new JButton("Search");

        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(sp);
        tblAndSearchPanel.add(searchBtn);

        searchBtn.addActionListener(e->{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          if(!searchTf.getText().isEmpty()) {
              try {
                  Date date = dateFormat.parse(searchTf.getText());
                  java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                  String[][] data1 = bookingService.searchByDate(sqlDate);
                  DefaultTableModel defaultTableModel1 = new DefaultTableModel(data1, column);
                  jt.setModel(defaultTableModel1);

              } catch (ParseException ex) {
                  JOptionPane.showMessageDialog(frame,"Please enter a valid date");
              }
          }else {
              JOptionPane.showMessageDialog(frame,"please enter a date");
          }
        });


        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JButton addBookingButton = new JButton("Make Booking");
        JButton editBookingButton = new JButton("EDIT");
        JButton deleteBookingButton = new JButton("DELETE");
        JButton backBtn = new JButton("BACK");
        JButton completeBookingBtn = new JButton("Complete Booking");
        JButton reportBtn = new JButton("Report");
        actionButtonPanel.add(addBookingButton);
        actionButtonPanel.add(editBookingButton);
        actionButtonPanel.add(completeBookingBtn);
        actionButtonPanel.add(deleteBookingButton);
        actionButtonPanel.add(reportBtn);
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
                String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
                defaultTableModel.removeRow(selectedRowIndex);
                bookingService.setBookingInactive(Long.valueOf(selectedId));
                DefaultTableModel defaultTableModel1=new DefaultTableModel(bookingService.getAllForJTable(),column);
                jt.setModel(defaultTableModel1);
                JOptionPane.showMessageDialog(frame,"Record has been Inactivated successfully");
            }
        });

            editBookingButton.addActionListener(e -> {
                int selectedRowIndex = jt.getSelectedRow();
                if (selectedRowIndex < 0) {
                        JOptionPane.showMessageDialog(frame, "Please select a record from table");
                    } else {
                    String selectedStatus = (String) jt.getValueAt(selectedRowIndex, 6);
                if(selectedStatus.equalsIgnoreCase("open")) {
                            String selectedId = (String) jt.getValueAt(selectedRowIndex, 0);
                            String cId = (String) jt.getValueAt(selectedRowIndex, 1);
                            String vId = (String) jt.getValueAt(selectedRowIndex, 2);
                        String selectedBookingDate = (String) jt.getValueAt(selectedRowIndex, 3);
                        String selectedAmount = (String) jt.getValueAt(selectedRowIndex, 5);
                        new EditBookingUi(selectedId, selectedBookingDate, selectedAmount , cId, vId);
                        DefaultTableModel defaultTableModel1 = new DefaultTableModel(bookingService.getAllForJTable(), column);
                        jt.setModel(defaultTableModel1);
                        frame.dispose();
                    }else {
                            JOptionPane.showMessageDialog(frame,"Complete booking cannot be updated");
                        }
                }
            });


        completeBookingBtn.addActionListener(e->{
          int selectedRowIndex = jt.getSelectedRow();
            if(selectedRowIndex >= 0 ){
                String status = (String) jt.getValueAt(selectedRowIndex,6);
                String id = (String) jt.getValueAt(selectedRowIndex,0);
                String date = (String) jt.getValueAt(selectedRowIndex,3);
                if(status.equalsIgnoreCase("Open")){
                    new CompleteBookingUi(id,date);
                    frame.dispose();
                    DefaultTableModel defaultTableModel1=new DefaultTableModel(bookingService.getAllForJTable(),column);
                    jt.setModel(defaultTableModel1);
                }else {
                    JOptionPane.showMessageDialog(frame,"The booking is already completed");
                }
            }else{
                JOptionPane.showMessageDialog(frame,"Please select a field");
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

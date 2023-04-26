package org.car_rental.ui;

import org.car_rental.service.VehicleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VehicleUi {


    VehicleService vehicleService = new VehicleService();
    public static void main(String[] args) {
        new VehicleUi();
    }
    public VehicleUi(){
        JFrame frame=new JFrame("Vehicle");


        JPanel tblAndSearchPanel = new JPanel();
        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JTextField searchTf = new JTextField(30);

        String[][] data = vehicleService.getAllForJTable();
        String[] column={"ID","NAME","COLOR","YEAR","BRAND","OWNER ID"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(data,column);
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);

        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(sp);

        searchTf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
              String[][] data = vehicleService.searchByName(searchTf.getText());
                DefaultTableModel defaultTableModel = new DefaultTableModel(data,column);
                jt.setModel(defaultTableModel);
            }
        });


        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JButton addVehicleButton = new JButton("ADD");
        JButton editVehicleButton = new JButton("EDIT");
        JButton deleteVehicleButton = new JButton("DELETE");
        JButton backBtn = new JButton("BACK");

        actionButtonPanel.add(addVehicleButton);
        actionButtonPanel.add(editVehicleButton);
        actionButtonPanel.add(deleteVehicleButton);
        actionButtonPanel.add(backBtn);


        addVehicleButton.addActionListener(e->{
            new AddVehicleUi();
            frame.dispose();
        });

        deleteVehicleButton.addActionListener(e->{
           int selectedRowIndex = jt.getSelectedRow();
           if(selectedRowIndex < 0){
            JOptionPane.showMessageDialog(frame,"Please select a record from the table");
           }else {
               String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
               defaultTableModel.removeRow(selectedRowIndex);
               vehicleService.setVehicleInactive(Long.valueOf(selectedId));
               DefaultTableModel defaultTableModel1=new DefaultTableModel(vehicleService.getAllForJTable(),column);
               jt.setModel(defaultTableModel1);
               JOptionPane.showMessageDialog(frame,"Record has been updated successfully");
           }
        });

        editVehicleButton.addActionListener(e->{
            int selectedRowIndex = jt.getSelectedRow();
            if(selectedRowIndex < 0){
                JOptionPane.showMessageDialog(frame,"Please select a record from table");
            }else {
                String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
                String selectedName = (String) jt.getValueAt(selectedRowIndex,1);
                String selectedColor = (String) jt.getValueAt(selectedRowIndex,2);
                String selectedYear = (String) jt.getValueAt(selectedRowIndex,3);
                String selectedBrand = (String) jt.getValueAt(selectedRowIndex,4);
                String selectedOwner = (String) jt.getValueAt(selectedRowIndex,5);
                new EditVehicleUi(selectedId,selectedName,selectedColor,selectedYear,selectedBrand,selectedOwner);
                DefaultTableModel defaultTableModel1 = new DefaultTableModel(vehicleService.getAllForJTable(),column);
                jt.setModel(defaultTableModel1);
                frame.dispose();
            }
        });

        backBtn.addActionListener(e->{
            new HomeUi();
            frame.dispose();
        });




        frame.setLayout(new GridLayout(1,2,150,5));

        frame.add(tblAndSearchPanel);
        frame.add(actionButtonPanel);

        frame.setSize(1500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }



}

package org.car_rental.ui;

import org.car_rental.service.VehicleOwnerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VehicleOwnerUi {

    VehicleOwnerService vehicleOwnerService =new VehicleOwnerService();

    public static void main(String[] args) {
        new VehicleOwnerUi();
    }

    public VehicleOwnerUi(){

        JFrame frame=new JFrame("Vehicle Owner");


        JPanel tblAndSearchPanel = new JPanel();
        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JTextField searchTf = new JTextField(30);

        String[][] data= vehicleOwnerService.getAllForJTable();
        String[] column={"ID","NAME","PHONE","ADDRESS","COMMISSION"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(data,column);
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
                String[][] data = vehicleOwnerService.searchByName(searchTf.getText());
                DefaultTableModel defaultTableModel1=new DefaultTableModel(data,column);
                jt.setModel(defaultTableModel1);
            }
        });

        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JButton addOwnerButton = new JButton("ADD");
        JButton editOwnerButton = new JButton("EDIT");
        JButton deleteOwnerButton = new JButton("DELETE");
        JButton backBtn = new JButton("BACK");

        actionButtonPanel.add(addOwnerButton);
        actionButtonPanel.add(editOwnerButton);
        actionButtonPanel.add(deleteOwnerButton);
        actionButtonPanel.add(backBtn);

        addOwnerButton.addActionListener(e->{
            new AddOwnerUi();
            frame.dispose();
        });


        deleteOwnerButton.addActionListener(e->{
            int selectedRowIndex = jt.getSelectedRow();
            if(selectedRowIndex < 0){
                JOptionPane.showMessageDialog(frame,"Please select a row");
            }else{
                String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
                defaultTableModel.removeRow(selectedRowIndex);
                vehicleOwnerService.setOwnerInactive(Long.valueOf(selectedId));
                DefaultTableModel defaultTableModel1 =new DefaultTableModel(vehicleOwnerService.getAllForJTable(),column);
                jt.setModel(defaultTableModel1);
                JOptionPane.showMessageDialog(frame,"Record has been deleted successfully");
            }
        });

        editOwnerButton.addActionListener(e->{
            int selectedRowIndex = jt.getSelectedRow();
            if(selectedRowIndex < 0){
                JOptionPane.showMessageDialog(frame,"Please select a record");
            }else{
                String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
                String selectedName = (String) jt.getValueAt(selectedRowIndex,1);
                String selectedPhone = (String) jt.getValueAt(selectedRowIndex,2);
                String selectedAddress = (String) jt.getValueAt(selectedRowIndex,3);
                String selectedCommission = (String) jt.getValueAt(selectedRowIndex,4);
                new EditOwnerUi(selectedId,selectedName,selectedPhone,selectedAddress,selectedCommission);
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

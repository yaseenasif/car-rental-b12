package org.car_rental.ui;

import com.itextpdf.text.DocumentException;
import org.car_rental.domain.Booking;
import org.car_rental.domain.Customer;
import org.car_rental.service.BookingService;
import org.car_rental.service.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CustomerUi extends Component {


    CustomerService customerService =new CustomerService();
    Customer customer = new Customer();
    BookingService bookingService=new BookingService();
    public static void main(String[] args) {
        new CustomerUi();
    }

    public CustomerUi(){

        JFrame frame=new JFrame("Customer");


        JPanel tblAndSearchPanel = new JPanel();
        tblAndSearchPanel.setBackground(Color.GRAY);
        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JTextField searchTf = new JTextField(30);

        String[][] data = customerService.getAllCustomerForJTable();

        String[] column={"ID","NAME","PHONE NUMBER","CNIC","ADDRESS","REF PHONE"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(data,column);
        JTable jt=new JTable(defaultTableModel);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        JButton generatePdfBtn = new JButton("Generate PDF");


        tblAndSearchPanel.add(searchTf);
        tblAndSearchPanel.add(sp);
        tblAndSearchPanel.add(generatePdfBtn);


        generatePdfBtn.addActionListener(e->{

            try {
                GeneratePdfCustomer.generatePdf(jt);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (DocumentException ex) {
                throw new RuntimeException(ex);
            }
            try {
                File file = new File("report.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("The PDF report file does not exist!");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

         searchTf.addKeyListener(new KeyListener() {
             @Override
             public void keyTyped(KeyEvent e) {

             }

             @Override
             public void keyPressed(KeyEvent e) {

             }

             @Override
             public void keyReleased(KeyEvent e) {

                 String[][] data = customerService.searchCustomerByName(searchTf.getText());
                 DefaultTableModel defaultTableModel=new DefaultTableModel(data,column);
                 jt.setModel(defaultTableModel);

             }

         });


        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton addCustomerButton = new JButton("ADD");
        JButton editCustomerButton = new JButton("EDIT");
        JButton deleteCustomerButton = new JButton("DELETE");
        JButton backButton = new JButton("BACK");


        actionButtonPanel.add(addCustomerButton);
        actionButtonPanel.add(editCustomerButton);
        actionButtonPanel.add(deleteCustomerButton);
        actionButtonPanel.add(backButton);



        addCustomerButton.addActionListener(e->{
            frame.dispose();
            new AddCustomerUi();

        });

        backButton.addActionListener(e->{
            frame.dispose();
            new HomeUi();
        });

        deleteCustomerButton.addActionListener(e->{
            int selectedRowIndex = jt.getSelectedRow();

            if(selectedRowIndex < 0){
                JOptionPane.showMessageDialog(frame,"Please select a row");
            }else{
                String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
                defaultTableModel.removeRow(selectedRowIndex);
                customerService.deleteCustomerById(Long.valueOf(selectedId));
                DefaultTableModel defaultTableModel1 = new DefaultTableModel(customerService.getAllCustomerForJTable(),column);
                jt.setModel(defaultTableModel1);
                JOptionPane.showMessageDialog(frame,"Row successfully deleted");
            }
        });

        editCustomerButton.addActionListener(e->{

          int selectedRowIndex=jt.getSelectedRow();
          if (selectedRowIndex < 0){
              JOptionPane.showMessageDialog(frame,"Please select a row");
          }else{
              String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
              String selectedName= (String) jt.getValueAt(selectedRowIndex,1);
              String selectedPhone = (String) jt.getValueAt(selectedRowIndex,2);
              String selectedCnic = (String) jt.getValueAt(selectedRowIndex,3);
              String selectedAddress = (String) jt.getValueAt(selectedRowIndex,4);
              String selectedRefPhone = (String) jt.getValueAt(selectedRowIndex,5);

              new EditCustomerUi(selectedId,selectedName,selectedPhone,selectedCnic,selectedAddress,selectedRefPhone);
              DefaultTableModel defaultTableModel1 = new DefaultTableModel(customerService.getAllCustomerForJTable(),column);
              jt.setModel(defaultTableModel1);
              frame.dispose();

          }




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

package org.car_rental.service;

import org.car_rental.dao.CustomerDAO;
import org.car_rental.domain.Customer;

import java.util.List;

public class CustomerService {

    CustomerDAO customerDAO =new CustomerDAO();

    public String[][] getAllCustomerForJTable(){

        List<Customer> customerList = customerDAO.getAll();
        return transformToJTable(customerList,6);

    }


    public String[][] searchCustomerByName(String name){

       List<Customer> customerList =  customerDAO.getByName(name);
       return transformToJTable(customerList,6);
    }

    public void save(String name ,String phone , String cnic ,String address ,String refPhone){

        Customer customer = Customer.builder()
                .name(name)
                .phoneNumber(phone)
                .cnic(cnic)
                .address(address)
                .referencePhoneNumber(refPhone)
                .build();


        customerDAO.insert(customer);


    }


    private String[][] transformToJTable(List<Customer> customerList ,Integer columnSize){

        String[][] data=new String[customerList.size()][columnSize];

        for (int i=0 ;i< customerList.size() ;i++){

            data[i][0] = String.valueOf(customerList.get(i).getId());
            data[i][1] = customerList.get(i).getName();
            data[i][2] = customerList.get(i).getPhoneNumber();
            data[i][3] = customerList.get(i).getCnic();
            data[i][4] = customerList.get(i).getAddress();
            data[i][5] = customerList.get(i).getReferencePhoneNumber();


        }

        return data;
    }

     public void  updateCustomer(Long id , String name , String phone , String cnic , String address, String refPhone){

        Customer customer = Customer.builder()
                .name(name)
                .phoneNumber(phone)
                .cnic(cnic)
                .address(address)
                .referencePhoneNumber(refPhone)
                .build();

        customerDAO.update(customer,id);


    }

    public void deleteCustomerById(Long id) {

        customerDAO.deleteById(id);
    }

}

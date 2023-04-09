package org.car_rental.service;

import org.car_rental.dao.VehicleDAO;
import org.car_rental.dao.VehicleOwnerDAO;

import org.car_rental.domain.VehicleOwner;

import java.util.List;

public class VehicleOwnerService {

    VehicleOwnerDAO vehicleOwnerDAO=new VehicleOwnerDAO();
    public String[][] getAllForJTable(){
        List<VehicleOwner> vehicleOwnerList = vehicleOwnerDAO.getAll();
        return transformToJTable(vehicleOwnerList,5);
    }

    public String[][] searchByName(String name) {
        List<VehicleOwner> vehicleOwnerList = vehicleOwnerDAO.getByName(name);
    return transformToJTable(vehicleOwnerList,5);
    }


    public void save(String name, String phone, String address, String commission) {

        VehicleOwner vehicleOwner=VehicleOwner.builder()
                .ownerName(name)
                .ownerNumber(phone)
                .address(address)
                .commission(Float.valueOf(commission))
                .build();
        vehicleOwnerDAO.insert(vehicleOwner);
    }


    public void delete(Long id) {
        vehicleOwnerDAO.deleteById(id);

    }

    public void update(String id ,String name , String phone, String address , String commission) {
        VehicleOwner vehicleOwner=VehicleOwner.builder()
                .ownerName(name)
                .ownerNumber(phone)
                .address(address)
                .commission(Float.valueOf(commission))
                .build();
        vehicleOwnerDAO.update(vehicleOwner , Long.valueOf(id));
    }

    private String[][] transformToJTable(List<VehicleOwner> vehicleOwnerList , Integer columnSize){

        String[][] data = new String[vehicleOwnerList.size()][columnSize];
        for (int i=0 ; i<vehicleOwnerList.size() ; i++){
            data[i][0] = String.valueOf(vehicleOwnerList.get(i).getId());
            data[i][1] = String.valueOf(vehicleOwnerList.get(i).getOwnerName());
            data[i][2] = String.valueOf(vehicleOwnerList.get(i).getOwnerNumber());
            data[i][3] = String.valueOf(vehicleOwnerList.get(i).getAddress());
            data[i][4] = String.valueOf(vehicleOwnerList.get(i).getCommission());
        }
        return data;
    }
}

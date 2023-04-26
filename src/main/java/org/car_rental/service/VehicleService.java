package org.car_rental.service;

import org.car_rental.dao.VehicleDAO;
import org.car_rental.domain.Vehicle;

import java.util.List;

public class VehicleService {

    VehicleDAO  vehicleDAO=new VehicleDAO();

    public String[][] getAllForJTable(){

        List<Vehicle> vehicleList = vehicleDAO.getAll();
        return  transformToJTable(vehicleList,6);

    }

    public String[][] getAvailableVehicles(){
        List<Vehicle> vehicleList = vehicleDAO.getAvailableVehicle();
       return transformToJTable(vehicleList,6);
    }

    public String[][] searchByName(String name){
        List<Vehicle> vehicleList =vehicleDAO.getByName(name);
        return transformToJTable(vehicleList,6);

    }
    public void save(String name ,String color, String year, String brand ,String ownerId) {

        Vehicle vehicle=Vehicle.builder()
                .vehicleName(name)
                .color(color)
                .year(Integer.valueOf(year))
                .brand(brand)
                .ownerId(Long.valueOf(ownerId))
                .build();

        vehicleDAO.insert(vehicle);
    }

    public void delete(Long id){
        vehicleDAO.deleteById(id);
    }

    public void update(String id , String name, String color , String year , String brand , String ownerId){
        Vehicle vehicle=Vehicle.builder()
                .vehicleName(name)
                .color(color)
                .year(Integer.valueOf(year))
                .brand(brand)
                .ownerId(Long.valueOf(ownerId))
                .build();
        vehicleDAO.update(vehicle, Long.valueOf(id));
    }
    private String[][] transformToJTable(List<Vehicle> vehicleList , Integer columnSize){

        String[][] data = new String[vehicleList.size()][columnSize];

        for (int i=0 ; i < vehicleList.size() ; i++){
            data[i][0] = String.valueOf(vehicleList.get(i).getId());
            data[i][1] = vehicleList.get(i).getVehicleName();
            data[i][2] = vehicleList.get(i).getColor();
            data[i][3] = String.valueOf(vehicleList.get(i).getYear());
            data[i][4] = vehicleList.get(i).getBrand();
            data[i][5] = String.valueOf(vehicleList.get(i).getOwnerId());

        }
        return  data;
    }

    public void setVehicleInactive(Long id){
        vehicleDAO.setVehicleInactive(id);
    }


}

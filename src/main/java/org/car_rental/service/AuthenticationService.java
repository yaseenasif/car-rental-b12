package org.car_rental.service;

import org.car_rental.dao.UserDAO;
import org.car_rental.domain.User;

public class AuthenticationService {

    UserDAO userDAO=new UserDAO();

    public Boolean checkLogin(String username ,String password){
       User user = userDAO.getUserByUsernameAndPassword(username,password);

        if(user != null){
            return Boolean.TRUE;

        }
            return Boolean.FALSE;
    }
}

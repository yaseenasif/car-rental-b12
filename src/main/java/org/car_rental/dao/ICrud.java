package org.car_rental.dao;

import java.util.List;

public interface ICrud  <T> {

      List<T> getAll();
      T getById(Long id);
      void insert(T obj);
      void deleteById(Long id);
      void update(T obj , Long id);


}

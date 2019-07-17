package com.tw.apistackbase;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByName(String EmployeeName);
    List<Employee> findDistinctEmployeeByNameOrderByName(String EmployeeName);

    @Transactional
    @Modifying
    @Query("update Employee e set e.name= :#{#employee.name} where e.ID = :id")
    int updateEmployeeById(@Param("employee") Employee employee,@Param("id") long id);





}

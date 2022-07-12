package com.stockkarte.repository;

import com.stockkarte.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepostitory extends JpaRepository<Employee, Long> {
}

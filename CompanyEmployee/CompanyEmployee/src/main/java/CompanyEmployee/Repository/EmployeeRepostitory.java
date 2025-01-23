package CompanyEmployee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import CompanyEmployee.Model.Employee;

public interface EmployeeRepostitory extends JpaRepository<Employee, Integer> {
    
}

package CompanyEmployee.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employee")
public class Employee {
    
    @Id
    private Integer id;
    private String first_name;
    private String last_name;
    private Integer age;
    private String designation;
    private String phone_number;
    private LocalDate joined_on;
    private String address;
    private LocalDate date_of_birth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

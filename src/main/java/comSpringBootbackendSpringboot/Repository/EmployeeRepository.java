package comSpringBootbackendSpringboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comSpringBootbackendSpringboot.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}

package comSpringBootbackendSpringboot.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comSpringBootbackendSpringboot.Execption.ResourceNotFoundExecption;
import comSpringBootbackendSpringboot.Model.Employee;
import comSpringBootbackendSpringboot.Repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}
	
	
		@PostMapping("/employees")
		public Employee createEmployee(@RequestBody Employee employee) {
			return repository.save(employee);
		}
		
		
		@GetMapping("/employees/{id}")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
			Employee employee = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundExecption("Employee not exist with id :" + id));
			return ResponseEntity.ok(employee);
		}
				
		@PutMapping("/employees/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails){
			Employee employee = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundExecption("Employee not exist with id :" + id));
			
			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			employee.setEmailId(employeeDetails.getEmailId());
			
			Employee updatedEmployee = repository.save(employee);
			return ResponseEntity.ok(updatedEmployee);
		}
		
		
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Integer id) throws RelationNotFoundException{
			Employee employee = repository.findById(id)
					.orElseThrow(() -> new RelationNotFoundException("Employee not exist with id :" + id));
			
			repository.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		

}

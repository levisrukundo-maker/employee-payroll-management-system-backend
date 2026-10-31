package rw.rukund.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.rukund.payroll.model.Employee;
import rw.rukund.payroll.model.PayrollOfficer;
import rw.rukund.payroll.service.EmployeeService;
import rw.rukund.payroll.service.PayrollOfficerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PayrollOfficerService payrollOfficerService;

	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		String message = employeeService.saveEmployee(employee);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable UUID id) {
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		if (employee.isPresent()) {
			return new ResponseEntity<>(employee.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The employee with that id does not exist", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {
		String message = employeeService.updateEmployee(id, employee);
		if (message.equals("Employee updated successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable UUID id) {
		String message = employeeService.deleteEmployee(id);
		if (message.equals("Employee deleted successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/by-position")
	public ResponseEntity<List<Employee>> getEmployeesByPosition(@RequestParam String position) {
		return new ResponseEntity<>(employeeService.getEmployeesByPosition(position), HttpStatus.OK);
	}

	@GetMapping("/of-payroll-officer/{payrollOfficerId}")
	public ResponseEntity<?> getEmployeesOfPayrollOfficer(@PathVariable UUID payrollOfficerId) {
		Optional<PayrollOfficer> payrollOfficer = payrollOfficerService.getPayrollOfficerById(payrollOfficerId);
		if (payrollOfficer.isEmpty()) {
			return new ResponseEntity<>("The payroll officer with that id does not exist", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(employeeService.getEmployeesOfPayrollOfficer(payrollOfficerId), HttpStatus.OK);
		}
	}
}

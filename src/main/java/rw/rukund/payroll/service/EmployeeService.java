package rw.rukund.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.rukund.payroll.model.Employee;
import rw.rukund.payroll.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public String saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "Employee saved successfully";
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployeeById(UUID id) {
		return employeeRepository.findById(id);
	}

	public String updateEmployee(UUID id, Employee employee) {
		Optional<Employee> existing = employeeRepository.findById(id);
		if (existing.isEmpty()) {
			return "The employee with that id does not exist";
		}
		Employee found = existing.get();
		found.setFirstName(employee.getFirstName());
		found.setLastName(employee.getLastName());
		found.setEmployeeNumber(employee.getEmployeeNumber());
		found.setPosition(employee.getPosition());
		employeeRepository.save(found);
		return "Employee updated successfully";
	}

	public String deleteEmployee(UUID id) {
		if (!employeeRepository.existsById(id)) {
			return "The employee with that id does not exist";
		}
		employeeRepository.deleteById(id);
		return "Employee deleted successfully";
	}

	public List<Employee> getEmployeesByPosition(String position) {
		return employeeRepository.findByPositionIgnoreCaseOrderByLastNameAsc(position);
	}

	public List<Employee> getEmployeesOfPayrollOfficer(UUID payrollOfficerId) {
		return employeeRepository.findEmployeesOfPayrollOfficer(payrollOfficerId);
	}
}

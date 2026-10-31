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
import rw.rukund.payroll.model.PayrollOfficer;
import rw.rukund.payroll.service.PayrollOfficerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/payroll-officers")
public class PayrollOfficerController {

	@Autowired
	private PayrollOfficerService payrollOfficerService;

	@PostMapping("/save")
	public ResponseEntity<String> savePayrollOfficer(@RequestBody PayrollOfficer payrollOfficer) {
		String message = payrollOfficerService.savePayrollOfficer(payrollOfficer);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PayrollOfficer>> getAllPayrollOfficers() {
		return new ResponseEntity<>(payrollOfficerService.getAllPayrollOfficers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPayrollOfficerById(@PathVariable UUID id) {
		Optional<PayrollOfficer> payrollOfficer = payrollOfficerService.getPayrollOfficerById(id);
		if (payrollOfficer.isPresent()) {
			return new ResponseEntity<>(payrollOfficer.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The payroll officer with that id does not exist", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updatePayrollOfficer(@PathVariable UUID id, @RequestBody PayrollOfficer payrollOfficer) {
		String message = payrollOfficerService.updatePayrollOfficer(id, payrollOfficer);
		if (message.equals("Payroll officer updated successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePayrollOfficer(@PathVariable UUID id) {
		String message = payrollOfficerService.deletePayrollOfficer(id);
		if (message.equals("Payroll officer deleted successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/by-payroll-area")
	public ResponseEntity<List<PayrollOfficer>> getPayrollOfficersByPayrollArea(@RequestParam String name) {
		return new ResponseEntity<>(payrollOfficerService.getPayrollOfficersByPayrollArea(name), HttpStatus.OK);
	}
}

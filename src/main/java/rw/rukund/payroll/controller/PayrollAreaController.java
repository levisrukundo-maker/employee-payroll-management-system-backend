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
import org.springframework.web.bind.annotation.RestController;
import rw.rukund.payroll.model.PayrollArea;
import rw.rukund.payroll.service.PayrollAreaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/payroll-areas")
public class PayrollAreaController {

	@Autowired
	private PayrollAreaService payrollAreaService;

	@PostMapping("/save")
	public ResponseEntity<String> savePayrollArea(@RequestBody PayrollArea payrollArea) {
		String message = payrollAreaService.savePayrollArea(payrollArea);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PayrollArea>> getAllPayrollAreas() {
		return new ResponseEntity<>(payrollAreaService.getAllPayrollAreas(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPayrollAreaById(@PathVariable UUID id) {
		Optional<PayrollArea> payrollArea = payrollAreaService.getPayrollAreaById(id);
		if (payrollArea.isPresent()) {
			return new ResponseEntity<>(payrollArea.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The payroll area with that id does not exist", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updatePayrollArea(@PathVariable UUID id, @RequestBody PayrollArea payrollArea) {
		String message = payrollAreaService.updatePayrollArea(id, payrollArea);
		if (message.equals("Payroll area updated successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePayrollArea(@PathVariable UUID id) {
		String message = payrollAreaService.deletePayrollArea(id);
		if (message.equals("Payroll area deleted successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/unused")
	public ResponseEntity<List<PayrollArea>> getUnusedPayrollAreas() {
		return new ResponseEntity<>(payrollAreaService.getUnusedPayrollAreas(), HttpStatus.OK);
	}
}

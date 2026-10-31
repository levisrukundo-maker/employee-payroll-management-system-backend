package rw.rukund.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.rukund.payroll.model.Payslip;
import rw.rukund.payroll.model.PayslipStatus;
import rw.rukund.payroll.service.PayslipService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/payslips")
public class PayslipController {

	@Autowired
	private PayslipService payslipService;

	@PostMapping("/save")
	public ResponseEntity<String> savePayslip(@RequestBody Payslip payslip) {
		String message = payslipService.savePayslip(payslip);
		if (message.equals("Payslip saved successfully")) {
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} else if (message.equals("Payslip date cannot be in the past")) {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(message, HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Payslip>> getAllPayslips() {
		return new ResponseEntity<>(payslipService.getAllPayslips(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPayslipById(@PathVariable UUID id) {
		Optional<Payslip> payslip = payslipService.getPayslipById(id);
		if (payslip.isPresent()) {
			return new ResponseEntity<>(payslip.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The payslip with that id does not exist", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updatePayslip(@PathVariable UUID id, @RequestBody Payslip payslip) {
		String message = payslipService.updatePayslip(id, payslip);
		if (message.equals("Payslip updated successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePayslip(@PathVariable UUID id) {
		String message = payslipService.deletePayslip(id);
		if (message.equals("Payslip deleted successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/by-status")
	public ResponseEntity<List<Payslip>> getPayslipsByStatus(@RequestParam PayslipStatus status) {
		return new ResponseEntity<>(payslipService.getPayslipsByStatus(status), HttpStatus.OK);
	}

	@GetMapping("/between")
	public ResponseEntity<List<Payslip>> getPayslipsBetween(@RequestParam String start, @RequestParam String end) {
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		return new ResponseEntity<>(payslipService.getPayslipsBetween(startDate, endDate), HttpStatus.OK);
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<String> updatePayslipStatus(@PathVariable UUID id, @RequestParam PayslipStatus status) {
		String message = payslipService.updatePayslipStatus(id, status);
		if (message.equals("Payslip status updated successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else if (message.equals("The payslip with that id does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}

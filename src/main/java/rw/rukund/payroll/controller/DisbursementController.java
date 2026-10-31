package rw.rukund.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.rukund.payroll.model.Disbursement;
import rw.rukund.payroll.service.DisbursementService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/disbursements")
public class DisbursementController {

	@Autowired
	private DisbursementService disbursementService;

	@PostMapping("/save")
	public ResponseEntity<String> saveDisbursement(@RequestParam UUID payslipId, @RequestBody Disbursement disbursement) {
		String message = disbursementService.saveDisbursement(payslipId, disbursement);
		if (message.equals("Disbursement saved successfully")) {
			return new ResponseEntity<>(message, HttpStatus.CREATED);
		} else if (message.equals("The payslip with that id does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Disbursement>> getAllDisbursements() {
		return new ResponseEntity<>(disbursementService.getAllDisbursements(), HttpStatus.OK);
	}

	@GetMapping("/employee/{employeeNumber}")
	public ResponseEntity<List<Disbursement>> getDisbursementsOfEmployee(@PathVariable String employeeNumber) {
		return new ResponseEntity<>(disbursementService.getDisbursementsOfEmployee(employeeNumber), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDisbursement(@PathVariable UUID id) {
		String message = disbursementService.deleteDisbursement(id);
		if (message.equals("Disbursement deleted successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
}

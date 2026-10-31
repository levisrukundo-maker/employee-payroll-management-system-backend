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
import rw.rukund.payroll.model.Branch;
import rw.rukund.payroll.service.BranchService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

	@Autowired
	private BranchService branchService;

	@PostMapping("/save")
	public ResponseEntity<String> saveBranch(@RequestBody Branch branch) {
		String message = branchService.saveBranch(branch);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Branch>> getAllBranches() {
		return new ResponseEntity<>(branchService.getAllBranches(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBranchById(@PathVariable UUID id) {
		Optional<Branch> branch = branchService.getBranchById(id);
		if (branch.isPresent()) {
			return new ResponseEntity<>(branch.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The branch with that id does not exist", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateBranch(@PathVariable UUID id, @RequestBody Branch branch) {
		String message = branchService.updateBranch(id, branch);
		if (message.equals("Branch updated successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBranch(@PathVariable UUID id) {
		String message = branchService.deleteBranch(id);
		if (message.equals("Branch deleted successfully")) {
			return new ResponseEntity<>(message, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/free")
	public ResponseEntity<List<Branch>> getFreeBranches() {
		return new ResponseEntity<>(branchService.getFreeBranches(), HttpStatus.OK);
	}
}

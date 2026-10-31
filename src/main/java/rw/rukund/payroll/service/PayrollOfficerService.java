package rw.rukund.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.rukund.payroll.model.Branch;
import rw.rukund.payroll.model.PayrollArea;
import rw.rukund.payroll.model.PayrollOfficer;
import rw.rukund.payroll.repository.BranchRepository;
import rw.rukund.payroll.repository.PayrollAreaRepository;
import rw.rukund.payroll.repository.PayrollOfficerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PayrollOfficerService {

	@Autowired
	private PayrollOfficerRepository payrollOfficerRepository;

	@Autowired
	private PayrollAreaRepository payrollAreaRepository;

	@Autowired
	private BranchRepository branchRepository;

	public String savePayrollOfficer(PayrollOfficer payrollOfficer) {
		payrollOfficerRepository.save(payrollOfficer);
		return "Payroll officer saved successfully";
	}

	public List<PayrollOfficer> getAllPayrollOfficers() {
		return payrollOfficerRepository.findAll();
	}

	public Optional<PayrollOfficer> getPayrollOfficerById(UUID id) {
		return payrollOfficerRepository.findById(id);
	}

	public String updatePayrollOfficer(UUID id, PayrollOfficer payrollOfficer) {
		Optional<PayrollOfficer> existing = payrollOfficerRepository.findById(id);
		if (existing.isEmpty()) {
			return "The payroll officer with that id does not exist";
		}
		PayrollOfficer found = existing.get();
		found.setFirstName(payrollOfficer.getFirstName());
		found.setLastName(payrollOfficer.getLastName());
		found.setEmail(payrollOfficer.getEmail());
		found.setBranch(payrollOfficer.getBranch());
		found.setPayrollAreas(payrollOfficer.getPayrollAreas());
		payrollOfficerRepository.save(found);
		return "Payroll officer updated successfully";
	}

	public String deletePayrollOfficer(UUID id) {
		if (!payrollOfficerRepository.existsById(id)) {
			return "The payroll officer with that id does not exist";
		}
		payrollOfficerRepository.deleteById(id);
		return "Payroll officer deleted successfully";
	}

	public List<PayrollOfficer> getPayrollOfficersByPayrollArea(String name) {
		return payrollOfficerRepository.findByPayrollAreaName(name);
	}

	public String addPayrollArea(UUID payrollOfficerId, UUID payrollAreaId) {
		Optional<PayrollOfficer> existingOfficer = payrollOfficerRepository.findById(payrollOfficerId);
		if (existingOfficer.isEmpty()) {
			return "The payroll officer with that id does not exist";
		}
		Optional<PayrollArea> existingArea = payrollAreaRepository.findById(payrollAreaId);
		if (existingArea.isEmpty()) {
			return "That payroll area does not exist";
		}
		PayrollOfficer payrollOfficer = existingOfficer.get();
		PayrollArea payrollArea = existingArea.get();
		if (payrollOfficer.getPayrollAreas().contains(payrollArea)) {
			return "The payroll officer already has that payroll area";
		}
		payrollOfficer.getPayrollAreas().add(payrollArea);
		payrollOfficerRepository.save(payrollOfficer);
		return "Payroll area added to the payroll officer";
	}

	public String changeBranch(UUID payrollOfficerId, int branchNumber) {
		Optional<PayrollOfficer> existingOfficer = payrollOfficerRepository.findById(payrollOfficerId);
		if (existingOfficer.isEmpty()) {
			return "The payroll officer with that id does not exist";
		}
		Optional<Branch> existingBranch = branchRepository.findByBranchNumber(branchNumber);
		if (existingBranch.isEmpty()) {
			return "The branch with that branchNumber does not exist";
		}
		Branch branch = existingBranch.get();
		if (payrollOfficerRepository.existsByBranch(branch)) {
			return "That branch is taken by another payroll officer";
		}
		PayrollOfficer payrollOfficer = existingOfficer.get();
		payrollOfficer.setBranch(branch);
		payrollOfficerRepository.save(payrollOfficer);
		return "Branch changed successfully";
	}
}

package rw.rukund.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.rukund.payroll.model.PayrollOfficer;
import rw.rukund.payroll.repository.PayrollOfficerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PayrollOfficerService {

	@Autowired
	private PayrollOfficerRepository payrollOfficerRepository;

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
}

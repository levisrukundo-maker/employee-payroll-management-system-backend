package rw.rukund.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.rukund.payroll.model.Payslip;
import rw.rukund.payroll.repository.PayslipRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PayslipService {

	@Autowired
	private PayslipRepository payslipRepository;

	public String savePayslip(Payslip payslip) {
		payslipRepository.save(payslip);
		return "Payslip saved successfully";
	}

	public List<Payslip> getAllPayslips() {
		return payslipRepository.findAll();
	}

	public Optional<Payslip> getPayslipById(UUID id) {
		return payslipRepository.findById(id);
	}

	public String updatePayslip(UUID id, Payslip payslip) {
		Optional<Payslip> existing = payslipRepository.findById(id);
		if (existing.isEmpty()) {
			return "The payslip with that id does not exist";
		}
		Payslip found = existing.get();
		found.setPayDate(payslip.getPayDate());
		found.setStatus(payslip.getStatus());
		found.setPeriod(payslip.getPeriod());
		found.setGrossSalary(payslip.getGrossSalary());
		found.setEmployee(payslip.getEmployee());
		found.setPayrollOfficer(payslip.getPayrollOfficer());
		payslipRepository.save(found);
		return "Payslip updated successfully";
	}

	public String deletePayslip(UUID id) {
		if (!payslipRepository.existsById(id)) {
			return "The payslip with that id does not exist";
		}
		payslipRepository.deleteById(id);
		return "Payslip deleted successfully";
	}
}

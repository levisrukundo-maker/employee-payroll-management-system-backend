package rw.rukund.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.rukund.payroll.model.PayrollArea;
import rw.rukund.payroll.repository.PayrollAreaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PayrollAreaService {

	@Autowired
	private PayrollAreaRepository payrollAreaRepository;

	public String savePayrollArea(PayrollArea payrollArea) {
		payrollAreaRepository.save(payrollArea);
		return "Payroll area saved successfully";
	}

	public List<PayrollArea> getAllPayrollAreas() {
		return payrollAreaRepository.findAll();
	}

	public Optional<PayrollArea> getPayrollAreaById(UUID id) {
		return payrollAreaRepository.findById(id);
	}

	public String updatePayrollArea(UUID id, PayrollArea payrollArea) {
		Optional<PayrollArea> existing = payrollAreaRepository.findById(id);
		if (existing.isEmpty()) {
			return "The payroll area with that id does not exist";
		}
		PayrollArea found = existing.get();
		found.setName(payrollArea.getName());
		payrollAreaRepository.save(found);
		return "Payroll area updated successfully";
	}

	public String deletePayrollArea(UUID id) {
		if (!payrollAreaRepository.existsById(id)) {
			return "The payroll area with that id does not exist";
		}
		payrollAreaRepository.deleteById(id);
		return "Payroll area deleted successfully";
	}
}

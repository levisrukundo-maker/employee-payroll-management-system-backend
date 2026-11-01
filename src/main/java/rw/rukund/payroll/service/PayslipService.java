package rw.rukund.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.rukund.payroll.model.Payslip;
import rw.rukund.payroll.model.PayslipStatus;
import rw.rukund.payroll.repository.DisbursementRepository;
import rw.rukund.payroll.repository.PayslipRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PayslipService {

	@Autowired
	private PayslipRepository payslipRepository;

	@Autowired
	private DisbursementRepository disbursementRepository;

	public String savePayslip(Payslip payslip) {
		if (payslip.getPayDate().isBefore(LocalDate.now())) {
			return "Payslip date cannot be in the past";
		}
		if (payslipRepository.existsByPayrollOfficerAndPayDateAndStatusNot(payslip.getPayrollOfficer(), payslip.getPayDate(), PayslipStatus.REJECTED)) {
			return "Payroll officer already processes a payslip on that date";
		}
		payslip.setStatus(PayslipStatus.DRAFTED);
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
		found.setPeriod(payslip.getPeriod());
		found.setGrossSalary(payslip.getGrossSalary());
		found.setEmployee(payslip.getEmployee());
		found.setPayrollOfficer(payslip.getPayrollOfficer());
		payslipRepository.save(found);
		return "Payslip updated successfully";
	}

	public String deletePayslip(UUID id) {
		Optional<Payslip> existing = payslipRepository.findById(id);
		if (existing.isEmpty()) {
			return "The payslip with that id does not exist";
		}
		if (disbursementRepository.existsByPayslip(existing.get())) {
			return "That payslip has disbursements, delete them first";
		}
		payslipRepository.deleteById(id);
		return "Payslip deleted successfully";
	}

	public List<Payslip> getPayslipsByStatus(PayslipStatus status) {
		return payslipRepository.findByStatusOrderByPayDateAsc(status);
	}

	public List<Payslip> getPayslipsBetween(LocalDate start, LocalDate end) {
		return payslipRepository.findByPayDateBetweenOrderByPayDateAsc(start, end);
	}

	public String updatePayslipStatus(UUID id, PayslipStatus status) {
		Optional<Payslip> existing = payslipRepository.findById(id);
		if (existing.isEmpty()) {
			return "The payslip with that id does not exist";
		}
		Payslip payslip = existing.get();
		PayslipStatus current = payslip.getStatus();
		boolean allowed = false;
		if (current == PayslipStatus.DRAFTED && (status == PayslipStatus.REVIEWED || status == PayslipStatus.REJECTED)) {
			allowed = true;
		} else if (current == PayslipStatus.REVIEWED && (status == PayslipStatus.APPROVED || status == PayslipStatus.REJECTED)) {
			allowed = true;
		}
		if (!allowed) {
			return "Cannot change status from " + current + " to " + status;
		}
		payslip.setStatus(status);
		payslipRepository.save(payslip);
		return "Payslip status updated successfully";
	}

	public List<Object[]> countPayslipsByStatus() {
		return payslipRepository.countPayslipsByStatus();
	}

	@Transactional
	public String cancelDay(UUID payrollOfficerId, LocalDate date) {
		int cancelled = payslipRepository.cancelDay(payrollOfficerId, date, PayslipStatus.REJECTED, PayslipStatus.APPROVED);
		return cancelled + " payslips cancelled";
	}
}

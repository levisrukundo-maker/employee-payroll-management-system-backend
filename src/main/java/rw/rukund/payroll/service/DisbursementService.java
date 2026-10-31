package rw.rukund.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.rukund.payroll.model.Disbursement;
import rw.rukund.payroll.model.Payslip;
import rw.rukund.payroll.model.PayslipStatus;
import rw.rukund.payroll.repository.DisbursementRepository;
import rw.rukund.payroll.repository.PayslipRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DisbursementService {

	@Autowired
	private DisbursementRepository disbursementRepository;

	@Autowired
	private PayslipRepository payslipRepository;

	public String saveDisbursement(UUID payslipId, Disbursement disbursement) {
		Optional<Payslip> existing = payslipRepository.findById(payslipId);
		if (existing.isEmpty()) {
			return "The payslip with that id does not exist";
		}
		Payslip payslip = existing.get();
		if (payslip.getStatus() != PayslipStatus.APPROVED) {
			return "Disbursements are only for APPROVED payslips";
		}
		disbursement.setDisbursedDate(LocalDate.now());
		disbursement.setPayslip(payslip);
		disbursementRepository.save(disbursement);
		return "Disbursement saved successfully";
	}

	public List<Disbursement> getAllDisbursements() {
		return disbursementRepository.findAll();
	}

	public List<Disbursement> getDisbursementsOfEmployee(String employeeNumber) {
		return disbursementRepository.findByEmployeeNumber(employeeNumber);
	}

	public String deleteDisbursement(UUID id) {
		if (!disbursementRepository.existsById(id)) {
			return "The disbursement with that id does not exist";
		}
		disbursementRepository.deleteById(id);
		return "Disbursement deleted successfully";
	}
}

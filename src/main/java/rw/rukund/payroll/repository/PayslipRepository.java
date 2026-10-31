package rw.rukund.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.rukund.payroll.model.PayrollOfficer;
import rw.rukund.payroll.model.Payslip;
import rw.rukund.payroll.model.PayslipStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PayslipRepository extends JpaRepository<Payslip, UUID> {

	List<Payslip> findByStatusOrderByPayDateAsc(PayslipStatus status);

	List<Payslip> findByPayDateBetweenOrderByPayDateAsc(LocalDate start, LocalDate end);

	boolean existsByPayrollOfficerAndPayDateAndStatusNot(PayrollOfficer payrollOfficer, LocalDate payDate, PayslipStatus status);
}

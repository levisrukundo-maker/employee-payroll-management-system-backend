package rw.rukund.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Query("SELECT p.status, COUNT(p) FROM Payslip p GROUP BY p.status")
	List<Object[]> countPayslipsByStatus();

	@Modifying
	@Query("UPDATE Payslip p SET p.status = :rejected WHERE p.payrollOfficer.id = :payrollOfficerId AND p.payDate = :date AND p.status <> :approved")
	int cancelDay(@Param("payrollOfficerId") UUID payrollOfficerId, @Param("date") LocalDate date, @Param("rejected") PayslipStatus rejected, @Param("approved") PayslipStatus approved);
}

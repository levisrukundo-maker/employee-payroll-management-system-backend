package rw.rukund.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.rukund.payroll.model.Disbursement;
import rw.rukund.payroll.model.Payslip;

import java.util.List;
import java.util.UUID;

@Repository
public interface DisbursementRepository extends JpaRepository<Disbursement, UUID> {

	@Query("SELECT d FROM Disbursement d WHERE d.payslip.employee.employeeNumber = :employeeNumber ORDER BY d.disbursedDate DESC")
	List<Disbursement> findByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

	boolean existsByPayslip(Payslip payslip);
}

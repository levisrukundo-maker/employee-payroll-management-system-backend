package rw.rukund.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.rukund.payroll.model.Employee;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	List<Employee> findByPositionIgnoreCaseOrderByLastNameAsc(String position);

	@Query("SELECT DISTINCT p.employee FROM Payslip p WHERE p.payrollOfficer.id = :payrollOfficerId")
	List<Employee> findEmployeesOfPayrollOfficer(@Param("payrollOfficerId") UUID payrollOfficerId);
}

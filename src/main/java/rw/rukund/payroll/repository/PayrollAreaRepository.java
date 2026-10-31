package rw.rukund.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.rukund.payroll.model.PayrollArea;

import java.util.List;
import java.util.UUID;

@Repository
public interface PayrollAreaRepository extends JpaRepository<PayrollArea, UUID> {

	@Query("SELECT a FROM PayrollArea a WHERE a.payrollOfficers IS EMPTY")
	List<PayrollArea> findUnusedPayrollAreas();
}

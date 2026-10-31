package rw.rukund.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.rukund.payroll.model.Branch;
import rw.rukund.payroll.model.PayrollOfficer;

import java.util.List;
import java.util.UUID;

@Repository
public interface PayrollOfficerRepository extends JpaRepository<PayrollOfficer, UUID> {

	@Query("SELECT p FROM PayrollOfficer p JOIN p.payrollAreas a WHERE LOWER(a.name) = LOWER(:name)")
	List<PayrollOfficer> findByPayrollAreaName(@Param("name") String name);

	boolean existsByBranch(Branch branch);
}

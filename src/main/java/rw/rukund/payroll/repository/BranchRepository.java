package rw.rukund.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.rukund.payroll.model.Branch;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {

	@Query("SELECT b FROM Branch b WHERE NOT EXISTS (SELECT p FROM PayrollOfficer p WHERE p.branch = b) ORDER BY b.branchNumber")
	List<Branch> findFreeBranches();

	Optional<Branch> findByBranchNumber(int branchNumber);
}

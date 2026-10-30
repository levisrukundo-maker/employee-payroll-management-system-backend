package rw.rukund.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.rukund.payroll.model.PayrollOfficer;

import java.util.UUID;

@Repository
public interface PayrollOfficerRepository extends JpaRepository<PayrollOfficer, UUID> {
}

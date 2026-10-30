package rw.rukund.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.rukund.payroll.model.PayrollArea;

import java.util.UUID;

@Repository
public interface PayrollAreaRepository extends JpaRepository<PayrollArea, UUID> {
}

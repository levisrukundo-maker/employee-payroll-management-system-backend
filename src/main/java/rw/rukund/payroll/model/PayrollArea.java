package rw.rukund.payroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payroll_area")
public class PayrollArea {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String name;

	@ManyToMany(mappedBy = "payrollAreas")
	@JsonIgnore
	private List<PayrollOfficer> payrollOfficers = new ArrayList<>();

	public PayrollArea() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PayrollOfficer> getPayrollOfficers() {
		return payrollOfficers;
	}

	public void setPayrollOfficers(List<PayrollOfficer> payrollOfficers) {
		this.payrollOfficers = payrollOfficers;
	}
}

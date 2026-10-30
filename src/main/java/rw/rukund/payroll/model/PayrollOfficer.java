package rw.rukund.payroll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payroll_officer")
public class PayrollOfficer {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String firstName;

	private String lastName;

	private String email;

	@ManyToOne
	@JoinColumn(name = "branch_id", nullable = true)
	private Branch branch;

	@ManyToMany
	@JoinTable(
			name = "payroll_officer_payroll_area",
			joinColumns = @JoinColumn(name = "payroll_officer_id"),
			inverseJoinColumns = @JoinColumn(name = "payroll_area_id")
	)
	private List<PayrollArea> payrollAreas = new ArrayList<>();

	public PayrollOfficer() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<PayrollArea> getPayrollAreas() {
		return payrollAreas;
	}

	public void setPayrollAreas(List<PayrollArea> payrollAreas) {
		this.payrollAreas = payrollAreas;
	}
}

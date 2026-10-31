package rw.rukund.payroll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "disbursement")
public class Disbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String paymentChannel;

	private String reference;

	private int amountRwf;

	private LocalDate disbursedDate;

	@ManyToOne
	@JoinColumn(name = "payslip_id")
	private Payslip payslip;

	public Disbursement() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getPaymentChannel() {
		return paymentChannel;
	}

	public void setPaymentChannel(String paymentChannel) {
		this.paymentChannel = paymentChannel;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getAmountRwf() {
		return amountRwf;
	}

	public void setAmountRwf(int amountRwf) {
		this.amountRwf = amountRwf;
	}

	public LocalDate getDisbursedDate() {
		return disbursedDate;
	}

	public void setDisbursedDate(LocalDate disbursedDate) {
		this.disbursedDate = disbursedDate;
	}

	public Payslip getPayslip() {
		return payslip;
	}

	public void setPayslip(Payslip payslip) {
		this.payslip = payslip;
	}
}

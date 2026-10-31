package rw.rukund.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.rukund.payroll.model.Branch;
import rw.rukund.payroll.repository.BranchRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BranchService {

	@Autowired
	private BranchRepository branchRepository;

	public String saveBranch(Branch branch) {
		branchRepository.save(branch);
		return "Branch saved successfully";
	}

	public List<Branch> getAllBranches() {
		return branchRepository.findAll();
	}

	public Optional<Branch> getBranchById(UUID id) {
		return branchRepository.findById(id);
	}

	public String updateBranch(UUID id, Branch branch) {
		Optional<Branch> existing = branchRepository.findById(id);
		if (existing.isEmpty()) {
			return "The branch with that id does not exist";
		}
		Branch found = existing.get();
		found.setBranchNumber(branch.getBranchNumber());
		found.setName(branch.getName());
		branchRepository.save(found);
		return "Branch updated successfully";
	}

	public String deleteBranch(UUID id) {
		if (!branchRepository.existsById(id)) {
			return "The branch with that id does not exist";
		}
		branchRepository.deleteById(id);
		return "Branch deleted successfully";
	}

	public List<Branch> getFreeBranches() {
		return branchRepository.findFreeBranches();
	}
}

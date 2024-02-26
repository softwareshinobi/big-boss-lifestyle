package digital.agenteight.bigbosslifestyle.experience;

import digital.agenteight.bigbosslifestyle.experience.BudgetRepository;
import digital.agenteight.bigbosslifestyle.experience.Budget;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = SQLException.class)
public class BudgetService {

    BudgetRepository budgetRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }


    public Budget saveBudget(Budget budget) {
        return this.budgetRepository.save(budget);
    }

    public List<Budget> getBudgetsForUser(Integer userId) {
        return this.budgetRepository.findByUserId(userId);
    }

    public void delete(Integer id) {
        budgetRepository.deleteById(id);
    }

    public Optional<Budget> get(Integer id) {
        return budgetRepository.findById(id);
    }


}

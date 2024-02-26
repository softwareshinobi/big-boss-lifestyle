package digital.agenteight.bigbosslifestyle.experience;

import digital.agenteight.bigbosslifestyle.experience.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    Optional<Budget> findByName (String name);
    Optional<Budget> findById (Integer id);

    List<Budget> findByUserId (Integer userId);

}


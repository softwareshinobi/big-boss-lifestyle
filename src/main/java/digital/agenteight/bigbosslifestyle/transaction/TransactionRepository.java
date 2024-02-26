package digital.agenteight.bigbosslifestyle.transaction;

import digital.agenteight.bigbosslifestyle.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> findByName (String name);
    List<Transaction> findByUserId (Integer userId);

    Optional<Transaction> findById (Integer id);
    Optional<Transaction> findByAmount (Double amount);

    Optional<Transaction> findByDate (Date date);

    Optional<Transaction> findByType (String type);
    Optional<Transaction> findByDescription (String description);
}
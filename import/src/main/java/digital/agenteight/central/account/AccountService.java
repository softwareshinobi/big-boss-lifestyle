package digital.agenteight.central.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = SQLException.class)
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> get() {
        return this.accountRepository.findAll();
    }

    public Account authenticateAccount(Account account) {

        Optional<Account> accountOptional = this.accountRepository.
                findByEmailAllIgnoreCase(account.getEmail());

        if (accountOptional.isPresent() && accountOptional.get().getPassword().equals(account.getPassword())) {
            return accountOptional.get();
        } else {
            return null;
        }
    }

    public Account saveAccount(Account account) {
        return this.accountRepository.save(account);
    }

    public void deleteAccount(Integer id) {
        this.accountRepository.deleteById(id);
    }

}

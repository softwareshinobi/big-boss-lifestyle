package digital.agenteight.bigbosslifestyle.home;

import digital.agenteight.bigbosslifestyle.experience.BudgetRepository;
import digital.agenteight.bigbosslifestyle.transaction.TransactionRepository;
import digital.agenteight.bigbosslifestyle.user.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    MyUserRepository userRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    public HomeController(MyUserRepository userRepository, BudgetRepository budgetRepository, TransactionRepository transactionRepository) {

        this.userRepository = userRepository;

        this.budgetRepository = budgetRepository;

        this.transactionRepository = transactionRepository;

    }

    @GetMapping("/")
    public String goToIndex(Model model) {

        model.addAttribute("info", "be great");

        return "landing";

    }

}

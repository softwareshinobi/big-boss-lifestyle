package digital.agenteight.bigbosslifestyle.transaction;

import digital.agenteight.bigbosslifestyle.account.Account;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/addTransactionForm")
    public String addTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("formTitle", "Add Transaction");
        return "transaction/addTransactionForm";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, Model model, HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("currentUser");
        if (obj == null) {
            model.addAttribute("error", "Login session expired");
            return "redirect:/user/loginForm";
        }

        transactionService.delete(id);
        return "redirect:/transaction/viewTransactions";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, Model model, HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("currentUser");
        if (obj == null) {
            model.addAttribute("error", "Login session expired");
            return "redirect:/user/loginForm";
        }

        Optional<Transaction> transactionOptional = transactionService.get(id);
        if (transactionOptional.isPresent()) {
            model.addAttribute("transaction", transactionOptional.get());
            model.addAttribute("formTitle", "Edit Transaction");
            return "transaction/editTransactionForm";
        } else {
            model.addAttribute("error", "Transaction not found for id: " + id);
            return "redirect:/transaction/viewTransactions";
        }
    }

    @PostMapping("/saveTransaction")
    public String saveTransaction(@ModelAttribute Transaction transaction, Model model, HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("currentUser");
        if (obj == null) {
            model.addAttribute("error", "Login session expired");
            return "redirect:/user/loginForm";
        } else {
            Account currentUser = (Account) obj;
            model.addAttribute("myUser", currentUser);
            transaction.setUserId(currentUser.getId());

            transactionService.saveTransaction(transaction);

            Transaction savedTransaction = this.transactionService.saveTransaction(transaction);
            if (savedTransaction != null) {
                model.addAttribute("info", "Save Transaction success!");
                model.addAttribute("transaction", savedTransaction);
            } else {
                model.addAttribute("transaction", transaction);
                model.addAttribute("error", "Save Transaction Failed!");
            }
            model.addAttribute("formTitle", "Add Transaction");
            return "transaction/addTransactionForm";
        }
    }

    @GetMapping("/viewTransactions")
    public String viewTransactions(Model model, HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("currentUser");
        if (obj == null) {
            model.addAttribute("error", "Login session expired");
            return "redirect:/user/loginForm";
        } else {
            Account currentUser = (Account) obj;
            model.addAttribute("myUser", currentUser);

            List<Transaction> transactions = transactionService.getTransactionsForUser(currentUser.getId());
            if (transactions != null) {
                model.addAttribute("transactions", transactions);
            } else {
                model.addAttribute("transactions", new ArrayList<>());
                return "transaction/transactionList";
            }
            return "transaction/transactionList";
        }
    }

}

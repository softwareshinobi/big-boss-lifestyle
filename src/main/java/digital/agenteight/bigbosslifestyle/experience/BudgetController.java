package digital.agenteight.bigbosslifestyle.experience;

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
@RequestMapping("experience")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/addBudgetForm")
    public String addBudgetForm(Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        }

        model.addAttribute("budget", new Budget("88"));

        model.addAttribute("formTitle", "Add Budget");

        return "budget/addBudgetForm";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        }

        budgetService.delete(id);

        return "redirect:/budget/viewBudgets";

    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        }

        Optional<Budget> budgetOptional = budgetService.get(id);

        if (budgetOptional.isPresent()) {

            model.addAttribute("budget", budgetOptional.get());

            model.addAttribute("formTitle", "Edit Budget");

            return "budget/editBudgetForm";

        } else {

            model.addAttribute("error", "Budget not found for id: " + id);

            return "redirect:/budget/viewBudgets";

        }

    }

    @PostMapping("/save")
    public String saveBudget(@ModelAttribute Budget budget, Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        } else {

            Account currentUser = (Account) obj;

            model.addAttribute("myUser", currentUser);

            budget.setUserId(currentUser.getId());

            budgetService.saveBudget(budget);

            Budget savedBudget = this.budgetService.saveBudget(budget);

            if (savedBudget != null) {

                model.addAttribute("info", "Save Budget success!");

                model.addAttribute("budget", savedBudget);

            } else {

                model.addAttribute("budget", budget);

                model.addAttribute("error", "Save Budget Failed!");

                return "budget/addBudgetForm";

            }

            return "budget/addBudgetForm";

        }

    }

    @GetMapping("/")
    public String viewBudgets(Model model, HttpServletRequest request) {
        System.out.println("here?");
        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {
            //  if (false) {
            System.out.println("3333here?");
            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        } else {
            System.out.println("wxxxxxre?");
            Account currentUser = (Account) obj;

            model.addAttribute("myUser", currentUser);

            List<Budget> budgets = budgetService.getBudgetsForUser(currentUser.getId());

            Double sum = 0d;

            for (Budget budget : budgets) {

                sum = sum + budget.getAmount();

            }

            model.addAttribute("daily", sum);
//model.addAttribute("lifestyle-sum-day", sum);
            model.addAttribute("weekly", sum * 7);
            model.addAttribute("monthly", sum * 30.5);
            model.addAttribute("yearly", sum * 365);

            model.addAttribute("info", "info");
            model.addAttribute("error", "error");

            if (budgets != null) {

                model.addAttribute("budgets", budgets);

            } else {

                model.addAttribute("budgets", new ArrayList<>());

                //todo i think this can be removed
                return "budget/budgetList";

            }

            return "budget/budgetList";

        }

    }

}

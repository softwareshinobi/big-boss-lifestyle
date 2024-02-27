package digital.agenteight.central.account;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    public AccountController() {

        System.out.println("AccountController AccountController AccountController");

    }

    @Autowired
    public AccountController(AccountService accountService) {
        System.out.println("AccountController AccountController AccountController");

        this.accountService = accountService;

    }

    @GetMapping("/health-check")
    public String renderNewAccddddountForm() {

        return "hello";

    }

    @GetMapping("/create-account-form")
    public String renderNewAccountForm(Model model) {

        model.addAttribute("account", new Account());

        return "account/register";

    }

    @PostMapping("/create-account-submit")
    public String commitNewAccount(@ModelAttribute Account account, Model model) {

        System.out.println("create-account-submit create-account-submit create-account-submit");

        Account savedAccount = this.accountService.saveAccount(account);

        if (savedAccount != null) {

            model.addAttribute("info", "Signup success!");

            return "account/login";

        } else {

            model.addAttribute("error", "Signup Failed");

            return "account/register";

        }

    }

    @GetMapping("/open-session-form")
    public String renderOpenSessionForm(Model model) {

        model.addAttribute("account", new Account());

        model.addAttribute("error", "");

        return "account/login";

    }

    @PostMapping("/open-session-submit")
    public String openSession(@ModelAttribute Account account, Model model, HttpServletRequest request) {

        Account currentAccount = this.accountService.authenticateAccount(account);

        if (currentAccount != null) {

            model.addAttribute("account", currentAccount);

            request.getSession(true).setAttribute("currentUser", currentAccount);

            return "redirect:/creation/";

        } else {

            model.addAttribute("error", "Invalid credentials. Try Again.");

            return "account/login";

        }

    }

    @GetMapping("/userHome")
    public String userHome(Model model, HttpServletRequest request) {

        Object currentUser = request.getSession().getAttribute("currentUser");

        if (currentUser == null) {

            model.addAttribute("error", "Login session expired");

            return "account/login";

        }

        Account account = (Account) currentUser;

        model.addAttribute("account", account);

        return "redirect:/creation/";

    }

    @GetMapping("/manage-account")
    public String manageAccounts(Model model, HttpServletRequest request) {

        Object currentUser = request.getSession().getAttribute("currentUser");

        if (currentUser == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/account/open-session-form";

        } else {

            Account account = (Account) currentUser;

            model.addAttribute("account", account);

            List<Account> accountList = accountService.get();

            System.out.println("the list of Account:");

            System.out.println(accountList);

            if (accountList != null) {

                model.addAttribute("accountList", accountList);

                return "account/manageUsers";

            } else {

                model.addAttribute("accountList", new ArrayList<>());

                return "account/manageUsers";

            }

        }

    }

    @GetMapping("/delete-account")
    public String deleteAccount(@RequestParam Integer id, Model model, HttpServletRequest request) {

        Object currentUser = request.getSession().getAttribute("currentUser");

        if (currentUser == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/account/open-session-form";

        }

        accountService.deleteAccount(id);

        return "redirect:/account/manage-users";
    }

}

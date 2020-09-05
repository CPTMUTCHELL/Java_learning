package example.controller;

import example.entity.Account;
import example.entity.User;
import example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import example.repository.AccountRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private  AccountRepository accountRepository;
    @Autowired
    private  UserRepository userRepository;

    @GetMapping("/addAccount")
    public String accountForm(String login, Model model, HttpSession session) {
       session.setAttribute("login", login);
        model.addAttribute("account", new Account());
        return "views/accountForm.html";
    }

    @PostMapping("/addAccount")
    public String addAccount(@Valid Account account, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) return "views/accountForm.html";
        if (accountRepository.findByName(account.getName()) != null) {
            model.addAttribute("exist", true);
            return "views/accountForm.html";
        }
        String login = (String) session.getAttribute("login");
        account.setUser(userRepository.findByName(login));
        accountRepository.save(account);
        return "redirect:/profile";
    }
    @GetMapping("/accounts")
    public String showAccounts(@RequestParam("login") String login,Model model){
        User user= userRepository.findByName(login);
        List<Account> accounts=user.getAccounts();
        model.addAttribute("accounts",accounts);
        return "views/accounts.html";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("accountName") String accountName){
        Account account=accountRepository.findByName(accountName);
        accountRepository.delete(account);
        return "redirect:/profile";
    }

}

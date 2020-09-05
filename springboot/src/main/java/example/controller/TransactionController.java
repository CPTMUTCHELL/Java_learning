package example.controller;

import example.entity.Account;
import example.entity.Transaction;
import example.repository.AccountRepository;
import example.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class TransactionController {
    @Autowired
    private  AccountRepository accountRepository;
    @Autowired

    private  TransactionRepository transactionRepository;

    @GetMapping("/createTransaction")
    public String transactionForm(String accountName, HttpSession session, Model model) {
        session.setAttribute("accountName", accountName);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("account",accountRepository.findByName(accountName));

        return "views/transactionForm.html";
    }

    @PostMapping(value = "/createTransaction")
    public String createTransaction(
            @Valid Transaction transaction, @RequestParam("destination") String destination,
            @RequestParam("sum") BigDecimal sum, HttpSession session, BindingResult result,
            Model model) {
        if (result.hasErrors()) return "views/transactionForm.html";
        String accName = (String) session.getAttribute("accountName");
        Account fromAcc = accountRepository.findByName(accName);
        Account toAcc = accountRepository.findByName(destination);
        if (toAcc==null){
            model.addAttribute("no_account_found",true);
            return "views/transactionForm.html";
        }
        transaction.setToAccount(toAcc);
        transaction.setFromAccount(fromAcc);
        if (fromAcc.getBalance().compareTo(sum)<0){
            model.addAttribute("not_enough_money",true);
            return "views/transactionForm.html";
        }
        fromAcc.setBalance(fromAcc.getBalance().subtract(sum));
        toAcc.setBalance(toAcc.getBalance().add(sum));
        accountRepository.updateBalance(fromAcc.getBalance(), fromAcc.getName());
        accountRepository.updateBalance(toAcc.getBalance(), toAcc.getName());
        java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transaction.setDate(timestamp);
        transactionRepository.save(transaction);
        return "redirect:/profile";
    }
    @GetMapping("/viewTransactions")
    public String viewTransactions(@RequestParam("accountName") String accountName,Model model){
        Account fromAcc = accountRepository.findByName(accountName);
        model.addAttribute("incomingTransactions",fromAcc.getIncomingTransactions());
        model.addAttribute("outgoingTransactions",fromAcc.getOutgoingTransactions());
        return "views/transactions.html";
    }

}

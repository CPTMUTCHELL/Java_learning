package example.controller;

import example.entity.Account;
import example.entity.Role;
import example.entity.User;
import example.repository.AccountRepository;
import example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @GetMapping("/usersJson")
    public ResponseEntity<List<User>> getUsersJson(){
        List<User> users=userRepository.findAll();

        return ResponseEntity.ok(users);
    }
    @GetMapping("/users")
    public String getUsers(Model model, Principal principal,HttpSession session){
        List<User> users=userRepository.findAll();

        model.addAttribute("users",users);
        User user = userRepository.findByName(principal.getName());
        model.addAttribute("this_user",user);
        return "views/users.html";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal,HttpSession session){
        String login=principal.getName();
        User user=userRepository.findByName(login);
        model.addAttribute("accounts", user.getAccounts());
        model.addAttribute("user",user);

        return "views/profile.html";
    }
    @GetMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("login") String login){
        User user=userRepository.findByName(login);
        userRepository.delete(user);
        return "redirect:/users";
    }


}

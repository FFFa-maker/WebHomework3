package web.homework3.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.homework3.demo.entities.User;

@Controller
public class SignController {

    @PostMapping("/sign")
    public String addUser(Model model,
                   @RequestParam("user") String username,
                   @RequestParam("password") String password){
        if(username.equals("") || password.equals("")){
            model.addAttribute("result" ,"不能为空");
            return "sign";
        }
        if(LoginController.userMap.containsKey(username)){
            model.addAttribute("result", "用户已存在");
            return "sign";
        }
        User user = new User(username, password);
        LoginController.userMap.put(username, user);
        return "/login";
    }

    @GetMapping("/sign")
    String signUp(){
        return "sign";
    }
}

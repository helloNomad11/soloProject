package co.kr.mysite.nevergoback.admin;

import co.kr.mysite.nevergoback.employee.EmployeeService;
import co.kr.mysite.nevergoback.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuperController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PostService postService;

    @GetMapping("/not-found")
    public String gotoMain(){
        return "redirect:/employees";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/employees";
    }
}

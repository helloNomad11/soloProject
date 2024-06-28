package co.kr.mysite.nevergoback.employee;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/employees")
@AllArgsConstructor
@NoArgsConstructor
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public String getAllEmployee(Model model) {
        Iterable<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList", employeeList);
        return "employee/main";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(Model model, @PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            // 예외 처리 또는 기본 페이지 반환
            return "employee/main";
        }
        model.addAttribute("employee", employee);
        return "employee/employeeDetail"; // Thymeleaf 템플릿 이름
    }

    @GetMapping("/create")
    public String create() {
        return "employee/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam String image, String name, String eMail, String phoneNumber, String team) {
        Employee employee = new Employee();
        employee.setImage(image);
        employee.setName(name);
        employee.setEMail(eMail);
        employee.setPhoneNumber(phoneNumber);
        employee.setTeam(team);
        this.employeeService.createEmployee(employee);
        System.out.println("employee: "+ employee.getName() + " created");
        return "redirect:/employees/" + employee.getEmployeeId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute(employeeService.findById(id));
        return "employee/update";
    }

    @Transactional
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestParam String image, String name, String eMail, String phoneNumber, String team) {
        Employee employee = this.employeeService.findById(id);
        employee.setImage(image);
        employee.setName(name);
        employee.setEMail(eMail);
        employee.setPhoneNumber(phoneNumber);
        employee.setTeam(team);
        return "redirect:/employees/" + employee.getEmployeeId();
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

//    @GetMapping("/search/{keyword}")
//    public String getSearchPage(@PathVariable String keyword, Model model) {
//        model.addAttribute("employees", employeeService.findByName(keyword));
//        return "employee/search";
//    }
}

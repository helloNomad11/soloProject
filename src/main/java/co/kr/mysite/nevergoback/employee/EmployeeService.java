package co.kr.mysite.nevergoback.employee;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public Iterable<Employee> findByName(String name) {
        return employeeRepository.findAllByName(name);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee newEmployee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    if (newEmployee.getName() != null) {
                        existingEmployee.setName(newEmployee.getName());
                    }
                    if (newEmployee.getImage() != null) {
                        existingEmployee.setImage(newEmployee.getImage());
                    }
                    if (newEmployee.getEMail() != null) {
                        existingEmployee.setEMail(newEmployee.getEMail());
                    }
                    if (newEmployee.getPhoneNumber() != null) {
                        existingEmployee.setPhoneNumber(newEmployee.getPhoneNumber());
                    }
                    if (newEmployee.getTeam() != null) {
                        existingEmployee.setTeam(newEmployee.getTeam());
                    }
                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

package co.kr.mysite.nevergoback.employee;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public Employee findByName(String name);
    public Iterable<Employee> findAllByName(String name);
}

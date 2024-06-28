package co.kr.mysite.nevergoback.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long employeeId;

    private String image;

    private String name;

    private String eMail;

    private String phoneNumber;

    private String team;

    private Long salary;
}

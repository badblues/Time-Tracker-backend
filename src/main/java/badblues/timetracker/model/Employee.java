package badblues.timetracker.model;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity(name = "Employee")
@Table(name = "employee",
       uniqueConstraints = {
           @UniqueConstraint(name = "employee_name_unique",
                             columnNames = "name")
       })
public class Employee {    

    @Id
    @SequenceGenerator( 
        name = "employee_sequence",
        sequenceName = "employee_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "employee_sequence"
    )

    @Column(updatable = false)
    private long id;
    
    @Column(nullable = false)
    private String name;


    @OneToMany(
            cascade = {CascadeType.ALL},
            mappedBy = "employee"
    )
    private List<Task> tasks = new ArrayList<>();
     
    public Employee () {
    }

    public Employee (long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee (String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
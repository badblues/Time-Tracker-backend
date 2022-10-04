package badblues.timetracker.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {

    
    @Id
    @SequenceGenerator( 
        name = "task_sequence",
        sequenceName = "task_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "task_sequence"
    )

    @Column(updatable = false)
    private long id;

    private String taskName;
    private String taskDescription;
    @Transient
    private Date startDate;
    @Transient
    private Date endDate;

    @ManyToOne
    @JoinColumn(
        name="employee_id",
        nullable=false,
        foreignKey = @ForeignKey(name="employee_id_fk")
        )
    private Employee employee;

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        //TODO
        //this.startDate = ...
    }

    public long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Date getStartDate() {
        return startDate;
    } 

    public Date getEndDate() {
        return endDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
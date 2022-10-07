package badblues.timetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import badblues.timetracker.model.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
 
}
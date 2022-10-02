package badblues.timetracker.service;

import org.springframework.stereotype.Service;
import java.util.List;
import badblues.timetracker.model.User;

@Service
public class UserService {
    
    public List<User> getUsers() {
        return List.of(new User(1, "Boba"));
    }

}
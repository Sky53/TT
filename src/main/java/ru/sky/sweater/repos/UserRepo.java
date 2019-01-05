package ru.sky.sweater.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sky.sweater.domain.User;

public interface UserRepo  extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

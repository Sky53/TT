package ru.sky.sweater.repos;

import org.springframework.data.repository.CrudRepository;
import ru.sky.sweater.domain.Message;

import java.util.List;

public interface MesseageRepo extends CrudRepository<Message, Integer> {

   List<Message> findByTag(String tag);


}

package com.stockkarte;

import com.stockkarte.models.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@RepositoryRestResource
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    List<Task> findByLastName(String lastName);
    Task findById(long id);
}

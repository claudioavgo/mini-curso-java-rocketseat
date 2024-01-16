package com.claudioav.todolist.repositories;

import com.claudioav.todolist.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByUserId(UUID user_id);
}

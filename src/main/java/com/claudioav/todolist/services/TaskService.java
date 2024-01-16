package com.claudioav.todolist.services;

import com.claudioav.todolist.models.TaskModel;
import com.claudioav.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskModel create(TaskModel newTask) {
        return taskRepository.save(newTask);
    }

    public Optional<TaskModel> get(UUID task_id) {
        return taskRepository.findById(task_id);
    }

    public List<TaskModel> listUserTasks(UUID user_id) {
        return taskRepository.findByUserId(user_id);
    }

    public TaskModel edit(TaskModel taskModel, UUID task_id) throws Exception {
        Optional<TaskModel> model = get(task_id);

        if (model.isEmpty()) {
            return null;
        }

        String description = taskModel.getDescription();
        String title = taskModel.getTitle();
        String priority = taskModel.getPriority();
        LocalDateTime startAt = taskModel.getStart_at();
        LocalDateTime endAt = taskModel.getEnd_at();

        model.get().setDescription(description != null ? description : model.get().getDescription());
        model.get().setTitle(title != null ? title : model.get().getTitle());
        model.get().setPriority(priority != null ? priority : model.get().getPriority());
        model.get().setEnd_at(startAt != null ? startAt : model.get().getStart_at());
        model.get().setStart_at(endAt != null ? endAt : model.get().getEnd_at());

        return taskRepository.save(model.get());
    }
}

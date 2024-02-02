package io.ai.comandside.aggregate;

import io.ai.comandside.entity.IdentifiedDomainObject;
import io.ai.comandside.entity.TaskStatus;
import io.ai.comandside.event.task.AssignedExecutorToTaskDomainEvent;
import io.ai.comandside.event.task.ChangedTaskNameDomainEvent;
import io.ai.comandside.event.task.ChangedTaskStatusDomainEvent;
import io.ai.comandside.valueobject.Information;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskAggregate extends IdentifiedDomainObject {

    @ElementCollection(targetClass = UUID.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "task_executors", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "user_id")
    private List<UUID> executors;

    @OneToOne
    @JoinColumn(name = "task_id")
    private TaskStatus taskStatus;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Information information;

    @Column(name = "project_id")
    private UUID projectId;

    public TaskAggregate(TaskStatus taskStatus, String name, String description, UUID projectId) {
        this.executors = new ArrayList<>();
        this.taskStatus = taskStatus;
        this.information = new Information(name, description);
        this.projectId = projectId;
    }

    public ChangedTaskNameDomainEvent changeName(String newName) {
        var description = this.information.getDescription();
        this.information = new Information(newName, description);
        return new ChangedTaskNameDomainEvent(this, id, newName);
    }

    public ChangedTaskStatusDomainEvent setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
        return new ChangedTaskStatusDomainEvent(this, id, taskStatus.getId());
    }

    public AssignedExecutorToTaskDomainEvent addExecutor(UUID userId) {
        this.executors.add(userId);
        return new AssignedExecutorToTaskDomainEvent(this, id, userId);
    }
}

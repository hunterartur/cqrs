package io.ai.comandside.aggregate;

import io.ai.comandside.entity.IdentifiedDomainObject;
import io.ai.comandside.event.project.AddedTaskInProjectDomainEvent;
import io.ai.comandside.event.project.AddedUserToProjectDomainEvent;
import io.ai.comandside.event.project.ChangedProjectNameDomainEvent;
import io.ai.comandside.valueobject.Information;
import jakarta.persistence.CascadeType;
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
@Table(name = "projects")
public class ProjectAggregate extends IdentifiedDomainObject {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private Information information;

    @ElementCollection(targetClass = UUID.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "project_members", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "user_id")
    private List<UUID> memberIds;

    @ElementCollection(targetClass = UUID.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "project_tasks", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "task_id")
    private List<UUID> taskIds;

    public ProjectAggregate(UUID userId, String name, String description) {
        this.memberIds = new ArrayList<>();
        this.taskIds = new ArrayList<>();
        this.information = new Information(name, description);
        this.memberIds.add(userId);
    }

    public ChangedProjectNameDomainEvent changeName(String newName) {
        information = new Information(newName, information.getDescription());
        return new ChangedProjectNameDomainEvent(this, id, information);
    }

    public AddedUserToProjectDomainEvent addUser(UUID userId) {
        if (this.memberIds == null) this.memberIds = new ArrayList<>();
        this.memberIds.add(userId);
        return new AddedUserToProjectDomainEvent(this, id, userId);
    }

    public AddedTaskInProjectDomainEvent addTask(UUID taskId) {
        if (this.taskIds == null) this.taskIds = new ArrayList<>();
        this.taskIds.add(taskId);
        return new AddedTaskInProjectDomainEvent(this, id, taskId);
    }
}

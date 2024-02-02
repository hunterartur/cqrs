package io.ai.comandside.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "task_statuses")
public class TaskStatus extends IdentifiedDomainObject {

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    public TaskStatus(String name, String color) {
        this.name = name;
        this.color = color;
    }

    protected TaskStatus() {
    }
}
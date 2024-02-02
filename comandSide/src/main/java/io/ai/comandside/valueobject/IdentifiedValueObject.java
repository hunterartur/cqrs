package io.ai.comandside.valueobject;

import io.ai.comandside.entity.IdentifiedDomainObject;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdentifiedValueObject extends IdentifiedDomainObject {
    public IdentifiedValueObject() {
        super();
    }
}

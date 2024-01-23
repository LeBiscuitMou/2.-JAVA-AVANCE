package net.ent.etrs.heartstoneJPA.models.entities;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
@ToString(of = {"id"})
public abstract class AbstractEntity {

    @Getter
    Long id = null;


    public void setId(Long id) {
        if (Objects.nonNull(id)) {
            this.id = id;
        }
    }

}

package net.ent.etrs.gestion_jeuvideo.models.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
public abstract class AbstractEntity implements Serializable{

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    public void setId(Long id) {
        if (Objects.nonNull(id)) {
            this.id = id;
        }
    }
    

}

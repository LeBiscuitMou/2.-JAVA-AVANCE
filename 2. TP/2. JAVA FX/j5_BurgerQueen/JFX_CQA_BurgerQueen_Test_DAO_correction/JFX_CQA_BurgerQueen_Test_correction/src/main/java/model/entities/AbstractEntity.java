package model.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@SuppressWarnings("serial")

@MappedSuperclass
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@FieldDefaults(level=AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public abstract class AbstractEntity implements Serializable{
	// annotation lombok
	@Getter
	// annotations JPA
	@Id
//	@Column(name="ID", columnDefinition="VARCHAR(36)")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

}

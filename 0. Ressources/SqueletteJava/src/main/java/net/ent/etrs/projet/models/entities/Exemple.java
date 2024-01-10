package net.ent.etrs.projet.models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "exemple", uniqueConstraints = @UniqueConstraint(columnNames = {""}, name = "uk__exemple__"))
@EqualsAndHashCode(of = {""}, callSuper = false)
@ToString(of = {"", "", ""}, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exemple extends AbstractEntity {

}

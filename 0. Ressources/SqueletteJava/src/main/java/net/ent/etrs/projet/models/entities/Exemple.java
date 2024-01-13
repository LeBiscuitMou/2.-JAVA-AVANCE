package net.ent.etrs.projet.models.entities;

@Entity
@Table(name = "exemple",
        uniqueConstraints = {
                @UniqueConstraint(name = "exemple____UK", columnNames = "")
        }
)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {""}, callSuper = false)
@ToString(of = {""}, callSuper = true)
public class Exemple extends AbstractEntity{

}

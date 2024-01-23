package net.ent.etrs.logistock.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client",
        uniqueConstraints = {
                @UniqueConstraint(name = "client__nom_prenom__UK", columnNames = {"nom", "prenom"})
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"nom", "prenom"}, callSuper = false)
@ToString(of = {"nom", "prenom", "adresse", "codePostal", "ville", "numeroTelephone"}, callSuper = true)
public class Client extends AbstractEntity {
    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le nom doit être référencé")
    @NotBlank(message = "le nom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du nom n'est pas valide")
    //JPA
    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le prenom doit être référencé")
    @NotBlank(message = "le prenom ne doit pas être vide")
    @Size(min = 1, max = 50, message = "la taille du prenom n'est pas valide")
    //JPA
    @Column(name = "prenom", length = 50, nullable = false, unique = true)
    private String prenom;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "l'adresse doit être référencé")
    @NotBlank(message = "l'adresse ne doit pas être vide")
    @Size(min = 1, max = 255, message = "la taille du adresse n'est pas valide")
    //JPA
    @Column(name = "adresse", length = 255, nullable = false, unique = false)
    private String adresse;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le code postal doit être référencé")
    @NotBlank(message = "le code postal ne doit pas être vide")
    @Size(min = 1, max = 5, message = "la taille du code postal n'est pas valide")
    //JPA
    @Column(name = "codePostal", length = 5, nullable = false, unique = false)
    private String codePostal;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "la ville doit être référencé")
    @NotBlank(message = "la ville ne doit pas être vide")
    @Size(min = 1, max = 100, message = "la taille du ville n'est pas valide")
    //JPA
    @Column(name = "ville", length = 100, nullable = false, unique = false)
    private String ville;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = "le numero de telephone doit être référencé")
    @NotBlank(message = "le numero de telephone ne doit pas être vide")
    @Size(min = 1, max = 10, message = "la taille du numero de telephone n'est pas valide")
    @Pattern(regexp = "^0.*")
    //JPA
    @Column(name = "numeroTelephone", length = 10, nullable = false, unique = false)
    private String numeroTelephone;

    public boolean isVIP(List<Commande> commandes) {
        boolean machin = false;
        BigDecimal result = BigDecimal.ZERO;

        for(Commande bite : commandes){
            if (bite.getDateCommande().isBefore(LocalDate.now().plusMonths(6))) {
                result = result.add(bite.calculerPrixTotal());
            }
        }

        if (result.compareTo(BigDecimal.valueOf(5000)) > 0) {
            machin = true;
            appliquerRistourne(result, machin);
        }

        return machin;
    }

    public BigDecimal appliquerRistourne(BigDecimal result, boolean machin) {
        if (machin) {
            result = result.multiply(BigDecimal.valueOf(0.95));
        }

        return result;
    }
}
package fr.ldek.ag.sacados;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Objet {
    
    @Getter @Setter
    private String nom;
    
    @Getter @Setter
    private Integer poids;
    
    @Getter @Setter
    private Integer utilite;
    
    
}

package net.ent.etrs.projetjavamaier.start;


import net.ent.etrs.projetjavamaier.presenteur.Presenteur;

public class Lanceur {
    public static void main(String[] args) {


        try {
            Presenteur p = new Presenteur();

        } catch (Exception ex) {
            System.out.println("ERROR");
        }
    }
}

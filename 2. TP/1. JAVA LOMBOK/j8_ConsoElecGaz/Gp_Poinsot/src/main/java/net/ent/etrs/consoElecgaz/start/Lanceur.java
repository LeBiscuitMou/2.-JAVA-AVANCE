package net.ent.etrs.consoElecgaz.start;

import net.ent.etrs.consoElecgaz.presenteur.Presenteur;

public class Lanceur {
    public static void main(String[] args) {


        try {
            Presenteur p = new Presenteur();

        } catch (Exception ex) {
            System.out.println("ERROR");
        }
    }
}

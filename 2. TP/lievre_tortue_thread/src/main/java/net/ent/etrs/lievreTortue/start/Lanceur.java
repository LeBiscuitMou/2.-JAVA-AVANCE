package net.ent.etrs.lievreTortue.start;

import net.ent.etrs.lievreTortue.models.entities.Lievre;
import net.ent.etrs.lievreTortue.models.entities.Tortue;

public class Lanceur {
    public static void main(String[] args) {
        Thread threadL = new Thread(new Lievre());
        Thread threadT = new Thread(new Tortue());

        threadL.start();
        threadT.start();
    }
}
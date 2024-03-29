package net.ent.etrs.lievreTortue.models.entities;

import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.lievreTortue.models.entities.exceptions.LievreException;
import net.ent.etrs.lievreTortue.models.entities.exceptions.TortueException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Tortue implements Runnable {
    @Getter
    @NotNull
    @Positive(message = "La distance par bond doit être positive")
    private final static Float DISTANCE_PAR_BOND = 2.0f;

    @Getter
    @NotNull
    @Positive(message = "La distance par bond doit être positive")
    private final static Float DISTANCE_A_PARCOURIR = 100.0f;

    @Getter
    @NotNull
    @Positive(message = "Le temps de recup doit être positif")
    private final static Integer TEMPS_RECUP = 1;

    @Getter
    @Setter
    @NotNull
    @Positive(message = "La distance doit être positive")
    private Float distance;

    public Tortue() {
        this.setDistance(0.0f);
    }

    public void avancer() {
        this.setDistance(this.distance + DISTANCE_PAR_BOND);
        System.out.println("La tortue a parcouru " + distance + "m.");
    }

    public void sleep() throws TortueException {
        try {
            Thread.sleep(TEMPS_RECUP * 1000);
        } catch (InterruptedException e) {
            throw new TortueException("Marche pas", e);
        }
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < DISTANCE_A_PARCOURIR; i += DISTANCE_PAR_BOND) {
                avancer();
                sleep();
            }
        } catch (TortueException e) {
            throw new RuntimeException(e);
        }
    }
}
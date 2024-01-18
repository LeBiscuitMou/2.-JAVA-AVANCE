package net.ent.etrs.league.start;

import net.ent.etrs.league.presenter.Presenteur;
import net.ent.etrs.league.presenter.PresenteurFactory;
import net.ent.etrs.league.presenter.exceptions.PresenteurFactoryException;

public class Lanceur {
    public static void main(String[] args) {
        try {
            Presenteur p = PresenteurFactory.fabriquerPresenteur();
        } catch (PresenteurFactoryException e) {
            e.printStackTrace();
        }
    }
}
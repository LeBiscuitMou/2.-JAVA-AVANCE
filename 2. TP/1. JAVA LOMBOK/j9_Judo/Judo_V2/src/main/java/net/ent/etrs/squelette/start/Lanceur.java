package net.ent.etrs.squelette.start;

import net.ent.etrs.squelette.presenter.Presenteur;
import net.ent.etrs.squelette.presenter.PresenteurFactory;
import net.ent.etrs.squelette.presenter.exceptions.PresenteurFactoryException;

public class Lanceur {
    public static void main(String[] args) {
        try {
            Presenteur p = PresenteurFactory.fabriquerPresenteur();
        } catch (PresenteurFactoryException e) {
            e.printStackTrace();
        }
    }
}
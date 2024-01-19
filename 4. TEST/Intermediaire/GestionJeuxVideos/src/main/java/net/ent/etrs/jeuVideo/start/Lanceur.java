package net.ent.etrs.jeuVideo.start;

import net.ent.etrs.jeuVideo.presenter.Presenteur;
import net.ent.etrs.jeuVideo.presenter.PresenteurFactory;
import net.ent.etrs.jeuVideo.presenter.exceptions.PresenteurFactoryException;

public class Lanceur {
    public static void main(String[] args) {
        try {
            Presenteur p = PresenteurFactory.fabriquerPresenteur();
        } catch (PresenteurFactoryException e) {
            e.printStackTrace();
        }
    }
}
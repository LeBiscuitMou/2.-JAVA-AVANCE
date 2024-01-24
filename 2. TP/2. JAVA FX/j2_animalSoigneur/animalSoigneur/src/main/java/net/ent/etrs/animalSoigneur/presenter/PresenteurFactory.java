package net.ent.etrs.animalSoigneur.presenter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.animalSoigneur.model.entities.references.ConstantesMetier;
import net.ent.etrs.animalSoigneur.presenter.exceptions.PresenteurException;
import net.ent.etrs.animalSoigneur.presenter.exceptions.PresenteurFactoryException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PresenteurFactory {
    public static Presenteur fabriquerPresenteur() throws PresenteurFactoryException {
        try {
            return new Presenteur();
        } catch (PresenteurException e) {
            throw new PresenteurFactoryException(ConstantesMetier.ERROR_CREATION + Presenteur.class.getSimpleName(), e);
        }
    }
}

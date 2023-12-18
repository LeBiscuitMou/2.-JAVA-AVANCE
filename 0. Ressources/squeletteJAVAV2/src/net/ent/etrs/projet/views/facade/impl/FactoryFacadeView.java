package net.ent.etrs.projet.views.facade.impl;

import net.ent.etrs.projet.views.facade.FacadeView;

public final class FactoryFacadeView {

    private FactoryFacadeView() {
    }

    public static FacadeView fabriquerFacadeView() {

        return new FacadeViewImpl();
    }
}

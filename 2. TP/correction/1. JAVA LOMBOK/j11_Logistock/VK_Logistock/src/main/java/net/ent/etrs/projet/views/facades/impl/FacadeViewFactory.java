package net.ent.etrs.projet.views.facades.impl;

import net.ent.etrs.projet.views.facades.FacadeView;

public final class FacadeViewFactory {
    private FacadeViewFactory() {

    }

    public static FacadeView fabriquerFacadeVue(){
        return new FacadeViewImpl();
    }
}

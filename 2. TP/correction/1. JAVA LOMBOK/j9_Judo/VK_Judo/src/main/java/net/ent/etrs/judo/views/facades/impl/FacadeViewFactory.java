package net.ent.etrs.judo.views.facades.impl;

import net.ent.etrs.judo.views.facades.FacadeView;

public final class FacadeViewFactory {
    private FacadeViewFactory() {

    }

    public static FacadeView fabriquerFacadeVue(){
        return new FacadeViewImpl();
    }
}

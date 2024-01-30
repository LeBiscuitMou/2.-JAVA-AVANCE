package net.ent.etrs.gestion_jeuvideo.views.facades.impl;

import net.ent.etrs.gestion_jeuvideo.views.facades.FacadeView;

public final class FacadeViewFactory {
    private FacadeViewFactory() {

    }

    public static FacadeView fabriquerFacadeVue(){
        return new FacadeViewImpl();
    }
}

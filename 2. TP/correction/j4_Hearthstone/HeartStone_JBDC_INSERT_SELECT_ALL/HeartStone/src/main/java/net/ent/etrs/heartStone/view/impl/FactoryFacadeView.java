package net.ent.etrs.heartStone.view.impl;


import lombok.NoArgsConstructor;
import net.ent.etrs.heartStone.view.FacadeView;

@NoArgsConstructor
public final class FactoryFacadeView {


    public static FacadeView fabriquerFacadeView() {

        return new FacadeViewImpl();
    }
}

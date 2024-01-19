package net.ent.etrs.jeuVideo.model.dao.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.jeuVideo.model.dao.IDaoConsole;
import net.ent.etrs.jeuVideo.model.dao.IDaoFabriquant;
import net.ent.etrs.jeuVideo.model.dao.IDaoJeuVideo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {
    @Getter
    private static final IDaoConsole daoConsole;

    static {
        daoConsole = new DaoConsoleImpl();
    }

    @Getter
    private static final IDaoFabriquant daoFabriquant;

    static {
        daoFabriquant = new DaoFabriquantImpl();
    }

    @Getter
    private static final IDaoJeuVideo daoJeuVideo;

    static {
        daoJeuVideo = new DaoJeuVideoImpl();
    }
}

package net.ent.etrs.gestion_jeuvideo.models.dao.impl;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoConsole;
import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoFabriquant;
import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoJeuVideo;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

    @Getter
    private static final IDaoJeuVideo daoJeuVideo;

    @Getter
    private static final IDaoConsole daoConsole;

    @Getter
    private static final IDaoFabriquant daoFabriquant;
    

    static {
        daoJeuVideo = new JeuVideoDaoJpaImpl();
        daoConsole = new ConsoleDaoJpaImpl();
        daoFabriquant = new FabriquantDaoJpaImpl();
    }

}

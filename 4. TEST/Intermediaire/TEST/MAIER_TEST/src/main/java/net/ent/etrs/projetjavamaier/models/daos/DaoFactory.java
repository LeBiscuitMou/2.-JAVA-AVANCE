package net.ent.etrs.projetjavamaier.models.daos;

import lombok.*;
import net.ent.etrs.projetjavamaier.models.daos.impl.DaoConsoleImpl;
import net.ent.etrs.projetjavamaier.models.daos.impl.DaoFabriquantImpl;
import net.ent.etrs.projetjavamaier.models.daos.impl.DaoJeuVideoImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class DaoFactory {


    private static final ConsoleDao daoConsole;
    private static final FabriquantDao daoFabriquant;
    private static final JeuVideoDao daoJeuVideo;


    static {
        daoConsole = new DaoConsoleImpl();
        daoFabriquant = new DaoFabriquantImpl();
        daoJeuVideo = new DaoJeuVideoImpl();


    }

        public static ConsoleDao getConsoleDao() {
            return daoConsole;
        }


        public static FabriquantDao getFabriquantDao() {
            return daoFabriquant;
        }

    public static JeuVideoDao getJeuVideoDao() {
        return daoJeuVideo;
    }


















}


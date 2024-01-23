package net.ent.etrs.poeleague.start;

import net.ent.etrs.poeleague.models.dao.DaoFactory;
import net.ent.etrs.poeleague.models.dao.DaoLeague;
import net.ent.etrs.poeleague.models.facades.impl.FacadeChargementFichierImpl;

public class Lanceur {
    public static void main(String[] args) {
        DaoLeague dao = DaoFactory.getDaoLeague();
        FacadeChargementFichierImpl test = new FacadeChargementFichierImpl();
        try {
            test.initialisation();
            dao.topThreeBestBuild(dao.find(1L).get()).forEach(System.out::println);
            dao.getRewardPointsByLeague().entrySet().forEach(System.out::println);
            dao.bestBuildByLeague().entrySet().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

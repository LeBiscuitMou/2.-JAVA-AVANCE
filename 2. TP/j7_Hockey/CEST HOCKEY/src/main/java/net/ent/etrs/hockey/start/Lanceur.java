package net.ent.etrs.hockey.start;

import net.ent.etrs.hockey.models.dao.DaoFactory;

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

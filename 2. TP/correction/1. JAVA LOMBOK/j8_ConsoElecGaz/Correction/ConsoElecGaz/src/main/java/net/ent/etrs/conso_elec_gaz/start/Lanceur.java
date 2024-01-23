package net.ent.etrs.conso_elec_gaz.start;

import net.ent.etrs.conso_elec_gaz.model.daos.DaoFactory;
import net.ent.etrs.conso_elec_gaz.model.daos.DaoRegion;
import net.ent.etrs.conso_elec_gaz.model.facade.exception.BusinessException;
import net.ent.etrs.conso_elec_gaz.model.facade.impl.FacadeMetierImpl;

public class Lanceur {
    public static void main(String[] args) {


        try {
            FacadeMetierImpl f = new FacadeMetierImpl();
            DaoRegion dr = DaoFactory.getDaoRegion();


            f.initialisation();

//            List<Region> regionList = f.getAllRegions();
//            for (int i = 0; i < 5; i++) {
//                regionList.remove(0);
//            }
//            f.consomationsByRegionsAndBeforeDate(regionList, LocalDate.of(2020,2,1)).forEach(System.out::println);

//          Map<LocalDate, Double> m = f.sumConsomationsForRegionAndBeforeDate(dr.find(1L).get(),LocalDate.of(2019,2,1));
//          for(Map.Entry<LocalDate,Double> entry : m.entrySet()){
//              System.out.println(entry.getKey()+" : "+entry.getValue()+" kw");
//          }


//            Map<Filiere,Map<LocalDate, Double>> m = f.sumConsomationsByFiliereForRegionAndBeforeDate(dr.find(1L).get(),LocalDate.of(2019,2,1));
//            for(Map.Entry<Filiere,Map<LocalDate,Double>> entry : m.entrySet()){
//                System.out.println(entry.getKey()+" : "+entry.getValue().size());
//            }


        } catch ( BusinessException e) {
            throw new RuntimeException(e);
        }

    }
}

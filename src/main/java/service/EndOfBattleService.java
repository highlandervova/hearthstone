package service;

import data.ListFinalBattle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EndOfBattleService {

    private static List<ListFinalBattle> finalBattle = new ArrayList<>();

    public void addListFinalBattle(
            String idBattle,
            String idUserHero1,
            String loginHero1,
            String nameHero1,
            Integer raceidHero1,
            Integer pointsHero1,
            Integer goldHero1,
            String idUserHero2,
            String loginHero2,
            String nameHero2,
            Integer raceIdHero2,
            Integer pointsHero2,
            Integer goldHero2,
            int Win ){
        ListFinalBattle finalList = new ListFinalBattle(
                idBattle,
                idUserHero1,
                loginHero1,
                nameHero1,
                raceidHero1,
                pointsHero1,
                goldHero1,
                idUserHero2,
                loginHero2,
                nameHero2,
                raceIdHero2,
                pointsHero2,
                goldHero2,
                Win);

        finalBattle.add(finalList);

         }

  public ListFinalBattle getId(String id) {

        ListFinalBattle b = null;
        for (ListFinalBattle listFinal : finalBattle) {
            if ((listFinal.getIdBattle()).equals(id)) {
                b = listFinal;
            }

        }
        return b;
    }

    public int getNumOfHeroBattl(String idBattle, String idUser) {
          int numOfHero = 0;
        ListFinalBattle b = getId(idBattle);
        for (ListFinalBattle listFinal : finalBattle) {
            if ((listFinal.getIdUserHero1()).equals(idUser) ) {
                numOfHero = 1;
            }else {numOfHero = 2;}
        }
        return numOfHero;
    }



}

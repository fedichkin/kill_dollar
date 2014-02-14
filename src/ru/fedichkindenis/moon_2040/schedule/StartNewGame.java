package ru.fedichkindenis.moon_2040.schedule;

import ru.fedichkindenis.moon_2040.bd.ManagerBD;
import ru.fedichkindenis.moon_2040.enums.Resources;
import ru.fedichkindenis.moon_2040.game.Game;
import ru.fedichkindenis.moon_2040.users.User;

import java.sql.Types;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 23.11.13
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class StartNewGame extends TimerTask {

    private Game game;

    public StartNewGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {

        for(User user : game.getListUsers()){
            ManagerBD.add_state_resources(game.getId(), new java.sql.Date(game.getSd().getTime()),
                    user.getPerson_uid(), ManagerBD.get_generate_res(game.getId()), 1, 1, null);

            ManagerBD.add_state_resources(game.getId(), new java.sql.Date(game.getSd().getTime()),
                    user.getPerson_uid(), 2L, game.getCreditUser(), 1, null);
        }

        for(int i = 0;i < game.getCountPpl();i++){
            ManagerBD.add_state_ppl(i+1, game.getId(), 1, 0,
                    new java.sql.Date(game.getSd().getTime()), null);

            ManagerBD.add_state_resources_ppl(game.getId(), new java.sql.Date(game.getSd().getTime()),
                    i+1, game.getCreditPpl(), 0, 0, 0, 0);
        }

        Long idStaticRes = ManagerBD.get_next_id_resources_statistics();
        int flatCount = 0;

        ManagerBD.add_game_statistics(game.getId(), new java.sql.Date(game.getSd().getTime()),
                game.getCountPpl(), 0, game.getCreditPpl(), game.getCreditPpl(), game.getCreditPpl(),
                game.getCountPpl(), 0, flatCount, flatCount, 0, 0, 0, idStaticRes, 0, 0, 0);

        for(Resources res : Resources.values()){
            int count = ManagerBD.getCountResourcesByGameDate(game.getId(),
                    new java.sql.Date(game.getSd().getTime()), res.getId());

            ManagerBD.add_resources_statistics(idStaticRes, res.getId(), count, 0, 0, 0, 0);
        }
    }
}

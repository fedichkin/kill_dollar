package ru.fedichkindenis.moon_2040.game;

import ru.fedichkindenis.moon_2040.users.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 24.10.13
 * Time: 23:51
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    private long id;
    private String name;
    private int maxPlayer;
    private Date sd;
    private Date fd;
    private Date step;
    private int countPpl;
    private int creditPpl;
    private int creditUser;
    private int lifeOutFlat;
    private String description;
    private ArrayList<User> listUsers;

    private class Goal {

        private String resources;
        private String func;
        private int value;
        private boolean win;
    }
}

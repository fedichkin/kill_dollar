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
    private Goal win;
    private Goal loss;

    private class Goal {

        private String resources;
        private String func;
        private int value;

        private Goal(String resources, String func, int value) {
            this.resources = resources;
            this.func = func;
            this.value = value;
        }

        public String getResources() {
            return resources;
        }

        public String getFunc() {
            return func;
        }

        public int getValue() {
            return value;
        }
    }

    public Game(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public void setSd(Date sd) {
        this.sd = sd;
    }

    public void setFd(Date fd) {
        this.fd = fd;
    }

    public void setStep(Date step) {
        this.step = step;
    }

    public void setCountPpl(int countPpl) {
        this.countPpl = countPpl;
    }

    public void setCreditPpl(int creditPpl) {
        this.creditPpl = creditPpl;
    }

    public void setCreditUser(int creditUser) {
        this.creditUser = creditUser;
    }

    public void setLifeOutFlat(int lifeOutFlat) {
        this.lifeOutFlat = lifeOutFlat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setListUsers(ArrayList<User> listUsers) {
        this.listUsers = listUsers;
    }

    public void setWin(String resources, String func, int value) {
        this.win = new Goal(resources, func, value);
    }

    public void setLoss(String resources, String func, int value) {
        this.loss = new Goal(resources, func, value);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public Date getSd() {
        return sd;
    }

    public Date getFd() {
        return fd;
    }

    public Date getStep() {
        return step;
    }

    public int getCountPpl() {
        return countPpl;
    }

    public int getCreditPpl() {
        return creditPpl;
    }

    public int getCreditUser() {
        return creditUser;
    }

    public int getLifeOutFlat() {
        return lifeOutFlat;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<User> getListUsers() {
        return listUsers;
    }

    public String getWinResources(){
        return win.getResources();
    }

    public String getWinFunc(){
        return win.getFunc();
    }

    public int getWinValue(){
        return win.getValue();
    }

    public String getLossResources(){
        return loss.getResources();
    }

    public String getLossFunc(){
        return loss.getFunc();
    }

    public int getLossValue(){
        return loss.getValue();
    }
}

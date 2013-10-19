package ru.fedichkindenis.moon_2040.users;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 19.10.13
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private long id;
    private String person_uid;
    private String email;
    private String first_name;
    private String last_name;

    public User(long id, String person_uid, String email, String first_name, String last_name){

        this.id = id;
        this.person_uid = person_uid;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public long getId() {
        return id;
    }

    public String getPerson_uid() {
        return person_uid;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}

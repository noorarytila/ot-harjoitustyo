
package moodtracker.domain;

import java.util.ArrayList;
import java.util.List;
import moodtracker.dao.UserDao;

/**
 * Luokka toteuttaa käyttäjä-olion luomisen ja getter- ja setter -metodit
 * 
 */

public class User {
    
    private String name;
    private String username;
    
    
    /**
     * Konstruktori käyttäjän luomiseksi
     * @param username käyttäjänimi merkkijonona
     * @param name käyttäjän nimi merkkijonona
     */
    public User(String username, String name) {
        this.name = name;
        this.username = username;
    }
    
    public String getName() {
        return name;
    }
    
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return username;
    }
    
    
}


package moodtracker.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import moodtracker.domain.Mood;
import moodtracker.domain.User;

/**
 * Rajapinta Data Acces object:in mukaiseen Moodien tallettamiseen ja hallintaan
 * @author nrytila
 */

public interface MoodDao {
   
    
    /**
     * Metodi moodin luomiseen
     * @param mood mood-olio
     * @return luotu moodi
     * @throws Exception 
     */
    Mood create(Mood mood) throws Exception;
    
    /**
     * Metodi tietyn käyttäjän kaikkien tallennettujen moodien hakemiseen.
     * @param user käyttäjä, jonka moodit haetaan
     * @return ArrayList moodeista
     * @throws Exception 
     */
    ArrayList<Integer> usersMoods(User user) throws Exception;
    
    List<Mood> getAll();
    
    
}

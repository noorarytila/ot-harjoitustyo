
package moodtracker.dao;

import java.util.List;
import moodtracker.domain.Mood;

public interface MoodDao {
    
    List<Mood> getAll();
    
    Mood create(Mood mood) throws Exception;
    
    
    
}

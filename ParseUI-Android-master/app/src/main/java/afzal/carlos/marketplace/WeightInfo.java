package afzal.carlos.marketplace;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("WeightLift")
public class WeightInfo extends ParseObject
        {

public WeightInfo()
        {
        }

public void setWeight(int weight){
        put("weight", weight);
        }

public void setReps(int reps){
        put("reps", reps);
        }

public void setSets(int sets){
        put("sets", sets);
        }

public void setUserID(String id){
        put("userID", id);
        }
        }

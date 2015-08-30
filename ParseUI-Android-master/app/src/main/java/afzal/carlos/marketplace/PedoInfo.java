package afzal.carlos.marketplace;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("RunInfo")
public class PedoInfo extends ParseObject
{

    public PedoInfo()
    {
    }
    public void setSteps(int steps){
        put("steps", steps);
    }

    public void setUserID(String id){
        put("userID", id);
    }
}
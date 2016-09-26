/**
 * 
 */
package gov.adlnet.xapi.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author SSHABBIR
 *
 */
public class _id
{

    private String $id;
    
    public _id() {}
    
    public _id(String $id)
    {
        this.$id = $id;
    }

    public String get$id()
    {
        return $id;
    }

    public String getMongoId()
    {
        return $id;
    }

    
    public void set$id(String $id)
    {
        this.$id = $id;
    }
    
    public JsonElement serialize() 
    {
        JsonObject obj = new JsonObject();
        if(this.$id != null)
        {
            obj.addProperty("id", this.$id);
        }
        return obj;
    }    
}

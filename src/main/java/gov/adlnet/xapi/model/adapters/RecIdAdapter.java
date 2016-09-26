/**
 * 
 */
package gov.adlnet.xapi.model.adapters;

import gov.adlnet.xapi.model._id;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author SSHABBIR
 *
 */
public class RecIdAdapter implements JsonDeserializer<_id>, JsonSerializer<_id>
{
    /* (non-Javadoc)
     * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
     */
    @Override
    public JsonElement serialize(_id a, Type arg1, JsonSerializationContext arg2)
    {
        return a.serialize();
    }

    /* (non-Javadoc)
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    @Override
    public _id deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
            Pattern p = Pattern.compile("^([a-z0-9]+)$");
            JsonObject jObj = json.getAsJsonObject();
            String id = "";
            if(!jObj.get("$id").isJsonNull())
            {
                id = jObj.get("$id").getAsString();
            }
            Matcher m = p.matcher(id);
            if(m.find()) 
            {
                return new _id(m.group(1));
            }
            else
            {
                return new _id();
            }
    }



}

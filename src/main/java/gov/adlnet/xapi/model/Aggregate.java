package gov.adlnet.xapi.model;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Aggregate
{
    private List<AggregateResult> result;
    private String ok;

    public Aggregate()
    {
    }

    public Aggregate(List<AggregateResult> result)
    {
        this.result = result;
    }

    public List<AggregateResult> getResult()
    {
        return result;
    }

    public void setResult(List<AggregateResult> result)
    {
        this.result = result;
    }

    public String getOk()
    {
        return ok;
    }

    public void setOk(String ok)
    {
        this.ok = ok;
    }

    @Override
    public String toString()
    {
        return "Aggregate [result=" + result + ", ok=" + ok + "]";
    }

    public JsonElement serialize()
    {
        JsonObject obj = new JsonObject();
        JsonArray jsonAttachments = new JsonArray();
        obj.add("result", jsonAttachments);
        for (AggregateResult a : this.result)
        {
            jsonAttachments.add(a.serialize());
        }
        if (this.ok != null)
        {
            obj.addProperty("ok", this.ok);
        }
        return obj;
    }
}

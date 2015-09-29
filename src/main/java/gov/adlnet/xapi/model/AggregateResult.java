package gov.adlnet.xapi.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AggregateResult
{
    private Statement statement;

    public AggregateResult()
    {
    }

    public AggregateResult(Statement statement)
    {
        this.statement = statement;
    }

    public Statement getStatement()
    {
        return statement;
    }

    public void setStatement(Statement statement)
    {
        this.statement = statement;
    }

    public JsonElement serialize()
    {
        JsonObject obj = new JsonObject();
        if (this.statement != null)
        {
            obj.add("statement", this.statement.serialize());
        }
        return obj;
    }
}

package gov.adlnet.xapi.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AggregateResult
{
    private Statement statement;
    private _id _id;

    public AggregateResult()
    {
    }

    public AggregateResult(Statement statement, _id _id)
    {
        this.statement = statement;
        this._id = _id;
    }

    public AggregateResult(_id _id)
    {
        this._id = _id;
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

    public _id getId()
    {
        return _id;
    }

    public void set_id(_id _id)
    {
        this._id = _id;
    }

    public JsonElement serialize()
    {
        JsonObject obj = new JsonObject();
        if (this.statement != null)
        {
            obj.add("statement", this.statement.serialize());
        }
        if (this._id != null)
        {
            obj.add("_id", this._id.serialize());
        }
        return obj;
    }
}

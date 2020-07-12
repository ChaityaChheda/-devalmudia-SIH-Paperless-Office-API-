package Entities;

import com.google.gson.JsonObject;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType(value="idTitle")
public class idTitle {
    int id;
    String title;

    public idTitle(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JsonObject toJSON () {
        JsonObject json = new JsonObject();
        json.addProperty("id", this.id);
        json.addProperty("title", this.title);
        return json;
    }
}

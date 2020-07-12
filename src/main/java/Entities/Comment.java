package Entities;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType(value="Comment")
public class Comment {
    private int id;
    private String name;
    private String message;
    private String timestamp;

    public Comment(int id, String name, String message, String timestamp) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

package Entities;

import com.datastax.driver.mapping.annotations.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.HashMap;
import java.util.List;

@Table
public class Menu2 {

    @PrimaryKey
    private int id;

    @Frozen
    private HashMap<String, List<idTitle>> contents;

    public Menu2(int id, HashMap<String, List<idTitle>> contents) {
        this.id = id;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, List<idTitle>> getContents() {
        return contents;
    }

    public void setContents(HashMap<String, List<idTitle>> contents) {
        this.contents = contents;
    }
}





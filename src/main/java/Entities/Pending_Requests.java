package Entities;

import com.datastax.driver.mapping.annotations.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Table
public class Pending_Requests {

    @PrimaryKey
    private String id;

    @Frozen
    private List<Request> requests;

    public Pending_Requests(String id, List<Request> requests) {
        this.id = id;
        this.requests = requests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}





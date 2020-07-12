package Entities;

import com.datastax.driver.mapping.annotations.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Table
public class test {
    @PrimaryKey
    private String id;

    private String Title;

    @Frozen
    List<Comment> comments;

    List<String> testList;

    public test(String id, String Title, List<Comment> comments, List<String> testList) {
        this.id = id;
        this.Title = Title;
        this.comments = comments;
        this.testList = testList;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<String> getTestList() {
        return testList;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}





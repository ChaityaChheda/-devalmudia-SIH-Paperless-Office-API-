package Entities;

import com.datastax.driver.mapping.annotations.Frozen;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.HashMap;
import java.util.List;

@Table
public class Workflow {
    @PrimaryKey
    private String id;

    private String Title;
    private String User;
    private String start_timestamp;
    private String end_timestamp;
    private String componentId;
    private String status;
    private String FormData;
    private String FlowChart;

    @Frozen
    private List<Comment> Comments;
    private List<String> Path;
    private List<String> nextNodes;
    private HashMap<String, String> Signatures;
    private boolean isRejected;

    public Workflow(String id, String Title, String User, String start_timestamp, String end_timestamp, String componentId, String status, String FormData, String FlowChart, List<Comment> Comments, List<String> Path, List<String> nextNodes, HashMap<String, String> Signatures, boolean isRejected) {
        this.id = id;
        this.Title = Title;
        this.User = User;
        this.start_timestamp = start_timestamp;
        this.end_timestamp = end_timestamp;
        this.componentId = componentId;
        this.status = status;
        this.FormData = FormData;
        this.FlowChart = FlowChart;
        this.Comments = Comments;
        this.Path = Path;
        this.nextNodes = nextNodes;
        this.Signatures = Signatures;
        this.isRejected = isRejected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(String start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public String getEnd_timestamp() {
        return end_timestamp;
    }

    public void setEnd_timestamp(String end_timestamp) {
        this.end_timestamp = end_timestamp;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormData() {
        return FormData;
    }

    public void setFormData(String formData) {
        FormData = formData;
    }

    public String getFlowChart() {
        return FlowChart;
    }

    public void setFlowChart(String flowChart) {
        FlowChart = flowChart;
    }

    public List<Comment> getComments() {
        return Comments;
    }

    public void setComments(List<Comment> comments) {
        Comments = comments;
    }

    public List<String> getPath() {
        return Path;
    }

    public void setPath(List<String> path) {
        Path = path;
    }

    public List<String> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<String> nextNodes) {
        this.nextNodes = nextNodes;
    }

    public HashMap<String, String> getSignatures() {
        return Signatures;
    }

    public void setSignatures(HashMap<String, String> signatures) {
        Signatures = signatures;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }

    public String getJSONString() throws JSONException {
        JSONObject flowChartJson = new JSONObject(this.FlowChart);
        JSONObject formDataJson = new JSONObject(this.FormData);
        JSONWorkFlow j = new JSONWorkFlow(id, Title, User,start_timestamp,end_timestamp,componentId,status, flowChartJson, formDataJson,getComments(),Path,nextNodes,Signatures,isRejected);
        Gson g = new Gson();
        String json = g.toJson(j);
        return json;
    }

    private class JSONWorkFlow {
        private String id;

        private String Title;
        private String User;
        private String start_timestamp;
        private String end_timestamp;
        private String componentId;
        private String status;
        private JSONObject FlowChart;
        private JSONObject FormData;
        private List<Comment> Comments;
        private List<String> Path;
        private List<String> nextNodes;
        private HashMap<String, String> Signatures;
        private boolean isRejected;

        public JSONWorkFlow(String id, String title, String user, String start_timestamp, String end_timestamp, String componentId, String status, JSONObject flowChartJson, JSONObject FormData, List<Comment> comments, List<String> path, List<String> nextNodes, HashMap<String, String> signatures, boolean isRejected) {
            this.id = id;
            Title = title;
            User = user;
            this.start_timestamp = start_timestamp;
            this.end_timestamp = end_timestamp;
            this.componentId = componentId;
            this.status = status;
            this.FlowChart = flowChartJson;
            this.FormData = FormData;
            Comments = comments;
            Path = path;
            this.nextNodes = nextNodes;
            Signatures = signatures;
            this.isRejected = isRejected;
        }
    }
}





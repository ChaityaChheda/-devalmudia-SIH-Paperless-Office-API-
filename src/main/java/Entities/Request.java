package Entities;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.sql.Date;
import java.sql.Timestamp;

@UserDefinedType(value="Request")
public class Request {

    private String id;
    private String nameofSender;
    private String subject;
    private String status;
    private Date date;
    private Timestamp receivedon;

    public Request(String id, String nameofSender, String subject, String status, Date date, Timestamp receivedon) {
        this.id = id;
        this.nameofSender = nameofSender;
        this.subject = subject;
        this.status = status;
        this.date = date;
        this.receivedon = receivedon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getNameofSender() {
        return nameofSender;
    }

    public void setNameofSender(String nameofSender) {
        this.nameofSender = nameofSender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getReceivedon() {
        return receivedon;
    }

    public void setReceivedon(Timestamp receivedon) {
        this.receivedon = receivedon;
    }
}

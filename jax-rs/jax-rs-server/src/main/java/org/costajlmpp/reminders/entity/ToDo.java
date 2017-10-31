package org.costajlmpp.reminders.entity;

import javax.json.bind.annotation.JsonbNillable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@JsonbNillable(false)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ToDo {

    private long id;

    @NotNull
    @Size(min = 3, max = 250)
    private String caption;
    private String description;
    private int priority;
    private boolean done;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

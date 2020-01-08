package com.realTalk.bot.DTO;


public class IMSDTO{
    private String id;
    private float created;
    private boolean is_archived;
    private boolean is_im;
    private boolean is_org_shared;
    private String user;
    private boolean has_pins;
    private String last_read;
    private boolean is_open;
    private float priority;


    // Getter Methods

    public String getId() {
        return id;
    }

    public float getCreated() {
        return created;
    }

    public boolean getIs_archived() {
        return is_archived;
    }

    public boolean getIs_im() {
        return is_im;
    }

    public boolean getIs_org_shared() {
        return is_org_shared;
    }

    public String getUser() {
        return user;
    }

    public boolean getHas_pins() {
        return has_pins;
    }

    public String getLast_read() {
        return last_read;
    }

    public boolean getIs_open() {
        return is_open;
    }

    public float getPriority() {
        return priority;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated(float created) {
        this.created = created;
    }

    public void setIs_archived(boolean is_archived) {
        this.is_archived = is_archived;
    }

    public void setIs_im(boolean is_im) {
        this.is_im = is_im;
    }

    public void setIs_org_shared(boolean is_org_shared) {
        this.is_org_shared = is_org_shared;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setHas_pins(boolean has_pins) {
        this.has_pins = has_pins;
    }

    public void setLast_read(String last_read) {
        this.last_read = last_read;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }
}
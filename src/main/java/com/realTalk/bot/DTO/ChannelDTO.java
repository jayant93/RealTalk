package com.realTalk.bot.DTO;

import com.realTalk.bot.Model.Member;

import java.util.ArrayList;


public class ChannelDTO {


    private String id;

    private String name;
    private boolean is_channel;
    private float created;
    private boolean is_archived;
    private boolean is_general;
    private float unlinked;
    private String creator;
    private String name_normalized;
    private boolean is_shared;
    private boolean is_org_shared;
    private boolean has_pins;
    private boolean is_member;
    private boolean is_private;
    private boolean is_mpim;
    private String last_read;

    ArrayList<String> members ;

    private float priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_channel() {
        return is_channel;
    }

    public void setIs_channel(boolean is_channel) {
        this.is_channel = is_channel;
    }

    public float getCreated() {
        return created;
    }

    public void setCreated(float created) {
        this.created = created;
    }

    public boolean isIs_archived() {
        return is_archived;
    }

    public void setIs_archived(boolean is_archived) {
        this.is_archived = is_archived;
    }

    public boolean isIs_general() {
        return is_general;
    }

    public void setIs_general(boolean is_general) {
        this.is_general = is_general;
    }

    public float getUnlinked() {
        return unlinked;
    }

    public void setUnlinked(float unlinked) {
        this.unlinked = unlinked;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName_normalized() {
        return name_normalized;
    }

    public void setName_normalized(String name_normalized) {
        this.name_normalized = name_normalized;
    }

    public boolean isIs_shared() {
        return is_shared;
    }

    public void setIs_shared(boolean is_shared) {
        this.is_shared = is_shared;
    }

    public boolean isIs_org_shared() {
        return is_org_shared;
    }

    public void setIs_org_shared(boolean is_org_shared) {
        this.is_org_shared = is_org_shared;
    }

    public boolean isHas_pins() {
        return has_pins;
    }

    public void setHas_pins(boolean has_pins) {
        this.has_pins = has_pins;
    }

    public boolean isIs_member() {
        return is_member;
    }

    public void setIs_member(boolean is_member) {
        this.is_member = is_member;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public boolean isIs_mpim() {
        return is_mpim;
    }

    public void setIs_mpim(boolean is_mpim) {
        this.is_mpim = is_mpim;
    }

    public String getLast_read() {
        return last_read;
    }

    public void setLast_read(String last_read) {
        this.last_read = last_read;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }
}

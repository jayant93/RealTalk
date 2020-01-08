package com.realTalk.bot.Model;


import javax.persistence.*;

@Entity
public class Profile {


    @Id
    private String email;

    private String title;
    private String phone;
    private String skype;
    private String real_name;
    private String real_name_normalized;
    private String display_name;
    private String display_name_normalized;
    private String fields = null;
    private String status_text;
    private String status_emoji;
    private float status_expiration;
    private String avatar_hash;


    private String first_name;
    private String last_name;
    private String image_24;
    private String image_32;
    private String image_48;
    private String image_72;
    private String image_192;
    private String image_512;
    private String status_text_canonical;
    private String team;



    @OneToOne
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
// Getter Methods

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getSkype() {
        return skype;
    }

    public String getReal_name() {
        return real_name;
    }

    public String getReal_name_normalized() {
        return real_name_normalized;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getDisplay_name_normalized() {
        return display_name_normalized;
    }

    public String getFields() {
        return fields;
    }

    public String getStatus_text() {
        return status_text;
    }

    public String getStatus_emoji() {
        return status_emoji;
    }

    public float getStatus_expiration() {
        return status_expiration;
    }

    public String getAvatar_hash() {
        return avatar_hash;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getImage_24() {
        return image_24;
    }

    public String getImage_32() {
        return image_32;
    }

    public String getImage_48() {
        return image_48;
    }

    public String getImage_72() {
        return image_72;
    }

    public String getImage_192() {
        return image_192;
    }

    public String getImage_512() {
        return image_512;
    }

    public String getStatus_text_canonical() {
        return status_text_canonical;
    }

    public String getTeam() {
        return team;
    }

    // Setter Methods

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public void setReal_name_normalized(String real_name_normalized) {
        this.real_name_normalized = real_name_normalized;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public void setDisplay_name_normalized(String display_name_normalized) {
        this.display_name_normalized = display_name_normalized;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }

    public void setStatus_emoji(String status_emoji) {
        this.status_emoji = status_emoji;
    }

    public void setStatus_expiration(float status_expiration) {
        this.status_expiration = status_expiration;
    }

    public void setAvatar_hash(String avatar_hash) {
        this.avatar_hash = avatar_hash;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setImage_24(String image_24) {
        this.image_24 = image_24;
    }

    public void setImage_32(String image_32) {
        this.image_32 = image_32;
    }

    public void setImage_48(String image_48) {
        this.image_48 = image_48;
    }

    public void setImage_72(String image_72) {
        this.image_72 = image_72;
    }

    public void setImage_192(String image_192) {
        this.image_192 = image_192;
    }

    public void setImage_512(String image_512) {
        this.image_512 = image_512;
    }

    public void setStatus_text_canonical(String status_text_canonical) {
        this.status_text_canonical = status_text_canonical;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
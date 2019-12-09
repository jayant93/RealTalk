package com.realTalk.bot.helpers.staticHelpers;

/*
this class will be used to collect user information
via auth questions and store it to the database
 */

public  class TempUserInfo {

    public static String name;

    public static String email;

    public static String teamName;

    public static String DateStartAtCompany;

    public static String getDateStartAtCompany() {
        return DateStartAtCompany;
    }

    public static void setDateStartAtCompany(String dateStartAtCompany) {
        DateStartAtCompany = dateStartAtCompany;
    }
    //    private String teamId;
//
//    private String userId;

    public static String timeZone;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        TempUserInfo.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        TempUserInfo.email = email;
    }

    public static String getTeamName() {
        return teamName;
    }

    public static void setTeamName(String teamName) {
        TempUserInfo.teamName = teamName;
    }

    public static String getTimeZone() {
        return timeZone;
    }

    public static void setTimeZone(String timeZone) {
        TempUserInfo.timeZone = timeZone;
    }
}

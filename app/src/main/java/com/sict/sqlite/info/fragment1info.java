package com.sict.sqlite.info;

public class fragment1info {
    private String jobname;
    private int date;
    private int hour;
    private int minute;
    private String hourstype;
public fragment1info(){}
    public fragment1info(String jobname, int date, int hour, int minute, String hourstype) {
        this.jobname = jobname;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.hourstype = hourstype;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getHourstype() {
        return hourstype;
    }

    public void setHourstype(String hourstype) {
        this.hourstype = hourstype;
    }
}

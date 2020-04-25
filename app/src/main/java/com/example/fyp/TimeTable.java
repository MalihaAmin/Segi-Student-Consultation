package com.example.fyp;

public class TimeTable {
    private boolean d8=false;
    private boolean d9=false;
    private boolean d10=false;
    private boolean d11=false;
    private boolean d12=false;
    private boolean a1=false;
    private boolean a2=false;
    private boolean a3=false;
    private boolean a4=false;
    private String weekday;
    private String lec_email;

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getLec_email() {
        return lec_email;
    }

    public void setLec_email(String lec_email) {
        this.lec_email = lec_email;
    }

    public TimeTable() {
        d8=false;
        d9=false;
        d10=false;
        d11=false;
        d12=false;
        a1=false;
        a2=false;
        a3=false;
        a4=false;
    }

    public boolean isD9() {
        return d9;
    }

    public void setD9(boolean d9) {
        this.d9 = d9;
    }

    public boolean isD10() {
        return d10;
    }

    public void setD10(boolean d10) {
        this.d10 = d10;
    }

    public boolean isD11() {
        return d11;
    }

    public void setD11(boolean d11) {
        this.d11 = d11;
    }

    public boolean isD12() {
        return d12;
    }

    public void setD12(boolean d12) {
        this.d12 = d12;
    }

    public boolean isA1() {
        return a1;
    }

    public void setA1(boolean a1) {
        this.a1 = a1;
    }

    public boolean isA2() {
        return a2;
    }

    public void setA2(boolean a2) {
        this.a2 = a2;
    }

    public boolean isA3() {
        return a3;
    }

    public void setA3(boolean a3) {
        this.a3 = a3;
    }

    public boolean isA4() {
        return a4;
    }

    public void setA4(boolean a4) {
        this.a4 = a4;
    }



    public boolean isD8() {
        return d8;
    }

    public void setD8(boolean d8) {
        this.d8 = d8;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personalinventory.bean;

/**
 *
 * @author HOME
 */
public class ExpensesCategoryBean {
    private int exp_catid;
    private String exp_catname;
    private String exp_catdetails;
    private int userid;

    public ExpensesCategoryBean() {
    }

    public ExpensesCategoryBean(int exp_catid, String exp_catname, String exp_catdetails, int userid) {
        this.exp_catid = exp_catid;
        this.exp_catname = exp_catname;
        this.exp_catdetails = exp_catdetails;
        this.userid = userid;
    }

    public int getExp_catid() {
        return exp_catid;
    }

    public void setExp_catid(int exp_catid) {
        this.exp_catid = exp_catid;
    }

    public String getExp_catname() {
        return exp_catname;
    }

    public void setExp_catname(String exp_catname) {
        this.exp_catname = exp_catname;
    }

    public String getExp_catdetails() {
        return exp_catdetails;
    }

    public void setExp_catdetails(String exp_catdetails) {
        this.exp_catdetails = exp_catdetails;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}

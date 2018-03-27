package com.shtoone.gdbhz.bean;

import java.io.Serializable;

/**
 * Created by leguang on 2016/8/4 0004.
 */
public class DepartmentData implements Cloneable, Serializable {
    public String departmentID;
    public String departmentName;
    public int fromto;

    /**********************/
    public String departtype;
    public String biaoshiid;
    public String testbiaoshiid;
    public String testdeparttype;
    public int lqupdata ;
    public String equipmentID="";

    @Override
    public String toString() {
        return "DepartmentData{" +
                "departmentName='" + departmentName + '\'' +
                ", departtype='" + departtype + '\'' +
                ", biaoshiid='" + biaoshiid + '\'' +
                ", lqupdata=" + lqupdata +
                '}';
    }

    /**********************/



    public DepartmentData() {
    }

    public DepartmentData(String departmentID, String departmentName, int fromto) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.fromto = fromto;
    }

    //克隆
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

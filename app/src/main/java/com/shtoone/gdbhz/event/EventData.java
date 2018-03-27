package com.shtoone.gdbhz.event;

import com.shtoone.gdbhz.bean.DepartmentData;

/**
 * Created by leguang on 2016/7/18 0018.
 */
public class EventData {
    public int position;

    public DepartmentData departmentData;
    public EventData(int position) {
        this.position = position;
    }

      public EventData(DepartmentData departmentData) {
        this.departmentData = departmentData;
    }


}

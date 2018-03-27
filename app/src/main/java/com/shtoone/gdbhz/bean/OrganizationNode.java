package com.shtoone.gdbhz.bean;

import com.shtoone.gdbhz.ui.treeview.annotation.TreeNodeEquipmentId;
import com.shtoone.gdbhz.ui.treeview.annotation.TreeNodeId;
import com.shtoone.gdbhz.ui.treeview.annotation.TreeNodeLabel;
import com.shtoone.gdbhz.ui.treeview.annotation.TreeNodePid;

/**
 * Author： hengzwd on 2017/3/9.
 * Email：hengzwdhengzwd@qq.com
 */

public class OrganizationNode {

    public OrganizationNode(String id, String pId, String name) {
        this.id = id;
        this.name = name;
        this.pId = pId;
    }


    public String getShebeibianhao() {
        return shebeibianhao;
    }

    public void setShebeibianhao(String shebeibianhao) {
        this.shebeibianhao = shebeibianhao;
    }

    @TreeNodeEquipmentId
    private String shebeibianhao;
    @TreeNodeId
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @TreeNodePid
    private String pId = "";

    @TreeNodeLabel
    private String name;
}

package com.jiangyifen.ec2.ui;

import com.vaadin.ui.Tree;

@SuppressWarnings("serial")
public class NavigationTree extends Tree{
	public static final Object father = "系统管理";
	public static final Object user = "用户管理";
	public static final Object dept = "部门管理";
	public NavigationTree(EntranceUi entranceUi) {
		
		this.setSelectable(true);
		this.addItem(father);
		this.addItem(user);
		this.addItem(dept);
		this.setParent(user, father);
		this.setChildrenAllowed(user, false);
		this.setParent(dept, father);
		this.setChildrenAllowed(dept, false);
		this.expandItemsRecursively(father);
		this.addListener(entranceUi);
		
	}
}

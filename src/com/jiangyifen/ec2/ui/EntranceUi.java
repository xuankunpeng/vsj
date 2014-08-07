package com.jiangyifen.ec2.ui;

import com.jiangyifen.ec2.ui.department.DeptPanel;
import com.jiangyifen.ec2.ui.user.UserPanel;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vsj.ui.Clock;

public class EntranceUi extends VerticalLayout implements ItemClickListener {

	private static final long serialVersionUID = 4060303758634038965L;
	private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
	private NavigationTree selectTree;

	public EntranceUi() {

		this.setSizeFull();

		selectTree = new NavigationTree(this);
		horizontalSplit.setFirstComponent(selectTree);
		horizontalSplit.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		horizontalSplit.setSizeFull();
		this.addComponent(new Label("<h2>Efficient Call</h2>", Label.CONTENT_XHTML));
		this.addComponent(horizontalSplit);
		this.setExpandRatio(horizontalSplit, 1);

	}

	@Override
	public void itemClick(ItemClickEvent event) {

		if (event.getSource() == selectTree) {
			Object itemId = event.getItemId();
			if (itemId == NavigationTree.user) {
				horizontalSplit.setSecondComponent(new UserPanel());
			} else if (itemId == NavigationTree.dept) {
				// horizontalSplit.replaceComponent(panel, new DeptPanel(this));
				horizontalSplit.setSecondComponent(new DeptPanel());
			}
		}

	}
}

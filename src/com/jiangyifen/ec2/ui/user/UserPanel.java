package com.jiangyifen.ec2.ui.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiangyifen.ec2.entity.User;
import com.jiangyifen.ec2.service.eaoservice.UserService;
import com.jiangyifen.ec2.ui.MyUploadUi;
import com.jiangyifen.ec2.utils.FlipOverTableComponent;
import com.jiangyifen.ec2.utils.SpringContextHolder;
import com.jiangyifen.ec2.utils.TimeShowThread;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class UserPanel extends VerticalLayout implements ClickListener, ValueChangeListener {

	private static final long serialVersionUID = -2168888260547090789L;

	private HorizontalLayout hlayout;
	private Button fetchBtn = new Button("获取", this);
	private Button addButton = new Button("添加", this);
	private Button editButton = new Button("编辑", this);
	private Button delButton = new Button("删除", this);
	private Button searchBtn = new Button("搜索", this);
	private Button timeBtn = new Button("显示时间", this);
	private Label lb_time = new Label();

	private ProgressIndicator indicator;

	private Table userTable;
	private String sqlSelect;// 查询语句
	private String sqlCount;
	private User user;
	private TextField keyWord;// 关键字输入框
	private TextField idField;// 按Id搜索
	private MyUploadUi myUpload;// 导入资源

	private VerticalLayout vlayout;
	private String userName = "";
	private String id;
	@SuppressWarnings("rawtypes")
	private Map map = new HashMap();

	private BeanItemContainer<User> userContainer;

	private AddUserWindow addUserWindow;
	private EditUserWindow editUserWindow;
	private UserService userService = SpringContextHolder.getBean("userService");

	private Object[] NATURAL_COL_ORDER = new Object[] { "id", "username", "age", "phoneNumber",
			"dept", "date" };
	private String[] COL_HEADERS = new String[] { "ID", "用户名", "年龄", "电话", "部门", "入职日期" };

	private FlipOverTableComponent<User> flip;

	public UserPanel() {

		this.setCaption("用户信息表");
		vlayout = new VerticalLayout();

		userTable = new Table("用户信息表");// 创建table
		userTable.setSizeFull();
		userTable.setSelectable(true);
		userTable.setImmediate(true);
		userTable.setPageLength(30);
		userTable.setNullSelectionAllowed(false);
		userTable.addListener(this);

		// userContainer = new BeanItemContainer<User>(User.class);
		// userTable.setContainerDataSource(userContainer);
		// userTable.setVisibleColumns(NATURAL_COL_ORDER);
		// userTable.setColumnHeaders(COL_HEADERS);

		vlayout.setSpacing(true);
		vlayout.addComponent(createSearchBtn());
		vlayout.addComponent(userTable);

		this.addComponent(vlayout);

		// findAllUser();

		this.addComponent(createButten());// 添加增删改按钮

		// userContainer.removeAllItems();
		// User user = new User();
		// user.setId(1l);
		// user.setDept("888888888888888");
		// userContainer.addItem(user);
	}

	/**
	 * 创建增删改按钮
	 * 
	 * @return
	 */
	public HorizontalLayout createButten() {
		hlayout = new HorizontalLayout();
		hlayout.setWidth("100%");
		hlayout.setSpacing(true);

		HorizontalLayout hlayout1 = new HorizontalLayout();
		hlayout1.setSpacing(true);
		hlayout1.addComponent(fetchBtn);
		hlayout1.addComponent(addButton);
		hlayout1.addComponent(editButton);
		hlayout1.addComponent(delButton);

		flip = new FlipOverTableComponent<User>(User.class, userService, userTable, sqlSelect,
				sqlCount, this);
		// entityCount = flip.getTotalRecord();
		userTable.setPageLength(18);
		flip.setPageLength(18, false);

		userTable.setVisibleColumns(NATURAL_COL_ORDER);
		userTable.setColumnHeaders(COL_HEADERS);

		hlayout.addComponent(hlayout1);
		hlayout.addComponent(flip);
		hlayout.setComponentAlignment(flip, Alignment.BOTTOM_RIGHT);

		hlayout.setSpacing(true);
		return hlayout;
	}

	/**
	 * 创建搜索部分
	 * 
	 * @return hlayout
	 */
	@SuppressWarnings("deprecation")
	public HorizontalLayout createSearchBtn() {

		hlayout = new HorizontalLayout();
		indicator = new ProgressIndicator();
		indicator.setHeight(UNITS_PIXELS);
		indicator.setWidth(UNITS_PIXELS);
		hlayout.addComponent(indicator);
		hlayout.setSpacing(true);
		this.addComponent(hlayout);

		Label caption = new Label("关键字：");
		caption.setWidth("-1px");
		hlayout.addComponent(caption);

		keyWord = new TextField();
		keyWord.setImmediate(true);
		keyWord.setInputPrompt("请输入搜索关键字");
		keyWord.setDescription("可按'用户名'进行搜索");
		keyWord.addListener(this);
		hlayout.addComponent(keyWord);

		Label IdCaption = new Label("ID：");
		IdCaption.setWidth("-1px");
		hlayout.addComponent(IdCaption);

		idField = new TextField();
		idField.setImmediate(true);
		idField.setInputPrompt("请输入搜索关键字");
		idField.setDescription("可按'ID'进行搜索");
		idField.addListener(this);
		hlayout.addComponent(idField);

		searchBtn.setImmediate(true);
		hlayout.addComponent(searchBtn);

		// uploadUi=new UploadUi();
		myUpload = new MyUploadUi(this);
		// myUpload.importExcle();
		hlayout.addComponent(myUpload);

		/*
		 * MyTime mt = new MyTime(); new TimeThread(mt).start();
		 */

		/* hlayout.addComponent(mt); */
		hlayout.addComponent(timeBtn);
		lb_time.setImmediate(true);

		lb_time.setData(new Date());
		hlayout.addComponent(lb_time);

		return hlayout;
	}

	/**
	 * 查询所有User并加入Container
	 */
	public void findAllUser() {
		userName = "";
		List<User> list = userService.getAllUsers(userName);
		userContainer.removeAllItems();
		userContainer.addAll(list);
	}

	/**
	 * 初始化sql
	 */
	private void initializeSql() {

		userName = keyWord.getValue().toString();
		id = idField.getValue().toString().trim();

		sqlSelect = "select s from User as s where 1=1 ";

		if (userName != null && !userName.equals("")) {
			sqlSelect += " and s.username like '%" + userName + "%' ";
		}
		if (id != null && !id.equals("")) {
			sqlSelect += " and s.id= " + id;
		}

		System.out.println("sqlSelect:---" + sqlSelect);

		sqlCount = "select count(e) from User e ";
	}

	/**
	 * 刷新Table
	 * 
	 * @param isToFirst
	 */
	public void refreshTable(Boolean isToFirst) {
		if (flip != null) {
			flip.setSearchSql(sqlSelect);
			flip.setCountSql(sqlCount);
			if (isToFirst) {
				flip.refreshToFirstPage();
			} else {
				flip.refreshInCurrentPage();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buttonClick(ClickEvent event) {
		Button btn = event.getButton();
		if (btn == addButton) {

			addUserWindow = new AddUserWindow(this);
			getWindow().addWindow(addUserWindow);
		} else if (btn == editButton) {

			editUserWindow = new EditUserWindow(this, user.getId());
			getWindow().addWindow(editUserWindow);
		} else if (btn == delButton) {

			userService.deleteUser(user);
			findAllUser();
		} else if (btn == searchBtn) {

			// userName = keyWord.getValue().toString();
			// id = idField.getValue().toString().trim();
			// map.put("userName", userName);
			// map.put("id", id);

			initializeSql();
			refreshTable(true);

			// List<User> list = userService.getAllUsers(map);
			// userContainer.removeAllItems();
			// userContainer.addAll(list);

		} else if (btn == fetchBtn) {

			findAllUser();
		} else if (btn == timeBtn) {

			TimeShowThread timeShowThread = new TimeShowThread(lb_time);
			timeShowThread.start();

			/*
			 * Thread timeThread = new Thread(new Runnable(){
			 * 
			 * @Override public void run() { System.out.println("------");
			 * lb_time.setCaption("=++++++++"); }
			 * 
			 * }); timeThread.start();
			 */

		}
	}

	@Override
	public void valueChange(ValueChangeEvent event) {

		Property property = event.getProperty();
		if (property == userTable) {

			user = (User) userTable.getValue();

		}

	}

}

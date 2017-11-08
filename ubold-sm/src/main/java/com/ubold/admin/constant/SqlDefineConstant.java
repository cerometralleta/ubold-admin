package com.ubold.admin.constant;

public class SqlDefineConstant {
	
	public static final String COLUMNTYPE_INT	  = "int";
	public static final String COLUMNTYPE_VARCHAR = "varchar";
	public static final String COLUMNTYPE_CLOB	  = "clob";
	public static final String COLUMNTYPE_NUMBER  = "number";
	public static final String COLUMNTYPE_DATE	  = "date";
	public static final String COLUMNTYPE_TEXT	  = "text";

	public static final Integer YES	  = 1;
	public static final Integer NO	  = 0;
	
	public static final Integer ASC	  = 0;
	public static final Integer DESC  = 1;
	
	/**
	 * 修改类型
	 */
	public static final String MODIFTY_HIDE = "hide";
	public static final String MODIFTY_ENABLE = "enable";
	public static final String MODIFTY_DISABLE = "disable";

	public static final String align_center = "center";
	public static final String align_left = "left";
	public static final String align_right = "right";

	public static final String valign_middle = "middle";
	public static final String valign_top = "top";
	public static final String valign_bottom = "bottom";

	/**
	 * 导航按钮
	 */
	public static final Integer BUTTON_NAV	 = 1;
	/**
	 * 列表按钮
	 */
	public static final Integer BUTTON_TABLE = 0;
	
	public static final String VERSION	  = "VERSION";
	public static final String CREATE_TIME	  = "CREATE_TIME";
	public static final String CREATE_USER	  = "CREATE_USER";
	public static final String LAST_UPDATE_TIME	  = "LAST_UPDATE_TIME";
	public static final String LAST_UPDATE_USER	  = "LAST_UPDATE_USER";
	
	/**
	 * 所有root节点统一上级为0
	 */
	public static final String TREE_ROOT = "0";
	
	/**
	 * SQL IN 空默认值
	 * 000000
	 */
	public static final String IN_NONE_CODE = "000000";
}

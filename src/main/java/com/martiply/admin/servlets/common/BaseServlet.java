package com.martiply.admin.servlets.common;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import com.martiply.db.tables.TableCoupon;
import com.martiply.db.tables.TableOwner;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_PAGE_NOT_AVAILABLE = "SESSION_PAGE_NOT_AVAILABLE";
//	public static final String SESSION_BID_CURRENCY = "SESSION_BID_CURRENCY";
	public static final String SESSION_TEMPORARY_CREDENTIALS = "SESSION_TEMPORARY_CREDENTIALS";
	public static final String SESSION_CALLBACK_ERROR = "SESSION_CALLBACK_ERROR";
	public static final String SESSION_REQUEST_TOKEN = "SESSION_REQUEST_TOKEN";
	public static final String SESSION_STORE_ID_VIEW = "SESSION_STORE_ID_VIEW";
	public static final String SESSION_OWNER_ID = TableOwner.OWNER_ID;
	protected static final String SESSION_CSV = "SESSION_CSV";

	
	public static final String SESSION_CURRENCY = "SESSION_CURRENCY";
	public static final String SESSION_PRETTY_CURRENCY = "SESSION_PRETTY_CURRENCY";
	public static final String SESSION_DEFAULT_BID = "SESSION_DEFAULT_BID";
	public static final String SESSION_BID_INCREMENT = "SESSION_BID_INCREMENT";
	public static final String SESSION_BID_INCREMENT_STRING = "SESSION_BID_INCREMENT_STRING";
	public static final String SESSION_CREDIT = "SESSION_CREDIT";
	
	public static final String SESSION_CP_ID = "SESSION_CP_ID";
	public static final String SESSION_CP_CONTENT = "SESSION_CP_CONTENT";
	public static final String SESSION_AVAILABLE_STORES = "SESSION_AVAILABLE_STORES";
	

	public static final String ATTR_PAGE_SIDE_ITEM = "PAGE_SIDE_ITEM";
	public static final String ATTR_PAGE_CONTENT = "PAGE_CONTENT";
	public static final String ATTR_ERROR = "ERROR";
	public static final String ATTR_DASHBOARD_TITLE = "HOME_TITLE";
	public static final String ATTR_DASHBOARD_SUBTITLE = "HOME_SUBTITLE";
	public static final String ATTR_ITEM_COUNT = "ITEM_COUNT";
	public static final String ATTR_NOTIFICATIONS = "NOTIFICATIONS";
	public static final String ATTR_LIST_COUPONS = "LIST_COUPONS";
	protected static final String ATTR_CPID = "CPID";
	protected static final String ATTR_CONTENT = "CONTENT";
	protected static final String ATTR_COUPON = "COUPON";
	protected static final String ATTR_STATS = "STATS";
	protected static final String ATTR_LIST_ITEMS = "LIST_ITEMS";
	protected static final String ATTR_STORE = "STORE";
	protected static final String ATTR_LIST_STORES = "LIST_STORES";
	protected static final String ATTR_TS_NOW = "TS_NOW";
			
	
	public static final String PARAM_A = "a";
	public static final String PARAM_I = "i";
	public static final String PARAM_A_ARRAY = "a[]";
	public static final String PARAM_PARTKEY_ARRAY = "p[]";
	public static final String PARAM_IMG = "img";
	
	public static final String SERVLET_HOME = "/home";
	protected static final String SERVLET_TWITTER_CALLBACK = "/callback";
	protected static final String SERVLET_LOGIN = "/login";
	protected static final String SERVLET_LOGOUT = "/logout";
	protected static final String SERVLET_IMAGE_DELETE = "/imd";
	protected static final String SERVLET_IMAGE_UPLOAD = "/imu";
	public static final String SERVLET_CSV_DOWNLOAD = "/getcsv";
	public static final String SERVLET_ITEM_DELETE = "/idel";
	public static final String SERVLET_CSV_MULTI_UPLOAD = "/csv";
	public static final String SERVLET_ITEM_UPDATE = "/ani";
	public static final String SERVLET_INVENTORY_HOME = "/inventory";
	public static final String SERVLET_COUPON_CHANGE_BID = "/acbid";
	public static final String SERVLET_COUPON_VERIFY_TWEETID = "/ativ";
	public static final String SERVLET_COUPON_CHOOSE = "/cpchoose";
	public static final String SERVLET_COUPON_NEW = "/cpnew";
	public static final String SERVLET_COUPON_HOME = "/cp";
	public static final String SERVLET_COUPON_VIEW = "/cpview";
	public static final String SERVLET_STORE_HOME = "/store";
	
	
	protected static final String VALUE_STORE = "s";
	protected static final String VALUE_INVENTORY = "i";
	protected static final String VALUE_TEMP = "temp";


	public int getOwnerSessionActive(HttpSession session) throws IOException{
		if(session != null){
			if (session.getAttribute(SESSION_OWNER_ID) != null){
				return (Integer)session.getAttribute(SESSION_OWNER_ID);
			}else{
				session.invalidate();
				return 0;
			}			
		}else{
			return 0;
		}
	}
	

	
	
	


}

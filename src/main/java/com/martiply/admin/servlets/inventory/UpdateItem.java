package com.martiply.admin.servlets.inventory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.martiply.admin.servlets.common.BaseServlet;
import com.martiply.dao.StandardDAO;
import com.martiply.db.tables.TableStandard;
import com.martiply.db.tables.TableStandardApparel;
import com.martiply.model.BasicItem;

@WebServlet(name = "Item Update", description = "Update or Create a new inventory item", urlPatterns = { BaseServlet.SERVLET_ITEM_UPDATE})
public class UpdateItem extends BaseServlet{
	
	private static final long serialVersionUID = 5491273094840508365L;
	private StandardDAO standardDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());	
		standardDAO = (StandardDAO)ctx.getBean("standardDAO");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/plain");
	    PrintWriter out = response.getWriter();	
	    
		HttpSession session = request.getSession(false);
		int ownerId = getOwnerSessionActive(session);			
		if (ownerId == 0){
			out.println("-1");
			return;
		}
	
		String idType = request.getParameter(TableStandard.ID_TYPE);
		String gtin = request.getParameter(TableStandard.GTIN);
		String idCustom = request.getParameter(TableStandard.ID_CUSTOM);
		String brand = request.getParameter(TableStandard.BRAND);
		String name = request.getParameter(TableStandard.NAME);
		String category = request.getParameter(TableStandard.CATEGORY);
		String condition = request.getParameter(TableStandard.COND);
		String stringPrice = request.getParameter(TableStandard.PRICE);
		String description = request.getParameter(TableStandard.DESCRIPTION);
		String url = request.getParameter(TableStandard.URL);
		
		
		String salePrice = request.getParameter(TableStandard.SALE_PRICE);
		// both saleStart and saleEnd are in timestamp second
		String saleStart = request.getParameter(TableStandard.SALE_START);
		String saleEnd = request.getParameter(TableStandard.SALE_END);

		
		String gender = request.getParameter(TableStandardApparel.GENDER);
		String age = request.getParameter(TableStandardApparel.AGE);
		String sizeSystem = request.getParameter(TableStandardApparel.SIZE_SYSTEM);
		String size = request.getParameter(TableStandardApparel.SIZE);
		String color = request.getParameter(TableStandardApparel.COLOR);
		String feature = request.getParameter(TableStandardApparel.FEATURE);
		String material = request.getParameter(TableStandardApparel.MATERIAL);
		String groupId = request.getParameter(TableStandardApparel.GROUP_ID);		
		
		SqlUpload sqlUpload = new SqlUpload(ownerId);
		String baseError = sqlUpload.testBasic(idType, gtin, idCustom, brand, name, category, stringPrice, condition, description, url);
		if (baseError != null){
			out.print(baseError);
			return;			
		}
		
		String apparelError = sqlUpload.testApparel(gender, age, sizeSystem, size, color, feature, material, groupId);
		if (apparelError != null){
			out.print(apparelError);
			return;			
		}
		
		String saleError = sqlUpload.testSale(salePrice, saleStart, saleEnd);
		if (saleError != null){
			out.print(saleError);
			return;			
		}
		
		BasicItem item = sqlUpload.item;
		item.setId(StandardDAO.makeKey(item));
		standardDAO.insert(item);
		
		out.println("0");

	}

}

package com.martiply.admin.servlets.inventory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

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

import au.com.bytecode.opencsv.CSVWriter;

@WebServlet(name = "CSV Download", description = "Donwlod items as csv", urlPatterns = { BaseServlet.SERVLET_CSV_DOWNLOAD })
public class CSVDownload extends BaseServlet {
	private static final long serialVersionUID = -1546959640472437487L;
	private StandardDAO standardDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		standardDAO = (StandardDAO) ctx.getBean("standardDAO");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"userDirectory.csv\"");
		ArrayList<BasicItem> items = (ArrayList<BasicItem>) session.getAttribute(SESSION_CSV);
		try {
			OutputStream outputStream = response.getOutputStream();
			CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream));
			String[] headers = {TableStandard.ID_TYPE, TableStandard.GTIN, TableStandard.ID_CUSTOM, TableStandard.BRAND, TableStandard.NAME, TableStandard.CATEGORY, TableStandard.PRICE,
					"condition", TableStandard.DESCRIPTION, TableStandard.URL, TableStandard.SALE_PRICE, TableStandard.SALE_START, TableStandard.SALE_END,
					TableStandardApparel.GENDER, TableStandardApparel.AGE, TableStandardApparel.SIZE_SYSTEM, TableStandardApparel.SIZE, TableStandardApparel.COLOR, TableStandardApparel.FEATURE,
					TableStandardApparel.MATERIAL, TableStandardApparel.GROUP_ID};			
			csvWriter.writeNext(headers);
			
			for (BasicItem item : items){
				csvWriter.writeNext(SqlUpload.csvItem(item));
			}
			session.removeAttribute(SESSION_CSV);
			csvWriter.flush();
			csvWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			out.println("-1");
			return;
		}

		String[] partialKeys = request.getParameterValues(PARAM_PARTKEY_ARRAY);
		ArrayList<BasicItem> items = standardDAO.getItems(ownerId, partialKeys);
		session.setAttribute(SESSION_CSV, items);

		out.println("0");


	}
}

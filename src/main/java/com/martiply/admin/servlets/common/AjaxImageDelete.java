package com.martiply.admin.servlets.common;

import java.io.IOException;
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

import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.martiply.admin.servlets.common.AjaxImageUpload.AIURequest;
import com.martiply.dao.StandardDAO;
import com.martiply.dao.StoreDAO;
import com.martiply.model.Store;
import com.martiply.utils.S3AdminUtil;
import com.martiply.utils.S3Util;

@WebServlet(name = "AJAX Image Delete", urlPatterns = { BaseServlet.SERVLET_IMAGE_DELETE })
public class AjaxImageDelete extends BaseServlet {
	private static final long serialVersionUID = -2207356891715070496L;
	private StoreDAO storeDAO;
	private StandardDAO standardDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		storeDAO = (StoreDAO) ctx.getBean("storeDAO");
		standardDAO = (StandardDAO) ctx.getBean("standardDAO");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		int ownerId = getOwnerSessionActive(session);
		if (ownerId == 0) {
			out.println("-1");;
			return;
		}
		
		AIURequest aiuRequest = AIURequest.newInstance(request.getParameter(PARAM_A));
		ArrayList<KeyVersion> keyVersions = new ArrayList<DeleteObjectsRequest.KeyVersion>();

		switch (aiuRequest.a) {

		case VALUE_STORE: 
			Store store = storeDAO.getStore(aiuRequest.storeId, ownerId);

			if (store == null) {
				out.print("Permission denied");
				return;
			}
			keyVersions.add(new KeyVersion(S3Util.getImageStoreKey(aiuRequest.storeId, aiuRequest.which, S3Util.SIZE_SOURCE)));
			keyVersions.add(new KeyVersion(S3Util.getImageStoreKey(aiuRequest.storeId, aiuRequest.which, S3Util.SIZE_LARGE)));
			keyVersions.add(new KeyVersion(S3Util.getImageStoreKey(aiuRequest.storeId, aiuRequest.which, S3Util.SIZE_NORMAL)));
			break;

		case VALUE_INVENTORY:

			if (standardDAO.getItem(aiuRequest.itemId, ownerId) == null) {
				out.print("Permission denied");
				return;
			}

			keyVersions.add(new KeyVersion(S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_SOURCE)));
			keyVersions.add(new KeyVersion(S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_LARGE)));
			keyVersions.add(new KeyVersion(S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_NORMAL)));
			keyVersions.add(new KeyVersion(S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_SMALL)));
			keyVersions.add(new KeyVersion(S3Util.getImageItemKey(aiuRequest.itemId, aiuRequest.which, S3Util.SIZE_XSMALL)));
			break;
		}

		S3AdminUtil.deleteObjects(keyVersions);
		out.print("0"); // Success !
	}

}

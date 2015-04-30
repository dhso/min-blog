package com.minws.controller.sys;

import org.apache.commons.fileupload.FileUploadException;
import org.json.JSONException;

import com.jfinal.core.Controller;
import com.minws.frame.sdk.ueditor.UeditorKit;

public class MainController extends Controller {

	public void index() {
		redirect("/blog/index");
	}

	public void ueditor() throws JSONException, FileUploadException {
		String result = new UeditorKit(getRequest()).exec();
		renderText(result);
	}

}

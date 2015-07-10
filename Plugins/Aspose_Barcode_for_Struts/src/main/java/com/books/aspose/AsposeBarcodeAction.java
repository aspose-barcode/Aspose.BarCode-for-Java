package com.books.aspose;

import java.io.IOException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.Action;

import com.books.aspose.AsposeAPI;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by Adeel Ilyas on 7/8/2015.
 */
/*
 * @author: Adeel Ilyas Company: Aspose Pty Ltd.
 */
public class AsposeBarcodeAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Aspose Barcode Image");
		try {
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("image/png");
        

            String symbology = request.getParameter("symbology");
            String codeText = request.getParameter("billAmount");

            AsposeAPI.createAsposeBarCode(codeText, out,symbology);
            out.flush();
            out.close();


            System.out.println("BillAmount: " + codeText);
   

        } catch (IOException io) {
        	io.printStackTrace();

        }

		return null;
	}
}
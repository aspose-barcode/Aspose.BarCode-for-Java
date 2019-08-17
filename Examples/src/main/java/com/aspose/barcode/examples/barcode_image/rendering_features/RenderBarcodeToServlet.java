package com.aspose.barcode.examples.barcode_image.rendering_features;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

import javax.servlet.*;
import javax.servlet.http.*;

import com.aspose.barcode.generation.BarcodeGenerator;

//ExStart: RenderBarcodeToServlet
public class RenderBarcodeToServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "1234567");
		BufferedImage image = bb.generateBarCodeImage();

		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "png", outputStream);
		outputStream.close();
	}
}
//ExEnd: RenderBarcodeToServlet
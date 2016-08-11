package com.aspose.barcode.examples.barcode_image.rendering_features;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;

public class RenderBarcodeToServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.Code128);
		b.setCodeText("12345678");
		BufferedImage image = b.getBarCodeImage();

		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "png", outputStream);
		outputStream.close();
	}

}

package com.aspose.barcode.examples.barcode_image.rendering_features;

import java.awt.Image;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;

public class RenderBarcodeToImageInstance {

	public static void main(String[] args) {
		
		BarCodeBuilder bb = new BarCodeBuilder();
		bb.setSymbologyType(Symbology.Code128);
		bb.setCodeText("12345678");
		
		// Generate bar code image
		Image image = bb.generateBarCodeImage();
	}

}

package com.aspose.barcode.examples.barcode_image.rendering_features;

import java.awt.Image;

import com.aspose.barcode.BarCodeBuilder;

public class RenderBarcodeToImageInstance {

	public static void main(String[] args) {
		
		BarCodeBuilder bb = new BarCodeBuilder();
                bb.setEncodeType(com.aspose.barcode.EncodeTypes.CODE_128);
		bb.setCodeText("12345678");
		
		// Generate bar code image
		Image image = bb.generateBarCodeImage();
	}

}

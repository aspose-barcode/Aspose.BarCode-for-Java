package com.aspose.barcode.examples.barcode_image.rendering_features;

import java.awt.Image;

import com.aspose.barcode.generation.BarcodeGenerator;

public class RenderBarcodeToImageInstance {

	public static void main(String[] args) {
		//ExStart: RenderBarcodeToImageInstance
		BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "12345678");
		
		// Generate bar code image
		Image image = bb.generateBarCodeImage();
		//ExEnd: RenderBarcodeToImageInstance
	}

}

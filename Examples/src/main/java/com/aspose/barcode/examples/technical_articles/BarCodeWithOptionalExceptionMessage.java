package com.aspose.barcode.examples.technical_articles;

import java.awt.image.BufferedImage;

import com.aspose.barcode.generation.BarcodeGenerator;

public class BarCodeWithOptionalExceptionMessage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ExStart: BarCodeWithOptionalExceptionMessage
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.EAN_13, "348503498549085409");

		// Error message will not been thrown in case of false, default value is also
		// false
		generator.getParameters().getBarcode().setThrowExceptionWhenCodeTextIncorrect(true);

		// Get barcode image
		BufferedImage image = generator.generateBarCodeImage();
		// ExEnd: BarCodeWithOptionalExceptionMessage
	}

}

package com.aspose.barcode.examples.barcode_image.utility_features;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.EncodeTypes;

public class SaveBarcodeImageToStreams {

	public static void main(String[] args) throws IOException {
		// ExStart: SaveBarcodeImageToStreams
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128, "123456");
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		generator.save(outStream, BarCodeImageFormat.JPEG);
		// ExEnd: SaveBarcodeImageToStreams
	}
}

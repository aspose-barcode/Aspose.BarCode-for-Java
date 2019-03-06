package com.aspose.barcode.examples.barcode_image.utility_features;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.aspose.barcode.generation.BarCodeGenerator;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.EncodeTypes;

public class SaveBarcodeImageToStreams {

	public static void main(String[] args) throws IOException {
    	
		BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.CODE_128,"123456");
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		generator.save(outStream, BarCodeImageFormat.JPEG);
	}
}

package com.aspose.barcode.examples.technical_articles;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.generation.BarCodeGenerator;

public class Pdf417BarcodeWithChineseCharacters {

	public static void main(String[] args) throws IOException {
		// ExStart: Pdf417BarcodeWithChineseCharacters
		// Generate the barcode
		BarCodeGenerator gen = new BarCodeGenerator(EncodeTypes.PDF_417);
				 
		// Set the code text
		String codetext = "被洪水困住的";
		ByteBuffer bytebuffer = Charset.forName("MS936").encode(codetext);
		byte[] bytes = bytebuffer.array();
		String codeText = new String(bytes);
		gen.setCodeText(codeText);
				 
		// Set the display text
		gen.getD2().setDisplayText(codetext);
		gen.save("D:\\barcode.png");
		// ExEnd: Pdf417BarcodeWithChineseCharacters
	}
}

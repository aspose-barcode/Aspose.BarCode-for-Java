package com.aspose.barcode.examples.technical_articles;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.generation.BarCodeGenerator;

public class Pdf417BarcodeWithTurkishCharacters {

	public static void main(String[] args) throws IOException {
		// ExStart: Pdf417BarcodeWithTurkishCharacters
		// Generate the barcode
		BarCodeGenerator gen = new BarCodeGenerator(EncodeTypes.PDF_417);
		 
		// Set the code text
		String codetext = "AYŞE" + "\n" + "Ümit" + "\n" + "Ümit@estee.com" + "\n" + "Türkiye";
		ByteBuffer bytebuffer = Charset.forName("windows-1254").encode(codetext);
		byte[] bytes = bytebuffer.array();
		String codeText = new String(bytes);
		gen.setCodeText(codeText);
		 
		// Set the display text
		gen.getD2().setDisplayText(codetext);
		gen.save("D:\\barcode.png");
		// ExEnd: Pdf417BarcodeWithTurkishCharacters
	}

}

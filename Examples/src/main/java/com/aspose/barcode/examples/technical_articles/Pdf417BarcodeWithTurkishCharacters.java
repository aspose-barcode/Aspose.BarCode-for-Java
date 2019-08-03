package com.aspose.barcode.examples.technical_articles;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class Pdf417BarcodeWithTurkishCharacters {

	public static void main(String[] args) throws IOException {
		// ExStart: Pdf417BarcodeWithTurkishCharacters
		String dataDir = Utils.getDataDir(Pdf417BarcodeWithTurkishCharacters.class) + "TechnicalArticles/";
		// Generate the barcode
		BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PDF_417);
		 
		// Set the code text
		String codetext = "AYŞE" + "\n" + "Ümit" + "\n" + "Ümit@estee.com" + "\n" + "Türkiye";
		ByteBuffer bytebuffer = Charset.forName("windows-1254").encode(codetext);
		byte[] bytes = bytebuffer.array();
		String codeText = new String(bytes);
		gen.setCodeText(codeText);
		 
		// Set the display text
		gen.getParameters().getBarcode().getCodeTextParameters().setTwoDDisplayText(codetext);
		gen.save(dataDir + "barcode.png");
		// ExEnd: Pdf417BarcodeWithTurkishCharacters
	}

}

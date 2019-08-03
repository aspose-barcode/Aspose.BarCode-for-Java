package com.aspose.barcode.examples.barcode.basic_features;

import java.awt.Font;
import java.io.IOException;

import com.aspose.barcode.Caption;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.TextAlignment;

public class BarcodeCaption {

	public static void main(String[] args) throws IOException {
		// ExStart: BarcodeCaption
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(BarcodeCaption.class) + "Barcode/BasicFeatures/";

		// Create instance of BarcodeGenerator, specify codetext and symbology in the
		// constructor
		BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "12345678");

		bb.getParameters().getCaptionAbove().setAlignment(TextAlignment.LEFT);
		bb.getParameters().getCaptionAbove().setText("Aspose.Demo");
		bb.getParameters().getCaptionAbove().setVisible(true);
		bb.getParameters().getCaptionAbove().getFont().setFamilyName("Pristina");
		bb.getParameters().getCaptionAbove().getFont().getSize().setPoint(14);
		bb.getParameters().getCaptionAbove().setTextColor(java.awt.Color.RED);

		bb.getParameters().getCaptionBelow().setAlignment(TextAlignment.RIGHT);
		bb.getParameters().getCaptionBelow().setText("Aspose.Demo");
		bb.getParameters().getCaptionBelow().setVisible(true);
		bb.getParameters().getCaptionBelow().getFont().setFamilyName("Pristina");
		bb.getParameters().getCaptionBelow().getFont().getSize().setPoint(14);
		bb.getParameters().getCaptionBelow().setTextColor(java.awt.Color.RED);

		// Save the Barcode image to file
		bb.save(dataDir + "barcodeCaption.jpg");
		// ExEnd: BarcodeCaption
	}

}

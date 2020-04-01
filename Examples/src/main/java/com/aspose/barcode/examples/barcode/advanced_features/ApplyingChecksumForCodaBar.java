package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.CodabarChecksumMode;
import com.aspose.barcode.EnableChecksum;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.ChecksumValidation;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class ApplyingChecksumForCodaBar {
	public static void main(String[] args) throws IOException {

		// ExStart: ApplyingChecksumForCodaBar

		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ApplyingChecksumValidation.class) + "Barcode/AdvancedFeatures/";

		// Generation
		// Instantiate BarcodeGenerator object
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODABAR, "1234567890");

		// Set the EnableChecksum property to yes
		generator.getParameters().getBarcode().setChecksumEnabled(EnableChecksum.YES);

		// Set the CodabarChecksumMode
		generator.getParameters().getBarcode().getCodabar().setCodabarChecksumMode(CodabarChecksumMode.MOD_10);

		// Save the image on the system
		generator.save(dataDir + "Codabar_Mod10.png");

		// Recognition
		// Initialize reader object
		BarCodeReader reader = new BarCodeReader(dataDir + "Codabar_Mod10.png", DecodeType.CODABAR);

		// Set ChecksumValidation property of the reader to On
		reader.setChecksumValidation(ChecksumValidation.ON);

		for (BarCodeResult result : reader.readBarCodes()) {
			System.out.println("CodeText: " + result.getCodeText());
			System.out.println("Symbology type: " + result.getCodeType());

			// Get checksum value
			System.out.println("Checksum:" + result.getExtended().getOneD().getCheckSum());
		}
		// ExEnd: ApplyingChecksumForCodaBar
	}
}

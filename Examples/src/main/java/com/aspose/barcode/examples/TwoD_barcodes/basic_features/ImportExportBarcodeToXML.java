package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class ImportExportBarcodeToXML {

	public static void main(String[] args) throws IOException {
		String dataDir = Utils.getDataDir(ImportExportBarcodeToXML.class) + "TwoD_barcodes/BasicFeatures";

		ExportBarcodeToXML(dataDir);
		ImportBarcodeFromXML(dataDir);
	}

	public static void ExportBarcodeToXML(String dataDir) {
		// ExStart:ExportBarcodeToXML
		// Instantiate barcode object and set CodeText & Barcode Symbology
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.PDF_417,
				"this is some test code text. \n Second line \n third line.");

		// Set width and height
		generator.getParameters().getBarcode().getXDimension().setMillimeters(0.6f);
		generator.getParameters().getBarcode().getBarHeight().setMillimeters(1.2f);

		String xmlFile = dataDir + "barcode.xml";
		generator.exportToXml(xmlFile);
		// ExEnd:ExportBarcodeToXML
		System.out.println("Barcode exported to XML and saved at " + dataDir);
	}

	public static void ImportBarcodeFromXML(String dataDir) throws IOException {
		// ExStart:ImportBarcodeFromXML
		String xmlFile = dataDir + "barcode.xml";

		// Instantiate barcode object and set CodeText & Barcode Symbology
		BarcodeGenerator generator = BarcodeGenerator.importFromXml(xmlFile);
		generator.save(dataDir + "barcode_xml_out.jpg", BarCodeImageFormat.JPEG);
		// ExEnd:ImportBarcodeFromXML
		System.out.println("Imported barcode saved at " + dataDir);
	}
}

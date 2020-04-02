package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.awt.Color;
import java.io.IOException;

import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.FontStyle;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.CodeLocation;
import com.aspose.barcode.generation.TextAlignment;

public class ImportExportBarcodeToXML {

	public static void main(String[] args) throws IOException {
		String dataDir = Utils.getDataDir(ImportExportBarcodeToXML.class) + "2DBarcode/BasicFeatures/";

		ExportBarcodeToXML(dataDir);
		ImportBarcodeFromXML(dataDir);
		ExportPropertiesToXML(dataDir);
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

	public static void ExportPropertiesToXML(String dataDir) throws IOException {
		// ExStart:ExportPropertiesToXML
		// Initialize the BarcodeGenerator class by passing barcode text and barcode
		// symbology as parameters.
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "abcdefghijklmnopqrstuvwxyzabcdef");

		// Set various different properties/variables of the barcode.
		generator.getParameters().getBorder().setVisible(true);
		generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.ABOVE);

		// Specify caption Above settings.
		generator.getParameters().getCaptionAbove().setText("Caption ABOVE");
		generator.getParameters().getCaptionAbove().setAlignment(TextAlignment.CENTER);
		generator.getParameters().getCaptionAbove().setVisible(true);
		generator.getParameters().getCaptionAbove().setTextColor(Color.GREEN);

		// Specify caption Below settings.
		generator.getParameters().getCaptionBelow().setText("Caption BELOW");
		generator.getParameters().getCaptionBelow().setAlignment(TextAlignment.CENTER);
		generator.getParameters().getCaptionBelow().setVisible(true);
		generator.getParameters().getCaptionBelow().setTextColor(Color.YELLOW);

		// Specify text font settings.
		generator.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Courier New");
		generator.getParameters().getBarcode().getCodeTextParameters().getFont().getSize().setPoint(24);
		generator.getParameters().getBarcode().getCodeTextParameters().getFont().setStyle(FontStyle.BOLD);

		// Call the export to XML method to export the properties to XML file.
		generator.save(dataDir + "BarcodeGenerator.DataMatrix_out.xml");
		// ExEnd:ExportPropertiesToXML
	}
}

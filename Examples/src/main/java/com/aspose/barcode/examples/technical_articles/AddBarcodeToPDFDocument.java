package com.aspose.barcode.examples.technical_articles;

import com.aspose.pdf.*;
import com.aspose.pdf.facades.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.*;
import com.aspose.barcode.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.aspose.barcode.EncodeTypes;

public class AddBarcodeToPDFDocument {

	public static void main(String[] args) throws Exception {
		// The path to the documents directory.
		String dataDir = Utils.getDataDir(AddBarcodeToPDFDocument.class) + "TechnicalArticles/";
		// ExStart: AddBarcodeImageToPDFDocument
		// Instantiate linear barcode object, Set the Code text and symbology type for the barcode
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_39_STANDARD, "1234567");
		generator.save(dataDir + "barcodeToPDF.bmp", BarCodeImageFormat.BMP);

		InputStream in = new FileInputStream(dataDir + "barcodeToPDF.bmp");
		//ExStart: CreatePdfDocument
		// Create a PDF document and Add a section to the PDF document
		Document doc = new Document();
		doc.getPages().add();
		//ExEnd: CreatePdfDocument

		// Open document
		PdfFileMend mender = new PdfFileMend();

		// Create PdfFileMend object to add Barcode image
		mender.bindPdf(doc);

		// Add image in the PDF file
		mender.addImage(in, 1, 100, 600, 200, 700);

		// Save changes
		mender.save(dataDir + "AddImage_out.pdf");

		// Close PdfFileMend object
		mender.close();
		// ExEnd: AddBarcodeImageToPDFDocument
	}
}

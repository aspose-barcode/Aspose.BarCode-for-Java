package com.aspose.barcode.examples.technical_articles;

//ExStart: RecognitionFromPDF
import com.aspose.barcode.*;
import com.aspose.barcode.License;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.BaseDecodeType;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode_recognition.advanced_features.BarcodeOrientation;
import com.aspose.barcode.generation.BarcodeGenerator;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.aspose.pdf.*;
import com.aspose.pdf.facades.PdfExtractor;

public class RecognitionFromPDF {
	public static void main(String[] args) {
		try {
			// Set license for Barcode
			ApplyALicense.applyALicense();

			// Set license for PDF
			com.aspose.pdf.License licBarCode = new com.aspose.pdf.License();
			licBarCode.setLicense("Aspose.Pdf.Java.lic");

			String dataDir = Utils.getDataDir(RecognitionFromPDF.class) + "TechnicalArticles/";
			String strPdfDoc = dataDir + "output1.pdf";
			String strBarCodeImage = "";

			// Generate barcode and add to PDF file
			BarcodeGenerator builder = new BarcodeGenerator(EncodeTypes.CODE_39_STANDARD);
			builder.setCodeText("test123");
			String strBarCodeImageSave = dataDir + "input_image1.jpg";
			builder.save(strBarCodeImageSave);

			Document pdf1 = new Document();
			// Get the page you want to add the image to
			Page page = pdf1.getPages().add();

			// Load image
			BufferedImage originalImage = ImageIO.read(new File(strBarCodeImageSave));

			// Add an image to the Images collection of the page resources
			page.getResources().getImages().add(originalImage);

			// Save the Pdf
			pdf1.save(strPdfDoc);

			// Instantiate PdfExtractor object
			PdfExtractor extractor = new PdfExtractor();

			// Bind the input PDF document to extractor
			extractor.bindPdf(strPdfDoc);

			// Extract images from the input PDF document
			extractor.extractImage();
			String suffix = ".jpg";
			int imageCount = 1;
			while (extractor.hasNextImage()) {
				System.out.println("Extracting image " + imageCount);
				strBarCodeImage = "tmpbarcode" + imageCount + suffix;
				extractor.getNextImage(strBarCodeImage);

				// Recognize barcode from image
				BarCodeReader reader = new BarCodeReader(strBarCodeImage, DecodeType.CODE_39_EXTENDED);
				
				for (BarCodeResult result : reader.readBarCodes()) {
					System.out.println("CodeText: " + result.getCodeText());
					System.out.println("Symbology type: " + result.getCodeType());
				}
				
				imageCount++;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
//ExEnd: RecognitionFromPDF
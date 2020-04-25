package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.ECIEncodings;
import com.aspose.barcode.QREncodeMode;
import com.aspose.barcode.QREncodeType;
import com.aspose.barcode.generation.CodeLocation;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class CreatingAQRBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(CreatingAQRBarcode.class) + "2DBarcode/BasicFeatures/";

		createAQRBarcode(dataDir);
		errorCorrection(dataDir);
		rotation(dataDir);
		QRBarcodeWithImage(dataDir);
		set_QR_version(dataDir);
		EncodeQRCodEInECIMode(dataDir);
		EncodeQRCode(dataDir);
		EncodeMicroQRCode(dataDir);
	}

	public static void createAQRBarcode(String dataDir) throws IOException {
		// ExStart: createAQRBarcode
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.QR, "1234567890");

		generator.save(dataDir + "QRBarcode.bmp", BarCodeImageFormat.BMP);
		// ExEnd: createAQRBarcode
		System.out.println("File saved at:" + dataDir + "QRBarcode.bmp");
	}

	public static void errorCorrection(String dataDir) throws IOException {
		// ExStart: errorCorrection
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.QR, "1234567890");

		generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_H);

		generator.save(dataDir + "errorCorrectionQRBarcode.bmp", BarCodeImageFormat.BMP);
		// ExEnd: errorCorrection
		System.out.println("File saved at:" + dataDir + "errorCorrectionQRBarcode.bmp");
	}

	public static void rotation(String dataDir) throws IOException {
		// ExStart: rotation
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.QR, "1234567890");

		// Hide code text
		generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);
		generator.getParameters().setRotationAngle(90);

		generator.save(dataDir + "rotation_qr.bmp", BarCodeImageFormat.BMP);
		// ExEnd: rotation
		System.out.println("File saved at:" + dataDir + "rotation_qr.bmp");
	}

	public static void QRBarcodeWithImage(String dataDir) throws IOException {
		// ExStart: QRBarcodeWithImage
		// Define barcode image height & width
		int QRCODE_IMAGE_HEIGHT = 300;
		int QRCODE_IMAGE_WIDTH = 300;

		// Create an instance of BarcodeGenerator class
		// Set the barcode text
		// Set the barcode symbology
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.QR, "1234567890");

		// Set the error level
		generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_H);

		// Generate the barocde image and save it as image in an object of BufferedImage
		// class
		java.awt.image.BufferedImage image = generator.generateBarCodeImage();
		System.out.println("ImageHeight: " + image.getHeight());

		// Load the image in an object of BufferedImage class - this is the image that
		// you want to embed into the barcode image.
		java.awt.image.BufferedImage overlay = javax.imageio.ImageIO.read(new java.io.File(dataDir + "logo.png"));

		// Calculate the height & width
		int deltaHeight = image.getHeight() - overlay.getHeight();
		int deltaWidth = image.getWidth() - overlay.getWidth();

		// Create a new empty image
		java.awt.image.BufferedImage combined = new java.awt.image.BufferedImage(QRCODE_IMAGE_WIDTH,
				QRCODE_IMAGE_HEIGHT, java.awt.image.BufferedImage.TYPE_INT_ARGB);

		// Get the Graphics2D object
		java.awt.Graphics2D g = (java.awt.Graphics2D) combined.getGraphics();

		// Draw the primary image (barcode image) on the canvas
		g.drawImage(image, 0, 0, null);
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1f));

		// Draw the second image (logo image) on the canvas inside the barcode image
		g.drawImage(overlay, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

		// Create and save the final very of the image with barcode and logo inside it
		java.io.File imageFile = new java.io.File(dataDir + "qrcode_with_logo.png");
		javax.imageio.ImageIO.write(combined, "PNG", imageFile);
		// ExEnd: QRBarcodeWithImage
		System.out.println("File saved at:" + dataDir + "qrcode_with_logo.png");
	}

	public static void set_QR_version(String dataDir) throws IOException {
		// ExStart: set_QR_version
		// Instantiate BarcodeGenerator object
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.QR,
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ");

		// Set the error level
		generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_Q);

		// Set the QR barcode version number
		generator.getParameters().getBarcode().getQR().setQrVersion(com.aspose.barcode.QRVersion.VERSION_10);

		// Save the image
		generator.save(dataDir + "qr_version10_errorQ.png");
		// ExEnd: set_QR_version
		System.out.println("File saved at:" + dataDir + "qr_version10_errorQ.png");
	}

	public static void EncodeQRCodEInECIMode(String dataDir) throws IOException {
		// ExStart: EncodeQRCodEInECIMode
		// initialize a BarcodeGenerator class object
		// Set its CodeText & Symbology Type
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.QR, "1234567890");

		// Set encoding mode, ForceQR (default) for standard QR, ECI encoding type,
		// error correction level
		generator.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.ECI_ENCODING);
		generator.getParameters().getBarcode().getQR().setQrEncodeType(QREncodeType.FORCE_QR);
		generator.getParameters().getBarcode().getQR().setQrECIEncoding(ECIEncodings.UTF8);
		generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_L);

		// Get barcode image Bitmap & Save QR code
		java.awt.image.BufferedImage combined = generator.generateBarCodeImage();

		java.io.File imageFile = new java.io.File(dataDir + "EncodeQRCodEInECIMode_out.png");
		javax.imageio.ImageIO.write(combined, "PNG", imageFile);
		// ExEnd: EncodeQRCodEInECIMode
		System.out.println("File saved at:" + dataDir + "EncodeQRCodEInECIMode_out.png");
	}
	public static void EncodeQRCode(String dataDir) throws IOException {
		// ExStart: EncodeQRCode
		// initialize a BarcodeGenerator class object
		// Set its CodeText & Symbology Type
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.QR, "12345TEXT");

		// Set ForceQR (default) for standard QR and Code text
		generator.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.AUTO);
		generator.getParameters().getBarcode().getQR().setQrEncodeType(QREncodeType.FORCE_QR);
		generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_L);

		// Get barcode image Bitmap & Save QR code
		java.awt.image.BufferedImage combined = generator.generateBarCodeImage();

		java.io.File imageFile = new java.io.File(dataDir + "EncodeQR_out.png");
		javax.imageio.ImageIO.write(combined, "PNG", imageFile);
		// ExEnd: EncodeQRCode
		System.out.println("File saved at:" + dataDir + "EncodeQR_out.png");
	}
	
	public static void EncodeMicroQRCode(String dataDir) throws IOException {
		// ExStart: EncodeMicroQRCode
		// initialize a BarcodeGenerator class object
		// Set its CodeText & Symbology Type
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.QR, "12345TEXT");

		// Set encoding mode, Auto for Micro QR, error correction level     
		generator.getParameters().getBarcode().getQR().setQrEncodeMode(QREncodeMode.AUTO);
		generator.getParameters().getBarcode().getQR().setQrEncodeType(QREncodeType.AUTO);
		generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_L);

		// Get barcode image Bitmap & Save QR code
		java.awt.image.BufferedImage combined = generator.generateBarCodeImage();

		java.io.File imageFile = new java.io.File(dataDir + "EncodeMicroQR_out.png");
		javax.imageio.ImageIO.write(combined, "PNG", imageFile);
		// ExEnd: EncodeMicroQRCode
		System.out.println("File saved at:" + dataDir + "EncodeMicroQR_out.png");
	}

}

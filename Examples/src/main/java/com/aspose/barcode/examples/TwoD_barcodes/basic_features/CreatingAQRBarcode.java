package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class CreatingAQRBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(CreatingAQRBarcode.class) + "2DBarcode/BasicFeatures/";
				
		createAQRBarcode(dataDir);
		errorCorrection(dataDir);
		rotation(dataDir);
		QRBarcodeWithImage(dataDir);
	}

	public static void createAQRBarcode(String dataDir) throws IOException {
		
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.QR);
		b.setCodeText("1234567890");

		b.save(dataDir + "QRBarcode.bmp", BarCodeImageFormat.Bmp);
	}
	
	public static void errorCorrection(String dataDir) throws IOException {
				
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.QR);
		b.setQRErrorLevel(QRErrorLevel.LevelH);
		b.setCodeText("1234567890");
		
		b.save(dataDir + "errorCorrectionQRBarcode.bmp", BarCodeImageFormat.Bmp);
	}
	
	public static void rotation(String dataDir) throws IOException {
				
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.QR);
		b.setCodeText("1234567890");

		//Hide code text
		b.setCodeLocation(CodeLocation.None);
		b.setRotationAngleF(90);
		
		b.save(dataDir + "rotation_qr.bmp", BarCodeImageFormat.Bmp);
	}

	public static void QRBarcodeWithImage(String dataDir) throws IOException {
				
	    // Define barcode image height & width
            int QRCODE_IMAGE_HEIGHT = 300;
            int QRCODE_IMAGE_WIDTH = 300;

            // Create an instance of BarCodeBuilder class
            // Set the barcode text
            // Set the barcode symbology 
            BarCodeBuilder builder = new BarCodeBuilder("123456789", com.aspose.barcode.EncodeTypes.QR);

            // Set the error level
            builder.setQRErrorLevel(com.aspose.barcode.QRErrorLevel.LevelH);

            // Set the Graphics Unit
            builder.setGraphicsUnit(2);

            // Generate the barocde image and save it as image in an object of BufferedImage class
            java.awt.image.BufferedImage image = builder.getCustomSizeBarCodeImage(QRCODE_IMAGE_WIDTH, QRCODE_IMAGE_HEIGHT, false);
            System.out.println("ImageHeight: " + image.getHeight());

            // Load the image in an object of BufferedImage class - this is the image that you want to embed into the barcode image.
            java.awt.image.BufferedImage overlay = javax.imageio.ImageIO.read(new java.io.File("wifi_logo.jpg"));

            // Calculate the height & width
            int deltaHeight = image.getHeight() - overlay.getHeight();
            int deltaWidth  = image.getWidth()  - overlay.getWidth();

            // Create a new empty image
            java.awt.image.BufferedImage combined = new java.awt.image.BufferedImage(QRCODE_IMAGE_WIDTH, QRCODE_IMAGE_HEIGHT, 
                    java.awt.image.BufferedImage.TYPE_INT_ARGB);

            // Get the Graphics2D object
            java.awt.Graphics2D g = (java.awt.Graphics2D)combined.getGraphics();

            // Draw the primary image (barcode image) on the canvas
            g.drawImage(image, 0, 0, null);
            g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1f));

            // Draw the second image (logo image) on the canvas inside the barcode image
            g.drawImage(overlay, (int)Math.round(deltaWidth/2), (int)Math.round(deltaHeight/2), null);

            // Create and save the final very of the image with barcode and logo inside it
            java.io.File imageFile = new java.io.File("qrcode_with_logo.png");
            javax.imageio.ImageIO.write(combined, "PNG", imageFile);
	}
}

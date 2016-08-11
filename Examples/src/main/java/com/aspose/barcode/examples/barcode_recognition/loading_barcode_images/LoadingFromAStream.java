package com.aspose.barcode.examples.barcode_recognition.loading_barcode_images;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.aspose.barcode.barcoderecognition.BarCodeReadType;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.Utils;

public class LoadingFromAStream {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(LoadingFromAStream.class) + "BarcodeReader/loading_images/";
				
		// Open the stream. Read only access is enough for Aspose.BarCode to load an image.
		InputStream stream = new FileInputStream(dataDir + "CodeText.jpg");

		// Create an instance of BarCodeReader class
		// and pass InputStream object as a parameter
		BarCodeReader reader = new BarCodeReader(stream);

		//OR

		// Open the stream. Read only access is enough for Aspose.BarCode to load an image.
		InputStream stream1 = new FileInputStream(dataDir + "CodeText.jpg");

		// Create an instance of BarCodeReader class
		// and pass InputStream object and bar code symbology type as parameters
		BarCodeReader reader1 = new BarCodeReader(stream, BarCodeReadType.Code39Extended);

		//OR

		// Open the stream. Read only access is enough for Aspose.BarCode to load an image.
		InputStream stream2 = new FileInputStream(dataDir +"CodeText.jpg");

		// Create an instance of BarCodeReader class
		// and pass InputStream object, Rectangle object and bar code symbology type as parameters
		BarCodeReader reader2 = new BarCodeReader(stream, new Rectangle(0, 0, 100, 50), BarCodeReadType.Code39Extended);

		//OR

		// Load the image. Read only access is enough for Aspose.BarCode to load an image.
		BufferedImage img3 = ImageIO.read(new File(dataDir + "CodeText.jpg"));

		// Create an instance of BarCodeReader class
		// and pass BufferedImage object as parameter
		BarCodeReader reader3 = new BarCodeReader(img3);

		//OR

		// Load the image. Read only access is enough for Aspose.BarCode to load an image.
		BufferedImage img4 = ImageIO.read(new File(dataDir + "CodeText.jpg"));
		// Create an instance of BarCodeReader class
		// and pass BufferedImage object and bar code symbology type as parameters
		BarCodeReader reader4 = new BarCodeReader(img4, BarCodeReadType.Pdf417);

		//OR

		// Load the image. Read only access is enough for Aspose.BarCode to load an image.
		BufferedImage img5 = ImageIO.read(new File(dataDir + "CodeText.jpg"));

		// Create an instance of BarCodeReader class
		// and pass BufferedImage object, Rectangle object and bar code symbology type as parameters
		BarCodeReader reader5 = new BarCodeReader(img5, new Rectangle(0, 0, 100, 50), BarCodeReadType.Code39Extended);
	}

}

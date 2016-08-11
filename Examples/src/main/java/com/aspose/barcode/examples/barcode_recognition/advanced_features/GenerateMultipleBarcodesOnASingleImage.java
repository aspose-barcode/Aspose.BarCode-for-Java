package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class GenerateMultipleBarcodesOnASingleImage {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// The path to the resource directory.
		String dataDir = Utils.getDataDir(GenerateMultipleBarcodesOnASingleImage.class) + "BarcodeReader/advanced_features/";
		
		HashMap collection = new HashMap();
		collection.put("ONE123", Symbology.Code39Standard);
		collection.put("Process Collection", Symbology.DataMatrix);
		collection.put("Dictionary Collection", Symbology.QR);
		collection.put("X06712AT", Symbology.Code128);
		collection.put("979026000043", Symbology.EAN13);
		collection.put("Aztec BarCode", Symbology.Aztec);

		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		for (Object key : collection.keySet()) {
		    BarCodeBuilder bb = new BarCodeBuilder();
		    bb.setCodeText((String) key);
		    bb.setSymbologyType((Long) collection.get(key));
		    images.add(bb.generateBarCodeImage());
		}

		int maxWidth = 0;
		int sumHeight = 0;
		for (BufferedImage bmp : images)
		{
		    sumHeight += bmp.getHeight();
		    if (maxWidth < bmp.getWidth())
		        maxWidth = bmp.getWidth();
		}
		int offset = 10;
		BufferedImage resultBitmap = new BufferedImage(maxWidth + offset * 2, sumHeight + offset * images.size(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = resultBitmap.getGraphics();
		g.setColor(Color.white);
		//g.drawRect(0, 0, width, height);
		g.fillRect(0, 0, resultBitmap.getWidth(), resultBitmap.getHeight());

		int yPosition = offset;
		for (int i = 0; i < images.size(); ++i)
		{
		    BufferedImage currentBitmap = images.get(i);
		    g.drawImage(currentBitmap, offset, yPosition, null);
		    yPosition += currentBitmap.getHeight() + offset;
		}

		File outputfile = new File(dataDir + "output.png");
		ImageIO.write(resultBitmap, "png", outputfile);
	}

}

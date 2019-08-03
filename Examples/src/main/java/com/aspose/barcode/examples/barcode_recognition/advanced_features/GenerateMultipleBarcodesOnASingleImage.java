package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.aspose.barcode.BaseEncodeType;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class GenerateMultipleBarcodesOnASingleImage {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		//ExStart: GenerateMultipleBarcodesOnASingleImage
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(GenerateMultipleBarcodesOnASingleImage.class) + "BarcodeReader/advanced_features/";
		
		HashMap collection = new HashMap();
		collection.put("ONE123", com.aspose.barcode.EncodeTypes.CODE_39_STANDARD);
		collection.put("Process Collection", com.aspose.barcode.EncodeTypes.DATA_MATRIX);
		collection.put("Dictionary Collection", com.aspose.barcode.EncodeTypes.QR);
		collection.put("X06712AT", com.aspose.barcode.EncodeTypes.CODE_128);
		collection.put("979026000043", com.aspose.barcode.EncodeTypes.EAN_13);
		collection.put("Aztec BarCode", com.aspose.barcode.EncodeTypes.AZTEC);

		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		for (Object key : collection.keySet()) {
		    BarcodeGenerator bb = new BarcodeGenerator((BaseEncodeType) collection.get(key));
		    bb.setCodeText((String) key);
		    //bb.setSymbologyType((Long) collection.get(key));
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
		//ExEnd: GenerateMultipleBarcodesOnASingleImage
	}

}

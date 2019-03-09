package com.aspose.barcode.examples.TwoD_barcodes.basic_features;
//ExStart: GenerateBarCodeDemoApplet
import java.applet.Applet;
import java.awt.*;
import java.io.IOException;

import com.aspose.barcode.generation.BarCodeGenerator;
 
public class Test extends Applet {
	public void paint(Graphics g)
	{
		// Instantiate a BarCodeBuilder
		BarCodeGenerator b = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417);
		
		// Small module's width to be 1 millimeter
		b.getXDimension().setMillimeters(1);
 
		// Text to be encoded
		b.setCodeText("This is a test.");
		
		// Save the barcode to disk
		//ExStart:SaveToDisk
		try
		{
		    //Save as GIF file
		    b.save(getCodeBase().getPath() + "myPdf417.gif");
		    System.out.println("Saved barcode image to " + getCodeBase().getPath() + "barcode.gif");
		    
		    //Save as JPG file
		    b.save(getCodeBase().getPath() + "myPdf417.jpg");
		    System.out.println("Saved barcode image to " + getCodeBase().getPath() + "barcode.jpg");
		 
		    //Save as BMP file
		    b.save(getCodeBase().getPath() + "myPdf417.bmp");
		    System.out.println("Saved barcode image to " + getCodeBase().getPath() + "barcode.bmp");
		    
		    //Save as PNG file
		    b.save(getCodeBase().getPath() + "myPdf417.png");
		    System.out.println("Saved barcode image to " + getCodeBase().getPath() + "barcode.png");
		}
		catch (Exception ex) {
			
		}
		//ExEnd:SaveToDisk
		
		// Load and Draw the image on Applet
		Image img = getImage(getCodeBase(), "barcode.png");
		g.drawImage(img, 0, 0, this);
	}
}
//ExEnd: GenerateBarCodeDemoApplet
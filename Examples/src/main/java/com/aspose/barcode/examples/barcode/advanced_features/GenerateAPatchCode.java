package com.aspose.barcode.examples.barcode.advanced_features;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.MarginsF;
import com.aspose.barcode.examples.Utils;

public class GenerateAPatchCode {

	public static void main(String[] args) throws IOException {
		generatePatchCode();
		generateWholePage();
	}
	
	public static void generatePatchCode() {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(GenerateAPatchCode.class) + "Barcode/AdvancedFeatures/";
    	
		BarCodeBuilder builder = new BarCodeBuilder();
		// set Symbology type
                builder.setEncodeType(com.aspose.barcode.EncodeTypes.PATCH_CODE);
		builder.setCodeText("Patch T");
		
		builder.save(dataDir + "patch.bmp");
	}
	
	public static void generateWholePage() throws IOException {
		BarCodeBuilder builder = new BarCodeBuilder("Patch T", com.aspose.barcode.EncodeTypes.PATCH_CODE);
		builder.setImageWidth(150); //in millimeters
		builder.setMargins(new MarginsF(0.5f, 0.5f, 0.5f, 0.5f)); //make the same small margins
		builder.setCodeLocation(CodeLocation.None); //to hide codetext

		BufferedImage topImg = builder.generateBarCodeImage();
		builder.rotate(90);
		BufferedImage leftImg = builder.generateBarCodeImage();
		builder.rotate(90);
		BufferedImage bottomImg = builder.generateBarCodeImage();
		builder.rotate(90);
		BufferedImage rigthtImg = builder.generateBarCodeImage();
				
		BufferedImage frameImg = new BufferedImage(topImg.getWidth(), rigthtImg.getHeight() + 2 * topImg.getHeight(), rigthtImg.getType());

		// Initialize graphics object from the image
		Graphics graphics = frameImg.getGraphics();
		        
		graphics.drawImage(topImg, 0, 0, null);
		graphics.drawImage(leftImg, 0, topImg.getHeight(), null);
		graphics.drawImage(bottomImg, 0, topImg.getHeight() + leftImg.getHeight(), null);
		graphics.drawImage(rigthtImg, topImg.getWidth() - rigthtImg.getWidth(), topImg.getHeight(), null);
		
		// save Patch code frame
		File outputfile = new File(Utils.getDataDir(GenerateAPatchCode.class) + "Barcode/AdvancedFeatures/" +  "patch-code-frame.png");
		ImageIO.write(frameImg, "png", outputfile);
	}
	
}

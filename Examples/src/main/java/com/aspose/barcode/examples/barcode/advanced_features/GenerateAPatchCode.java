package com.aspose.barcode.examples.barcode.advanced_features;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.MarginsF;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class GenerateAPatchCode {

	public static void main(String[] args) throws IOException {
		generatePatchCode();
		generateWholePage();
	}
	
	public static void generatePatchCode() throws IOException {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(GenerateAPatchCode.class) + "Barcode/AdvancedFeatures/";
    	
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.PATCH_CODE, "Patch T");
		
		generator.save(dataDir + "patch.bmp");
	}
	
	public static void generateWholePage() throws IOException {
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.PATCH_CODE, "Patch T");
		generator.getBarCodeWidth().setMillimeters(150); //in millimeters
		generator.getMargins().getTop().setPixels(0.5f);//make the same small margins
		generator.getMargins().getRight().setPixels(0.5f);
		generator.getMargins().getLeft().setPixels(0.5f);
		generator.getMargins().getBottom().setPixels(0.5f);
		
		generator.getCodeTextStyle().setLocation(CodeLocation.NONE); //to hide codetext

		BufferedImage topImg = generator.generateBarCodeImage();
		generator.setRotationAngle(90);
		BufferedImage leftImg = generator.generateBarCodeImage();
		generator.setRotationAngle(90);
		BufferedImage bottomImg = generator.generateBarCodeImage();
		generator.setRotationAngle(90);
		BufferedImage rigthtImg = generator.generateBarCodeImage();
				
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

package com.aspose.barcode.examples.barcode.advanced_features;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aspose.barcode.generation.PatchFormat;
import com.aspose.barcode.generation.CodeLocation;
import com.aspose.barcode.MarginsF;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class GenerateAPatchCode {

	public static void main(String[] args) throws IOException {
		generatePatchCode();
		generateWholePage();
		setPatchFormat();
	}

	public static void generatePatchCode() throws IOException {
		// ExStart: generatePatchCode
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(GenerateAPatchCode.class) + "Barcode/AdvancedFeatures/";

		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.PATCH_CODE, "Patch T");
		
		generator.save(dataDir + "patch.bmp");
		// ExEnd: generatePatchCode
	}
	
	public static void setPatchFormat() throws IOException {
		// ExStart: setPatchFormat
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(GenerateAPatchCode.class) + "Barcode/AdvancedFeatures/";

		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.PATCH_CODE, "Patch T");
		generator.getParameters().getBarcode().getPatchCode().setPatchFormat(PatchFormat.US_LETTER);
		
		generator.save(dataDir + "patch.bmp");
		// ExEnd: setPatchFormat
	}

	public static void generateWholePage() throws IOException {
		// ExStart: generateWholePage
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.PATCH_CODE, "Patch T");
		generator.getParameters().getImageWidth().setMillimeters(150); // in millimeters
		generator.getParameters().getBarcode().getPadding().getTop().setPixels(0.5f);// make the same small margins
		generator.getParameters().getBarcode().getPadding().getRight().setPixels(0.5f);
		generator.getParameters().getBarcode().getPadding().getLeft().setPixels(0.5f);
		generator.getParameters().getBarcode().getPadding().getBottom().setPixels(0.5f);

		// to hide codetext
		generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE); 

		BufferedImage topImg = generator.generateBarCodeImage();
		generator.getParameters().setRotationAngle(90);
		BufferedImage leftImg = generator.generateBarCodeImage();
		generator.getParameters().setRotationAngle(90);
		BufferedImage bottomImg = generator.generateBarCodeImage();
		generator.getParameters().setRotationAngle(90);
		BufferedImage rigthtImg = generator.generateBarCodeImage();

		BufferedImage frameImg = new BufferedImage(topImg.getWidth(), rigthtImg.getHeight() + 2 * topImg.getHeight(),
				rigthtImg.getType());

		// Initialize graphics object from the image
		Graphics graphics = frameImg.getGraphics();

		graphics.drawImage(topImg, 0, 0, null);
		graphics.drawImage(leftImg, 0, topImg.getHeight(), null);
		graphics.drawImage(bottomImg, 0, topImg.getHeight() + leftImg.getHeight(), null);
		graphics.drawImage(rigthtImg, topImg.getWidth() - rigthtImg.getWidth(), topImg.getHeight(), null);

		// save Patch code frame
		File outputfile = new File(
				Utils.getDataDir(GenerateAPatchCode.class) + "Barcode/AdvancedFeatures/" + "patch-code-frame.png");
		ImageIO.write(frameImg, "png", outputfile);
		// ExEnd: generateWholePage
	}

}

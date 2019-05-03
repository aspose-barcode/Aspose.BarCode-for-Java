package com.aspose.barcode.examples.V3.barcode_generator;

import java.awt.Color;
import java.io.IOException;

import com.aspose.barcode.BorderDashStyle;
import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode_image.basic_features.BarcodeImageQuality;
import com.aspose.barcode.generation.v3.AutoSizeMode;
import com.aspose.barcode.generation.v3.BarcodeGenerator;
import com.aspose.barcode.generation.v3.StringAlignment;

public class GenerateUsingV3BarcodeGenerator {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(GenerateUsingV3BarcodeGenerator.class) + "BarcodeImage/V3BarcodeGenerator/";
		GenerateBarcode(dataDir);
		GenerateBarcodeWithCodeText(dataDir);
		GettingDefaultTextForGeneratedBarcode(dataDir);
		SettingBarcodeHeight(dataDir);
		SettingBarcodeProperties(dataDir);
		SettingBarcodeBorder(dataDir);
		GenerateBarcodeWithAutoSizeInterpolation(dataDir);
		GenerateBarcodeWithCaptionAbove(dataDir);
		GenerateBarcodeWithCaptionBelow(dataDir);
	}

	public static void GenerateBarcode(String dataDir) throws IOException
    {
        //ExStart: GenerateBarcode
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_128);
		generator.setCodeText("123ABC");
		generator.save("code128.png");
        //ExEnd: GenerateBarcode
    }
	
	public static void GenerateBarcodeWithCodeText(String dataDir) throws IOException
    {
        //ExStart: GenerateBarcodeWithCodeText
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14, "1234567890");
        generator.save(dataDir + "GenerateBarcodeWithCodeText_out.png");
        //ExEnd: GenerateBarcodeWithCodeText
    }
	
	public static void GettingDefaultTextForGeneratedBarcode(String dataDir) throws IOException
    {
        //ExStart: GettingDefaultTextForGeneratedBarcode
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14);
        String codetext = generator.getCodeText();
        System.out.println(codetext);
        //ExEnd: GettingDefaultTextForGeneratedBarcode
    }
	
	public static void SettingBarcodeHeight(String dataDir) throws IOException
    {
        //ExStart: SettingBarcodeHeight
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.ITF_14);
        generator.setCodeText("123456789");
        generator.getParameters().getBarcode().getBarHeight().setPixels(500);
        generator.save(dataDir + "SettingBarcodeHeight_out.png");
        //ExEnd: SettingBarcodeHeight
    }
	
	public static void SettingBarcodeProperties(String dataDir) throws IOException
    {
        //ExStart: SettingBarcodeProperties
		BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14);
        gen.setCodeText("123456789");
        gen.getParameters().getBarcode().setForeColor(Color.BLUE);
        gen.getParameters().getBarcode().getXDimension().setPixels(5);
        gen.getParameters().getBarcode().getBarHeight().setPixels(200);
        gen.getParameters().getBarcode().getPadding().getTop().setPixels(70);
        gen.getParameters().getBarcode().getPadding().getBottom().setPixels(70);
        gen.getParameters().getBarcode().getPadding().getLeft().setPixels(70);
        gen.getParameters().getBarcode().getPadding().getRight().setPixels(70);
        gen.save(dataDir + "SettingBarcodeProperties_out.png");
        //ExEnd: SettingBarcodeProperties
    }
	
	public static void SettingBarcodeBorder(String dataDir) throws IOException
    {
        //ExStart: SettingBarcodeBorder
		BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14);
        gen.setCodeText("123456789");
        gen.getParameters().getBorder().getWidth().setPixels(50);
        gen.getParameters().getBorder().setColor(Color.RED);
        gen.getParameters().getBorder().setVisible(true);
        gen.getParameters().getBorder().setDashStyle(BorderDashStyle.SOLID);
        gen.save(dataDir + "SettingBarcodeBorder_out.png");
        //ExEnd: SettingBarcodeBorder
    }

    public static void GenerateBarcodeWithAutoSizeInterpolation(String dataDir) throws IOException
    {
        //ExStart: GenerateBarcodeWithAutoSizeInterpolation
    	BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14);
        gen.setCodeText("123456789");
        gen.getParameters().getBarcode().setAutoSizeMode(AutoSizeMode.INTERPOLATION);
        gen.getParameters().getBarcode().getBarCodeWidth().setPixels(500);
        gen.getParameters().getBarcode().getBarCodeHeight().setPixels(200);
        gen.save(dataDir + "GenerateBarcodeWithAutoSizeInterpolation_out.png");
        //ExEnd: GenerateBarcodeWithAutoSizeInterpolation
    }

    public static void GenerateBarcodeWithCaptionAbove(String dataDir) throws IOException
    {
        //ExStart: GenerateBarcodeWithCaptionAbove
    	BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14);
        gen.setCodeText("123456789");
        gen.getParameters().getCaptionAbove().getPadding().getTop().setPixels(70);
        gen.getParameters().getCaptionAbove().getPadding().getBottom().setPixels(70);
        gen.getParameters().getCaptionAbove().getPadding().getLeft().setPixels(50);
        gen.getParameters().getCaptionAbove().getPadding().getRight().setPixels(50);
        gen.getParameters().getCaptionAbove().setText("Caption Above");
        gen.getParameters().getCaptionAbove().setTextColor(Color.RED);
        gen.getParameters().getCaptionAbove().getFont().getSize().setPixels(28);
        gen.getParameters().getCaptionAbove().setVisible(true);
        gen.getParameters().getCaptionAbove().setAlignment(StringAlignment.CENTER);
        gen.save(dataDir + "GenerateBarcodeWithCaptionAbove_out.png");
        //ExEnd: GenerateBarcodeWithCaptionAbove
    }

    public static void GenerateBarcodeWithCaptionBelow(String dataDir) throws IOException
    {
        //ExStart: GenerateBarcodeWithCaptionBelow
    	BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.ITF_14);
        gen.setCodeText("123456789");
        gen.getParameters().getCaptionBelow().getPadding().getTop().setPixels(70);
        gen.getParameters().getCaptionBelow().getPadding().getBottom().setPixels(70);
        gen.getParameters().getCaptionBelow().getPadding().getLeft().setPixels(50);
        gen.getParameters().getCaptionBelow().getPadding().getRight().setPixels(50);
        gen.getParameters().getCaptionBelow().setText("Caption Below");
        gen.getParameters().getCaptionBelow().setTextColor(Color.RED);
        gen.getParameters().getCaptionBelow().getFont().getSize().setPixels(28);
        gen.getParameters().getCaptionBelow().setVisible(true);
        gen.getParameters().getCaptionBelow().setAlignment(StringAlignment.CENTER);
        gen.save(dataDir + "GenerateBarcodeWithCaptionBelow_out.png");
        //ExEnd: GenerateBarcodeWithCaptionBelow
    }
    
}

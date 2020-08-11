---
title: Aspose.BarCode for Java 16.10 Release Notes
type: docs
weight: 30
url: /java/aspose-barcode-for-java-16-10-release-notes/
---

Aspose.BarCode for Java has been updated to version 16.10 and we are pleased to announce it. The following is a list of changes in this version of Aspose.BarCode.
### **Features and Improvements**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-36287|[Support for GS1-128 AI 8102 Coupon Extended Barcode](http://www.aspose.com/community/forums/thread/761935/question-about-gs1-128-coupon-extended-barcode.aspx)|New Feature|
|BARCODENET-36324|Support for UPCA & GS1 Databar coupon|New Feature|
|BARCODENET-36332|[Set Wide Narrow Ratio for barcode symbology : Code128](http://www.aspose.com/community/forums/thread/667946/pixel-shaving-for-linear-barcodes.aspx)|New Feature|
|BARCODENET-33941|[Can't recognize PDF417 code using the Motorola DS4208 scanner](http://www.aspose.com/community/forums/thread/556415/generated-pdf417-not-recognized.aspx)|Enhancement|
|BARCODENET-36336|[Barcode generated using Aspose.BarCode after printing is unable to recognized by laser scanner](http://www.aspose.com/community/forums/thread/710416/sometimes-unreadable-code93standard-barcode.aspx)|Enhancement|
|BARCODENET-36321|[Exclude several unwanted EAN8, MSI, Supplement barcodes](http://www.aspose.com/community/forums/thread/597692.aspx)|Enhancement|
|BARCODENET-36335|[Generating QR barcode with custom height and width (GraphicsUnit: Inches, Pixel, Millimeter) is not working properly](http://www.aspose.com/community/forums/thread/702984/qr-code-setting-the-size-has-no-effect.aspx)|Enhancement|
|BARCODENET-36247|[ISSN barcode throws exception for a valid text](http://www.aspose.com/community/forums/thread/727851/issn-value.aspx)|Enhancement|
|BARCODENET-36277|Improve the irregular grid to recognize more datamtrix|Enhancement|
|BARCODENET-36338|[Unable to Read the EAN13 barcode from an image](http://www.aspose.com/community/forums/thread/730495/detect-ean13-with-missing-end-marker.aspx)|Enhancement|
# **Usage examples:**
**BARCODENET-36287 Support for GS1-128 AI 8102 Coupon Extended Barcode** 
Code sample

{{< highlight xml >}}

 BarCodeBuilder barCodeBuilder = new BarCodeBuilder();

barCodeBuilder.setSymbologyType(Symbology.UpcaGs1Code128Coupon);

// upca part is "514141100906", GS1Code128 part is "(8102)03"

barCodeBuilder.setCodeText("514141100906(8102)03");

barCodeBuilder.save("UpcaGs1Code128Coupon.png");

{{< /highlight >}}

**BARCODENET-36324 Support for UPCA & GS1 Databar coupon** 
Code sample

{{< highlight xml >}}

 BarCodeBuilder barCodeBuilder = new BarCodeBuilder();

// upca part is "514141100906", GS1Databar part is "(8110)001234502239811110555"

barCodeBuilder.setCodeText("512345678900(8110)001234502239811110555");

barCodeBuilder.setSymbologyType(Symbology.UpcaGs1DatabarCoupon);

barCodeBuilder.getCaptionAbove().setText("012345-022398");

barCodeBuilder.save("couponUpcaDatabar.png");

{{< /highlight >}}

**BARCODENET-36332 Set Wide Narrow Ratio for barcode symbology** 
Code sample:

{{< highlight xml >}}

 BarCodeBuilder barCodeBuilder = new BarCodeBuilder("blackReduction", Symbology.Code128);

barCodeBuilder.setxDimension(1.2f);

barCodeBuilder.setBarWidthReduction(0.2f);

barCodeBuilder.save("blackReduction.png");

{{< /highlight >}}

**BARCODENET-33941 Can't recognize PDF417 code using the Motorola DS4208 scanner** 
Code sample:

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder();

builder.setSymbologyType(Symbology.Pdf417);

builder.setCodeLocation(CodeLocation.None);

builder.setEnableEscape(true);

builder.setAutoSize(false);

builder.setAspectRatio(4F); // invert 0.25f

builder.setPdf417ErrorLevel(Pdf417ErrorLevel.Level4);

builder.setGraphicsUnit(GraphicsUnit.Millimeter);

builder.setImageHeight(25.4F); //25.4mm = 1in

builder.setImageWidth(101.6F); //76.2F; //76.2mm = 3in 101.6mm=4in

builder.setxDimension(.6F);

builder.setColumns(7);

String text = "";

for(int i = 0; i < 600; i++)

{

    text += "A";

}

builder.setCodeText(text);

builder.save("pdf417barcode.bmp", BarCodeImageFormat.Bmp);

BarCodeReader reader = new BarCodeReader("pdf417barcode.bmp", DecodeType.PDF_417);

while (reader.read())

{

    System.out.println("CodeText: " + reader.getCodeText() + " Type: " + reader.getCodeType());

}

reader.close();

{{< /highlight >}}

**BARCODENET-36336 Barcode generated using Aspose.BarCode after printing is unable to recognized by laser scanner** 
Code sample:

{{< highlight xml >}}

 BarCodeBuilder barCodeBuilder = new BarCodeBuilder();

barCodeBuilder.setCodeText("15-11-48-02-1386");

barCodeBuilder.setSymbologyType(Symbology.Code93Standard);

barCodeBuilder.save("Code93ChecksumFix.png");

{{< /highlight >}}

**BARCODENET-36321 Exclude several unwanted EAN8, MSI, Supplement barcodes** 
Code sample:

{{< highlight xml >}}

 String[] filenames = {"00000039F.tif", "00000052B.tif"};

int counter = 0;

for (int i = 0; i < filenames.length; i++)

{

   String filename = folder + "/" + filenames[i];

   BarCodeReader reader = new BarCodeReader(filename);

   while (reader.read())

   {

       counter++;

       System.out.println(" Symbol:" + reader.getCodeType() + " Code :" + reader.getCodeText());

   }

   reader.close();

}

{{< /highlight >}}

The results:

{{< highlight xml >}}

 Symbol:DataMatrix Code :DI411416000001

 Symbol:DataMatrix Code :0220000000

 Symbol:DataMatrix Code :DI411416000001

 Symbol:DataMatrix Code :DMapp7of7

 Symbol:DataMatrix Code :DMapp7of7

{{< /highlight >}}

**BARCODENET-36335 Generating QR barcode with custom height and width (GraphicsUnit: Inches, Pixel, Millimeter) is not working properly** 
Code sample:

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder();

builder.setSymbologyType(Symbology.QR);

builder.setCodeText(("123456789"));

builder.setCodeLocation((CodeLocation.None));

builder.setQRErrorLevel((QRErrorLevel.LevelH));

builder.setGraphicsUnit((GraphicsUnit.Inch));

builder.setMargins(new MarginsF(0,0,0,0));

float imageWidth = 2.5f;

float imageHeight = 2.5f;

BufferedImage image = builder.getCustomSizeBarCodeImage(imageWidth, imageHeight, false);

ImageIO.write(image, "PNG", new File(folder + "/customSize.png"));

{{< /highlight >}}

**BARCODENET-36247 ISSN barcode throws exception for a valid text** 
Code sample:

{{< highlight xml >}}

 BarCodeBuilder barCodeBuilder = new BarCodeBuilder();

barCodeBuilder.setCodeText("0002-8231");

barCodeBuilder.setThrowExceptionWhenCodeTextIncorrect(true);

barCodeBuilder.setSymbologyType(Symbology.ISSN);

barCodeBuilder.setAutoSize(true);

// Save the image

barCodeBuilder.save("issn.png");

{{< /highlight >}}

**BARCODENET-36338 Unable to Read the EAN13 barcode from an image** 
Code sample:

{{< highlight xml >}}

 String filename = "Incorrect2.png";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.EAN_13);

while (reader.read())

{

   System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

---
title: Aspose.BarCode for Java 7.4.0 Release Notes
type: docs
weight: 10
url: /java/aspose-barcode-for-java-7-4-0-release-notes/
---

Aspose.BarCode for Java has been updated to version 7.4.0 and we are pleased to announce it.

Aspose.BarCode for .NET 7.4.0 - Release notes

Please find Java specific changes and fixing below:
## **Bug fixed**
BARCODJAVA-33577 Code39 encoding, the last code text character is missing

Usage examples:

BARCODENET-34297 Recognize barcode from smaller size images

Code sample

{{< highlight java >}}

 BarCodeReader reader =

new BarCodeReader(folder +

"image0.jpg"

, BarCodeReadType.DataMatrix);

while (reader.read())

{

     System.out.println(reader.getReadTypeName() +

" : "

\+ reader.getCodeText());

}

result:

DataMatrix: doc:3f820419c87d4a878bafd17d2add8d2d009

{{< /highlight >}}

BARCODENET-34265 Add support to re-size top and bottom horizontal bars of ITF14 barcode

Code sample

{{< highlight java >}}



BarCodeBuilder builder = new BarCodeBuilder(

"00850006000227"

);

builder.setSymbologyType(Symbology.ITF14);

builder.setITF14BorderType(ITF14BorderType.BarOut);

builder.setCodeTextFont(new Font(

"OCR B Std"

, Font.PLAIN ,8));

builder.setITF14BorderThickness(1.01f);

builder.save(folder +

"ITF14_border_thickness.png"

, BarCodeImageFormat.Png);

{{< /highlight >}}

BARCODENET-34289 Can't recognize DataMatrix code from the JPG image

Code sample

{{< highlight java >}}



String filename =

"2015_09_19_17_46_42.jpg"

;

BarCodeReader reader = new BarCodeReader(folder + filename, BarCodeReadType.DataMatrix);

while(reader.read()

{

System.out.println(reader.getReadTypeName() +

" : "

\+ reader.getCodeText());

}

result:

DataMatrix : H20-WQ9-F91024-0927

{{< /highlight >}}

BARCODENET-34288 Incorrect recognition of Datamatrix code from the JPG image

Code sample

{{< highlight java >}}



String filename =

"2015_09_19_17_21_36.jpg"

;

BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.DataMatrix);

while(reader.read())

{

System.out.println(reader.getReadTypeName() +

" : "

\+ reader.getCodeText());

}

result:

DataMatrix: H20-WQ9-F91024-2159

{{< /highlight >}}

BARCODENET-34283 ITF14 encoding, BarCodeBuilder.BarHeight properly does not work properly

Code sample

{{< highlight java >}}



BarCodeBuilder b = new BarCodeBuilder(

"01900136700005"

);

b.setAutoSize(true);

b.setSymbologyType(Symbology.ITF14);

b.setCodeTextFont(new Font(

"OCR B Std"

, Font.PLAIN, 10));

b.setGraphicsUnit(GraphicsUnit.Millimeter);

b.setxDimension(0.495f);

b.setBarHeight(31.75f);

b.setCodeTextSpace(0.5f);

b.setResolution(new Resolution(1200.0f, 1200.0f, ResolutionMode.Customized));

b.save(folder +

"ITF14 Sample at 1200dpi.png"

, BarCodeImageFormat.Png);

{{< /highlight >}}

BARCODENET-34282 UPCA encoding, BarCodeBuilder.BarHeight properly does not work properly

Code sample

{{< highlight java >}}



BarCodeBuilder b = new BarCodeBuilder(

"01900136700005"

);

b.setAutoSize(true);

b.setSymbologyType(Symbology.UPCA);

b.setCodeTextFont(new Font(

"OCR B Std"

, Font.PLAIN, 10));

b.setGraphicsUnit(GraphicsUnit.Millimeter);

b.setxDimension(0.495f);

b.setBarHeight(31.75f);

b.setCodeTextSpace(0.5f);

b.setResolution(new Resolution(1200.0f, 1200.0f, ResolutionMode.Customized));

b.save(folder +

"UPCA_fix_1200dpi.png"

, BarCodeImageFormat.Png);

{{< /highlight >}}

BARCODENET-34281 DataBarStackedOmniDirectional encoding, BarCodeBuilder.BarHeight properly does not work properly

Code sample

{{< highlight java >}}



BarCodeBuilder b = new BarCodeBuilder(

"(01)90013670000396(3200)15(11)150819"

);

b.setAutoSize(true);

b.setSymbologyType(Symbology.DatabarStackedOmniDirectional);

b.setCodeTextFont(new Font(

"OCR B Std"

, Font.PLAIN, 10));

b.setGraphicsUnit(GraphicsUnit.Millimeter);

b.setxDimension(0.495f);

b.setBarHeight(31.75f);

b.setCodeTextSpace(0.5f);

b.setResolution(new Resolution(1200.0f, 1200.0f, ResolutionMode.Customized));

b.save(folder +

"DatabarStackedOmniDirectional Sample at 1200dpi.png"

, BarCodeImageFormat.Png);

{{< /highlight >}}

BARCODENET-34280 GS1Code128 Encoding, BarCodeBuilder.BarHeight properly does not work properly

Code sample

{{< highlight java >}}



BarCodeBuilder b = new BarCodeBuilder(

"(01)90013670000396(3200)150000(11)150819"

);

b.setAutoSize(true);

b.setSymbologyType(Symbology.GS1Code128);

b.setCodeTextFont(new Font(

"OCR B Std"

, Font.PLAIN, 10));

b.setGraphicsUnit(GraphicsUnit.Millimeter);

b.setxDimension(0.495f);

b.setBarHeight(31.75f);

b.setCodeTextSpace(0.5f);

b.setResolution(new Resolution(1200.0f, 1200.0f, ResolutionMode.Customized));

b.save(

"GS1Code128 fix at 1200dpi.png"

, BarCodeImageFormat.Png);

{{< /highlight >}}

BARCODENET-33996 Can't recognize all DataMatrix codes from the PNG image

Code sample:

{{< highlight java >}}



String filename =

"Generate Barcode by Specifying Custom Image Size-001.png"

;

BarCodeReader reader = new BarCodeReader(folder + filename, BarCodeReadType.DataMatrix);

while (reader.read())

{

System.out.println(reader.getReadTypeName() +

" : "

\+ reader.getCodeText());

}

result:

DataMatrix : {

"u"

:

"H9329921"

,

"e"

:

"30033629"

}

DataMatrix : {

"u"

:

"K9317614"

,

"e"

:

"30032369"

}

DataMatrix : {

"u"

:

"K9317614"

,

"e"

:

"30032369"

}

DataMatrix : {

"u"

:

"K9317614"

,

"e"

:

"30032369"

}

DataMatrix : {

"u"

:

"K9317614"

,

"e"

:

"30032369"

}

DataMatrix : {

"u"

:

"H9329921"

,

"e"

:

"30033630"

}

DataMatrix : {

"u"

:

"H9329921"

,

"e"

:

"30033630"

}

{{< /highlight >}}

BARCODENET-33829 Can't recognize DataMatrix and incorrect recognition of Code128 from a tif file

Code sample

{{< highlight java >}}



String fileName = Path.combine(Folder,

"00000018_00000005_4.tif"

);

BarCodeReader reader = new BarCodeReader(fileName);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while(reader.read())

{

System.out.println(reader.getReadTypeName() +

" : "

\+ reader.getCodeText());

}

Result:

Code128 : 01321790011790012719230416

DataMatrix : AZ000000393740


{{< /highlight >}}

BARCODENET-33821 Can't recognize DataMatrix code from png image

{{< highlight java >}}



Now we recognize so datamatrix in quality mode.

String filename = folder +

"Long2DCode.PNG"

;

BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.DataMatrix);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while(reader.read())

{

System.out.println(reader.getReadTypeName() +

" : "

\+ reader.getCodeText());

}

result:

DataMatrix : 4M50K13110800052

{{< /highlight >}}

BARCODENET-33368 Unable to recognize Code128 and DataMatrix barcodes from jpg image

Code sample

{{< highlight java >}}



String filename =

"2012103010000052.png"

;

BarCodeReader reader = new BarCodeReader(filename, expectedType);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while(reader.read())

{

System.out.println(reader.getReadTypeName() +

" : "

\+ reader.getCodeText());

}

result:

Code128 : 28002 21241552  010

DataMatrix : SIGI 21241552  987   09272012102117  WC BENEFITS ACCEPTANCE

{{< /highlight >}}

BARCODENET-33336 Set barcode width up to 5mil

Code sample:

{{< highlight java >}}



//Instantiate barcode object

BarCodeBuilder builder = new BarCodeBuIlder();

//Set the Code text for the barcode

builder.setCodeText(

"0BE1240;1167 52"

);

//Set the symbology type to Code128

builder.setSymbologyType(Symbology.Code128);

//Set the barcode width to 5 points

builder.setImageWidth(5f);

//Set the measuring unit of barcode to point

builder.setGraphicsUnit(GraphicsUnit.Point);

builder.setAutoSize(false);

//Save the image to your system

// and set its image format to gif

builder.save(folder +

"barcode3.gif"

, ImageFormat.getGif());

Result:


Result:

Aspose.BarCode.BarCodeException : Too small image's size - width: 6,666667px, height: 80px.


The generated barcode don't fit to image. Barcode size - width: 198px,  height: 20px.

{{< /highlight >}}

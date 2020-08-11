---
title: Aspose.BarCode for Java 8.2.0 Release Notes
type: docs
weight: 40
url: /java/aspose-barcode-for-java-8-2-0-release-notes/
---

Aspose.BarCode for Java has been updated to version 8.1.0 and we are pleased to announce it. The following is a list of changes in this version of Aspose.BarCode.
### **Features and Improvements**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-36297|[Support to generate Code128C coded barcode](http://www.aspose.com/community/forums/thread/765548/barcode-128c.aspx)|New Feature|
|BARCODENET-36259|Add new BarCode type: Aztec Runes|New Feature|
|BARCODENET-36262|Implement Aztec Runes generator|New Feature|
|BARCODENET-36288|Add checksum validation for OneCode and AustraliaPost|New Feature|
|BARCODENET-36266|Implement Aztec Runes decoder|New Feature|
|BARCODENET-36300|Dynabic.Metered Integration|New Feature|
|BARCODENET-36308|The empty hexes for MaxiCode (barcode builder)|Enhancement|
|BARCODENET-36278|[Reading Postnet barcode from image is not producing correct results](http://www.aspose.com/community/forums/thread/759049/postnet-code-text-not-identified-correctly-and-coordinates-returned-by-getregion.points-are-incorrec.aspx)|Enhancement|
|BARCODENET-36310|[Aspose.BarCode is not recognizing correct DataMatix coded barcode](http://www.aspose.com/community/forums/page_2/769721/showthread.aspx)|Enhancement|
|BARCODENET-36254|Recognize DataMatrix with the irregular black/white proportions|Enhancement|
|BARCODENET-36312|Exclude unwanted dutch KIX barcodes|Enhancement|
|BARCODENET-36272|Generate empty picture with incorrect codetext for Aztec|Enhancement|
|BARCODENET-36292|Update AustraliaPost generator|Enhancement|
|BARCODENET-34381|Override platform depending functions.|Enhancement|
|BARCODENET-36270|Improve Aztec encoder speed for long codetext|Enhancement|
|BARCODENET-34336|[Converting PDF to Image stream and then reading barcode is not working .Net](http://www.aspose.com/community/forums/thread/672196/barcode-detecting-only-in-the-specific-region-on-each-page.aspx)|Enhancement|
|BARCODENET-36263|[Unable to get the supplement code text from EAN13 coded barcode](http://www.aspose.com/community/forums/thread/753227/can-t-get-supplement-for-issn.aspx)|Enhancement|
|BARCODENET-36283|[DataMatrix barcode can be decoded by using 3rd party online where as Aspose.BarCode is unable to decode](http://www.aspose.com/community/forums/thread/760436/datamatrix-barcode-can-not-be-recognized.aspx)|Enhancement|
|BARCODENET-36284|Incorrect rotated Postnet barcode recognition|Enhancement|
|BARCODENET-36286|Detects only first Postnet|Enhancement|
# **Usage examples:**
**BARCODENET-36297 - Support to generate Code128C coded barcode** 
Code sample:

The following snippet of code describes how to generate the Code128A, Code128B, Code128C based on the Code Text provided.

{{< highlight xml >}}

 BarCodeBuilder bb = new BarCodeBuilder ("0123456789", Symbology.Code128);

// Save the image

bb.save("Barcode_Issue36297_Code128C.png");

bb = new BarCodeBuilder ("AAA0123456789zzzzz", Symbology.Code128);

// Save the image

bb.save("Barcode_Issue36297_Code128B.png");


bb = new BarCodeBuilder (@"AAA\t\r\n0123456789\t\r\nZZZ", Symbology.Code128);

// Save the image

bb.save("Barcode_Issue36297_Code128A.png");

{{< /highlight >}}

**BARCODENET-36262 - Implement Aztec Runes generator** 
Sample generation code:

{{< highlight java >}}

 BarCodeBuilder b = new BarCodeBuilder();

b.setCodeText("25");

b.setSymbologyType(Symbology.Aztec);

b.setAztecSymbolMode(AztecSymbolMode.Rune);

BufferedImage image = b.getBarCodeImage();

{{< /highlight >}}

**BARCODENET-36288 - Add checksum validation for OneCode and AustraliaPost**

Added checksum validation for OneCode and AustraliaPost.
Fixed recognition for rotated AustraliaPost barcodes.
Fixed GetCheckSum() result for OneCode and AustraliaPost.

Sample pictures (australiapost_incorrect.png, onecode2_incorrect.png) are incorrect and can't be recognized in regular mode.
These pictures can be recognized with turn-off checksum validation (ChecksumValidation.Off).

Code sample 1:

{{< highlight java >}}

 BarCodeReader r = new BarCodeReader("australiapost_incorrect.png", DecodeType.AUSTRALIA_POST);

if (!r.read())

{

        System.out.println("Barcode is incorrect!");

}

{{< /highlight >}}

Result:

Barcode is incorrect!

Code sample 2:

{{< highlight java >}}

 BarCodeReader r = new BarCodeReader("onecode2_incorrect.png", DecodeType.ONE_CODE);

r.setChecksumValidation(ChecksumValidation.Off);

while (r.read())

{

        System.out.println(r.getCodeType() + ": " + r.getCodeText());

        System.out.println("CheckSum: " + r.getCheckSum());

}

{{< /highlight >}}

Result:

OneCode: 44999000000123456789941078350

CheckSum: 1841

**BARCODENET-36266 - Implement Aztec Runes decoder**

Sample reader code:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("aztecRunes25.png");

while (reader.read())

{

        System.out.println("{0}", reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: 25

Second sample reader code:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("aztecRunes68.png"))

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: 68

**BARCODENET-36308 - The empty hexes for MaxiCode (barcode builder)** 
Code sample:

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder("111", Symbology.MaxiCode);

builder.setGraphicsUnit(GraphicsUnit.Millimeter);

builder.setxDimension(0.22f);

builder.setResolution(new Resolution(72, 72, ResolutionMode.Customized));

BufferedImage image1 = builder.generateBarCodeImage();

image1.save("barcode.png", ImageFormat.getPng());

{{< /highlight >}}

Result:

{{< highlight xml >}}

 Aspose.BarCode.BarCodeException :

Size of barcode is too small.

Please, increase the xDimension or resolution.

{{< /highlight >}}

Code sample for correct generation:

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder("111", Symbology.MaxiCode);

builder.setGraphicsUnit(GraphicsUnit.Millimeter);

builder.setxDimension(3);

BufferedImage image1 = builder.generateBarCodeImage();

image1.save("barcode.png", ImageFormat.getPng());

{{< /highlight >}}

**BARCODENET-36278 - Reading Postnet barcode from image is not producing correct results**

Code sample:

{{< highlight java >}}

 BarCodeReader objBReader = new BarCodeReader("barcode_ab-1-1.png", DecodeType.POSTNET);

int counter = 0;

while (objBReader.read())

{

    counter++;

    System.out.println(" -- Symbol:" + objBReader.getCodeType() + " Code: " + objBReader.getCodeText());

    System.out.println(" -- Angle:" + objBReader.getAngle());

}

objBReader.close();

System.out.println(counter.toString());

{{< /highlight >}}

Result:

` `-- Symbol:Postnet Code: 21842

` `-- Angle:0,6480604

1

Added checksum validation to reject fake results (69840).
Also improved result for GetAngle().
Now returns the same results for different rotation hints:
Sample 1, "Rotate180":

{{< highlight java >}}

 BarCodeReader objBReader = new BarCodeReader(fileName, DecodeType.POSTNET);

objBReader.setOrientationHints(RecognitionHints.Orientation.Rotate180);

int counter = 0;

while (objBReader.read())

{

    counter++;

    System.out.println(" -- Symbol:" + objBReader.getCodeType() + " Code: " + objBReader.getCodeText());

    System.out.println(" -- Angle:" + objBReader.getAngle());

}

objBReader.close();

System.out.println(counter);

{{< /highlight >}}

Result:

` `-- Symbol:Postnet Code: 21842

` `-- Angle:180,6961

1

Sample 2, "Rotate270":

{{< highlight java >}}

 objBReader.setOrientationHints(RecognitionHints.Orientation.Rotate270);

{{< /highlight >}}

Result:

` `-- Symbol:Postnet Code: 21842

` `-- Angle:270,696

1

Sample 3, "Rotate90":

{{< highlight java >}}

 objBReader.setOrientationHints(RecognitionHints.Orientation.Rotate90);

{{< /highlight >}}

Result:

` `-- Symbol:Postnet Code: 21842

` `-- Angle:90,64806

1

**BARCODENET-36310 - Aspose.BarCode is not recognizing correct DataMatix coded barcode** 
Fixed the incorrect recognized Datamatrix.
Code sample:

{{< highlight xml >}}

 String filename = @"00000001F.tif";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);

while (reader.read())

{

	System.out.println(reader.getReadType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 DataMatrix: DMapp1of3

DataMatrix: DI011416000001

DataMatrix: 0120000000

DataMatrix: DI011416000001

DataMatrix: DMapp1of3

{{< /highlight >}}

**BARCODENET-36254 - Recognize DataMatrix with the irregular black/white proportions**

Code sample

{{< highlight xml >}}

 String filename = "irregular-matrix.png";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);

reader.setRecognitionMode(RecognitionMode.ManualHints);

reader.setManualHints(ManualHint.IncorrectBarcodes);

while(reader.read())

{

	System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

**BARCODENET-36312 - Exclude unwanted dutch KIX barcodes**

Code sample:

{{< highlight xml >}}

 BarCodeReader reader = new BarCodeReader("00000013F.tif", DecodeType.DUTCH_KIX);

int cnt = 0;

while (reader.read())

{

	cnt++;

	System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

System.out.println("Read " + cnt + " barcodes");

{{< /highlight >}}

Result:

{{< highlight xml >}}

 Read 0 barcodes

{{< /highlight >}}

**BARCODENET-36272 - Generate empty picture with incorrect codetext for Aztec**

Sample 1.
With correct codetext with 3067 characters:

{{< highlight xml >}}

 BarCodeBuilder b = new BarCodeBuilder();

String text = "";

for(int i = 0; i< 3067; i++)

{

   text += 'A';

}

b.setCodeText(text);

b.setSymbologyType(Symbology.Aztec);

BufferedImage image = b.getBarCodeImage();

{{< /highlight >}}

Sample 2.
With incorrect codetext (over limit) - 3068 characters:

{{< highlight java >}}

 BarCodeBuilder b = new BarCodeBuilder();

String text = "";

for(int i = 0; i< 3068; i++)

{

   text += 'A';

}

b.setCodeText(text);

b.setSymbologyType(Symbology.Aztec);

BufferedImage image = b.getBarCodeImage();

{{< /highlight >}}

Result:

{{< highlight xml >}}

 throws InvalidCodeException.

{{< /highlight >}}

**BARCODENET-36292 - Update AustraliaPost generator** 
Added support new encodings for Customer Infomation Field in AustraliaPost builder.
New propery CustomerInformationInterpretingType was added to BarCodeBuilder class.

Code sample for generating Standard Customer Barcode:

{{< highlight java >}}

 BarCodeBuilder b = new BarCodeBuilder();

b.setCodeText("1112345678");

b.setSymbologyType(Symbology.AustraliaPost);

BufferedImage image = b.getBarCodeImage();

{{< /highlight >}}

Code sample for generating and recognizing barcode in Customer Barcode 2 format with N Encoding Table:

{{< highlight java >}}

 BarCodeBuilder b = new BarCodeBuilder();

b.setCodeText("5912345678123456");

b.setSymbologyType(Symbology.AustraliaPost);

b.setCustomerInformationInterpretingType(CustomerInformationInterpretingType.NTable);

BufferedImage image = b.getBarCodeImage();

BarCodeReader r = new BarCodeReader(image, DecodeType.AUSTRALIA_POST);

r.setCustomerInformationInterpretingType(CustomerInformationInterpretingType.NTable);

while (r.read())

{

    System.out.println(r.getCodeType() + ": " + r.getCodeText());

}

{{< /highlight >}}

Result:

AustraliaPost: 5912345678123456

Code sample for generating and recognizing barcode in Customer Barcode 3 format with C Encoding Table:

{{< highlight java >}}

 BarCodeBuilder b = new BarCodeBuilder();

b.setCodeText("6212345678ABCdef123#");

b.setSymbologyType(Symbology.AustraliaPost);

b.setCustomerInformationInterpretingType(CustomerInformationInterpretingType.CTable);

BufferedImage image = b.getBarCodeImage();

BarCodeReader r = new BarCodeReader(image, DecodeType.AUSTRALIA_POST);

r.setCustomerInformationInterpretingType(CustomerInformationInterpretingType.CTable);

while (r.read())

{

   System.out.println(r.getCodeType() + ": " + r.getCodeText());

}

{{< /highlight >}}

Result:

AustraliaPost: 6212345678ABCdef123#

Code sample for generating barcode in Customer Barcode 2 format with raw data ('0', '1', '2' or '3' symbol in Customer Information Field):

{{< highlight java >}}

 BarCodeBuilder b = new BarCodeBuilder();

b.setCodeText("59123456780123012301230123");

b.setSymbologyType(Symbology.AustraliaPost);

b.setCustomerInformationInterpretingType(CustomerInformationInterpretingType.Other);

BufferedImage image = b.getBarCodeImage();

BarCodeReader r = new BarCodeReader(image, DecodeType.AUSTRALIA_POST);

r.setCustomerInformationInterpretingType(CustomerInformationInterpretingType.Other);

while (r.read())

{

   System.out.println(r.getCodeType() + ": " + r.getCodeText());

}

{{< /highlight >}}

Result:

AustraliaPost: 59123456780123012301230123

**BARCODENET-36263 - Unable to get the supplement code text from EAN13 coded barcode**

Code sample

{{< highlight xml >}}

 String filename = "PB_2016_06.jpg";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.EAN13, DecodeType.SUPPLEMENT);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

reader.setChecksumValidation(ChecksumValidation.Default);

while (reader.read())

{

	String codeText = reader.getCodeText();

	int codeType = reader.getCodeType();

	String codeChecksum = reader.getCheckSum();

	System.out.println(codeType + ":" + codeText + "," + codeChecksum);

}

reader.close();

{{< /highlight >}}

Result:

{{< highlight xml >}}

 EAN13:977156251000,9

Supplement:00616,

{{< /highlight >}}

**BARCODENET-36283 - DataMatrix barcode can be decoded by using 3rd party online where as Aspose.BarCode is unable to decode**

Code sample

{{< highlight xml >}}

 String filename = "2bea0d7c-2a1b-445a-90cf-15b61b53f7a1.bmp";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX))

while (reader.read())

{

       System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 DataMatrix: AK46135U11098U118VC12080D

{{< /highlight >}}

**BARCODENET-36284 - Incorrect rotated Postnet barcode recognition**

Code sample:

{{< highlight java >}}

 BarCodeReader objBReader = new BarCodeReader("postnet2_rot180.png", DecodeType.POSTNET);

int counter = 0;

while (objBReader.read())

{

    counter++;

    System.out.println(" -- Symbol:" + objBReader.getCodeType() + " Code: " + objBReader.getCodeText());

    System.out.println(" -- Angle:" + objBReader.getAngle());

}

objBReader.close();

System.out.println(counter);

{{< /highlight >}}

Result:

` `-- Symbol:Postnet Code: 56789

` `-- Angle:180

1

**BARCODENET-36286 - Detects only first Postnet**

Code sample:

{{< highlight java >}}

 BarCodeReader objBReader = new BarCodeReader("postnet_two.png", DecodeType.POSTNET);

int counter = 0;

while (objBReader.read())

{

    counter++;

    System.out.println(" -- Symbol:" + objBReader.getCodeType() + " Code: " + objBReader.getCodeText());

    System.out.println(" -- Angle:" + objBReader.getAngle());

}

objBReader.close();

System.out.println(counter);

{{< /highlight >}}

Result:

` `-- Symbol:Postnet Code: 12345

` `-- Angle:0

` `-- Symbol:Postnet Code: 23456

` `-- Angle:90

2

**BARCODENET-36300 - Dynabic.Metered Integration** 
New class Metered has been added in order to provide the set of methods to apply metered key.
In this example, an attempt will be made to set metered public and private key

{{< highlight xml >}}

 Metered matered = new Metered();

matered.setMeteredKey("PublicKey", "PrivateKey");

{{< /highlight >}}

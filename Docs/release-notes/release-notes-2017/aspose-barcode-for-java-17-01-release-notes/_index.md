---
title: Aspose.BarCode for Java 17.01 - Release Notes
type: docs
weight: 120
url: /java/aspose-barcode-for-java-17-01-release-notes/
---

Aspose.BarCode for Java has been updated to version 17.01 and we are pleased to announce it.
The following is a list of changes in this version of Aspose.Java.
### **Features and Improvements**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-36423|Reading problem of DataMatrix barcode|Enhancement|
|BARCODENET-36405|[Creating barcode of 300 dpi is producing wider barcode image](https://www.aspose.com/community/forums/thread/808431/barcode-resolution-size.aspx)|Enhancement|
|BARCODENET-36352|Implement algorithm for recognizing 3D-distorted MicroQr|Enhancement|
|BARCODENET-34234|[BarCodeBuilder does not generate CodeText for characters: "~B" and "~C" properly for PDF417;](http://www.aspose.com/community/forums/thread/644330/pdf417-barcode-not-encoding-tilde.aspx)|Enhancement|
|BARCODENET-33257|[Cannot recognize DataMatrix when image has been scanned at 200DPI](http://www.aspose.com/community/forums/thread/376559/cannot-read-barcode-when-aspose.words-document-is-saved-as-pdf.aspx)|Enhancement|
# **Usage examples**
**BARCODENET-36352 Implement algorithm for recognizing 3D-distorted MicroQr**

Sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader(folder + "SonyDV_BatteryPack_00.JPG", DecodeType.MICRO_QR);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while(reader.read()

{

   System.out.println(reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight java >}}

 FV50CE

{{< /highlight >}}

**BARCODENET-34234 BarCodeBuilder does not generate CodeText for characters: "~B" and "~C" properly for PDF417;**

Sample:

{{< highlight java >}}

 String codeText = "A^aa^a^a^a^someFreeText~B^bb^b^b^b^OtherFreeText~C^cc^c^c^c^LastFreeText|";

BarCodeBuilder b = new BarCodeBuilder();

b.setSymbologyType(Symbology.Pdf417);

b.setAspectRatio(4f);

b.setPdf417ErrorLevel(Pdf417ErrorLevel.Level4);

b.setCodeLocation(CodeLocation.None);

b.setCodeText(codeText);

b.save("Issue34234.SavedFromMemory.jpg", BarCodeImageFormat.Jpeg);

BarCodeReader reader = new BarCodeReader("Issue34234.SavedFromMemory.jpg", BarCodeReadType.Pdf417);

while(reader.read()

{

   System.out.println(reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight java >}}

 A^aa^a^a^a^someFreeText~B^bb^b^b^b^OtherFreeText~C^cc^c^c^c^LastFreeText|

{{< /highlight >}}

Note: To input decimal ascii code try next sample.
Sample:

{{< highlight java >}}

 String expected = "A^aa^a^a^a^someFreeText~B^bb^b^b^b^OtherFreeText~C^cc^c^c^c^LastFreeText|";

BarCodeBuilder builder = new BarCodeBuilder();

builder.setEncodeType(EncodeTypes.PDF_417);

builder.setPdf417ErrorLevel(Pdf417ErrorLevel.Level4);

builder.setCodeLocation(CodeLocation.None);

builder.setColumns(8);

builder.setCodeText(expected);

ByteArrayOutputStream stream = new ByteArrayOutputStream();

builder.save(stream, BarCodeImageFormat.Jpeg);

stream.flush();

BarCodeReader reader = new BarCodeReader(new ByteArrayInputStream(stream.toByteArray()), DecodeType.PDF_417);

while(reader.read()

{

   System.out.println(reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight java >}}

 ABC

ABC

{{< /highlight >}}

**BARCODENET-33257 Cannot recognize DataMatrix when image has been scanned at 200DPI**

Sample:

{{< highlight java >}}

 Map<String, String> files = new HashMap<String, String>();

files.put("Barcode200DPIA.png", "ZZZ#WAS#EF#0203##Unknown#Cost Estimate - Conventional#Cost Estimate Sheet#Test#6/19/2012#True#N/A");

files.put("Barcode200DPIB.png", "ZZZ#WAS#EF#0203##Unknown#Summary MSS#Summary MSS#Test#6/19/2012#True#N/A");

for(String filename : files.keySet())

{

   BarCodeReader reader = new BarCodeReader(folder + filename, DecodeType.DATA_MATRIX);

   reader.setRecognitionMode(RecognitionMode.MaxBarCodes);

   while(reader.read()

   {

      System.out.println(reader.getCodeText());

   }

}

{{< /highlight >}}

Result:

{{< highlight java >}}

 DataMatrix: ZZZ#WAS#EF#0203##Unknown#Cost Estimate - Conventional#Cost Estimate Sheet#Test#6/19/2012#True#N/A

DataMatrix: ZZZ#WAS#EF#0203##Unknown#Summary MSS#Summary MSS#Test#6/19/2012#True#N/A

{{< /highlight >}}

**BARCODENET-36405 Creating barcode of 300 dpi is producing wider barcode image**

Sample:

{{< highlight java >}}

 BarCodeBuilder builder = new BarCodeBuilder();

builder.setCodeText("6040223579");

builder.setResolution(new Resolution(300, 300, ResolutionMode.Graphics));

builder.setEncodeType(EncodeTypes.CODE_128);

builder.setBarHeight(0.6f);

builder.save("Code128_0.6mm_300pdi.tiff", ImageFormat.getTiff());

{{< /highlight >}}

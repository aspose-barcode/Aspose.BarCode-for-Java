---
title: Aspose.BarCode for Java 8.1.0 Release Notes
type: docs
weight: 50
url: /java/aspose-barcode-for-java-8-1-0-release-notes/
---

Aspose.BarCode for Java has been updated to version 8.1.0 and we are pleased to announce it. The following is a list of changes in this version of Aspose.BarCode.
### **Features and Improvements**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-34430|Implement new algorithm for recognizing 3D-distorted Aztec codes|New Feature|
|BARCODENET-36252|Fake recognition of 2D barcodes with many errors|Enhancement|
|BARCODENET-36248|Improve recognition speed for Aztec|Enhancement|
|BARCODENET-36232|Databar Expanded barcode is generated incorrectly for the big lenght (21 or 22 symbols)|Enhancement|
|BARCODENET-36231|Add error correction for Mode Message in Aztec code|Enhancement|
|BARCODENET-36230|Correction of moot symbols in a DatabarExpanded barcode|Enhancement|
|BARCODENET-36229|Recognize a datamtrix with black dots around|Enhancement|
|BARCODENET-36228|Fix order of recognition results for Aztec|Enhancement|
|BARCODENET-36223|Unable to recognize the barcode from PDF|Enhancement|
|BARCODENET-36211|Recognize data matrix with a lot of errors because of low scanner resolution|Enhancement|
|BARCODENET-36195|Program hangs on Aztec code|Enhancement|
|BARCODENET-34304|QREncodeMode Enumeration is missing Binary enum|Enhancement|
**BARCODENET-34430 Implement new algorithm for recognizing  3D-distorted Aztec codes** 
Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("AaBbCc_3D_2.png", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: AaBbCc

Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("manyRotatedAztecs_2.png", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (reader.read())

{

   System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: ABCDEF

Aztec: ABCDEF

Aztec: 4565345456545456

Aztec: 012345678901234567890123456789

Aztec: AaBbCc

Aztec: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

Aztec: AaBbCc

Aztec: AaBbCc

Aztec: www.8qr.de/123aq

Aztec: AaBbCc

**BARCODENET-36252 Fake recognition of 2D barcodes with many  errors** 
Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("badRs.png", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxBarCodes);

while (reader.read())

{

   System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: AaBbCc

**BARCODENET-36248 Improve recognition speed for Aztec** 
Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("test1_orig.jpg", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxBarCodes);

while (reader.read())

{

   System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: IwQAANtYAAJDAP8xAHwAQgBBAPtLADAAORr3NgA4Ar00KjHaCho19y4zIm8eUABSAP5FAFoAWQBE/w5OAFQAIPcATQAuCu9TAFQPvVeOUu8aWg5Xe058UrUWSe9aTF5Je9pBSt1CErsOQQFPBvcYAUsWbk5VAEz2g569T2ZM1lcBjr5LXzEOLjfgRtcPLRrtMgAy1xuHr1dba1I2Tt8zY1R7mlkG3lQ/zQND7x5SBkzfAExfMnvuMA7eRLY07QpCSnBe5w8bQUVa2DAD7zWuMg83uDIadD8tESveMCot9gMOg0fbwUH7d0u6BpODVGOHf0FOH9gnBaFzBmO8OLtaDzL4BzKtP5bzGrw59zAfSyMS0LpF8b9Ni0q75zh+3kMONQhzqDbS7DcANBNYoR8eZ4o1Qw2GDzLxO0+7fAA0YkvH8685JixvPzYAsTYX8UQAINgP8BP0DzQfpzMAsHz3cFNoUsCru05IANNr7k8m0wZCpkdBF0uXLSM3OdqXN0F9AzheQzjwvjbdjzJWQ+4DOAAwSwzLs0d8aydmA05C2TO+OQsyW3eCSSSSSoD/AA==f

**BARCODENET-36232 Databar Expanded barcode is generated incorrectly  for the big lenght (21 or 22 symbols)** 
Code sample

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder(@"8110106141411234562891101201212085010048000214025610048000310123191000", Symbology.DatabarExpanded);

builder.save("databarExpandedLong.png");

{{< /highlight >}}

**BARCODENET-36230 Correction of moot symbols in a DatabarExpanded  barcode** 
Code sample

{{< highlight xml >}}

 String filename = "Moot-DatabarExpanded.png";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATABAR_EXPANDED);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 DatabarExpanded: (8110)106141411234562891101201212085010048000214025610048000310123191000

{{< /highlight >}}

**BARCODENET-36229 Recognize a datamtrix with black dots  around** 
Code sample

{{< highlight xml >}}

 String filename = "DataMatrix01_cutted.tif";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 DataMatrix: 424D4D415231364252433F379A45D7F4788F76C4C00BC4D31061E63F0B0136CB3C9C385BBC0E85C2B985

{{< /highlight >}}

**BARCODENET-36228 Fix order of recognition results for Aztec** 
Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("manyRotatedAztecs_2.png", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: ABCDEF

Aztec: ABCDEF

Aztec: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

Aztec: 4565345456545456

Aztec: 012345678901234567890123456789

Aztec: AaBbCc

Aztec: AaBbCc

Aztec: AaBbCc

Aztec: www.8qr.de/123aq

Aztec: AaBbCc

**BARCODENET-36223 Unable to recognize the barcode from PDF  [.Net]** 
Code sample:

{{< highlight xml >}}

 com.aspose.pdf.License licensePdf = new com.aspose.pdf.License();

licensePdf.setLicense("Aspose.Total.Java.lic");

// bind the pdf document

com.aspose.pdf.facades.PdfExtractor pdfExtractor = new com.aspose.pdf.facades.PdfExtractor();

pdfExtractor.bindPdf("CodBar_DOC_2016_06_02_13_00_50_617.pdf");

// set page range for image extraction

pdfExtractor.setStartPage(1);

pdfExtractor.setEndPage(4);

// extract the images

System.out.println("Extracting images.....");

pdfExtractor.extractImage();

// save images to stream in a loop

while (pdfExtractor.hasNextImage())

{

   System.out.println("Getting next image....");

   // save image to stream

   ByteArrayOutputStream outputStreamImageStream = new ByteArrayOutputStream();

   pdfExtractor.getNextImage(outputStreamImageStream);

   InputStream imageStream = new ByteArrayInputStream(outputStreamImageStream.toByteArray());

   System.out.println("Recognizing barcode....");

   // recognize the barcode from the image stream above

   BarCodeReader barcodeReader = new BarCodeReader(imageStream, DecodeType.ALL_SUPPORTED_TYPES);

    barcodeReader.setRecognitionMode(RecognitionMode.ManualHints | RecognitionMode.MaxQuality);

    barcodeReader.setManualHints(ManualHint.ComplexBackground | ManualHint.IncorrectBarcodes | ManualHint.MedianSmoothing);

    barcodeReader.setOrientationHints(RecognitionHints.Orientation.Rotate180);

    while (barcodeReader.read())

    {

        System.out.println("Codetext found: " + barcodeReader.getCodeText() + ", Symbology: " + barcodeReader.getCodeType().toString());

    }

    // close the reader

    barcodeReader.close();

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 Codetext found: 40193240260010201111, Symbology: Code128

Codetext found: 40193132350010201111, Symbology: Code128

Codetext found: 40192979040010201111, Symbology: Code128

{{< /highlight >}}

**BARCODENET-36211 Recognize data matrix with a lot of errors because  of low scanner resolution** 
Code sample

{{< highlight xml >}}

 String filename = "Barcode200DPIB.png";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

if (reader.read())

{

&nbsp;&nbsp;&nbsp; System.out.println(reader.getCodeText());

}

reader.Close();

{{< /highlight >}}

Result:

{{< highlight xml >}}

 ZZZ#WAS#EF#0203##Unknown#Summary MSS#Summary MSS#Test#6/19/2012#True#N/A

{{< /highlight >}}

**BARCODENET-36195 Program hangs on Aztec code** 
Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("badRs.png", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: AaBbCc

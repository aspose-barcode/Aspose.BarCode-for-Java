---
title: Aspose.BarCode for Java 16.12 Release Notes
type: docs
weight: 10
url: /java/aspose-barcode-for-java-16-12-release-notes/
---

Aspose.BarCode for Java has been updated to version 16.12 and we are pleased to announce it. The following is a list of changes in this version of Aspose.BarCode.
### **Features and Improvements**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODEJAVA-149|[Reading problem of DataMatrix barcode](http://www.aspose.com/community/forums/thread/655630.aspx)|Enhancement|
|BARCODENET-36365|Improve recognition speed for Aztec|Enhancement|
|BARCODENET-36383|Improve recognition speed for Aztec by reducing the number of passes|Enhancement|
|BARCODENET-36380|Include CodablockF and Coupons in Silverlight control|Enhancement|
|BARCODENET-36387|Incorrect recognition of CodabloackF with AllSupportedTypes|Enhancement|
|BARCODENET-36398|[Reading Barcode from a BMP image is generating exception](https://www.aspose.com/community/forums/thread/806966/performance.aspx)|Enhancement|
|BARCODENET-36381|Remove line offset for CodablockF (GraphicsUnit.Pixel)|Enhancement|
|BARCODENET-36395|The recognition performance of rotated DataMatrix barcodes has been improved|Enhancement|
|BARCODENET-36419|Too small quiet zone for ITF14 when border type is Frame or FrameOut|Enhancement|
|BARCODENET-36382|[Creating a Datamatrix barcode with DataMatrixEccType.Ecc200 throws a null reference exception](http://www.aspose.com/community/forums/thread/804198/creating-a-datamatrix-barcode-with-datamatrixecctype.ecc200-throws-a-null-reference-exception.aspx)|Enhancement|
|BARCODENET-36353|[Barcode reader is unable to recognize the barcode on an image](http://www.aspose.com/community/forums/thread/786272/i-need-to-scan-this-bar-code-returns-wrong-data.aspx)|Enhancement|
|BARCODENET-36406|[Unable to recognize PDF417 barcode from a drivers' license image](https://www.aspose.com/community/forums/thread/809898/pdf417-not-recognized-in-jpg-image.sometimes.aspx)|Enhancement|
|BARCODENET-36400|Dynabic.Metered: Aspose.BarCode has to be adjusted to the latest Dynabic.Metered version|Enhancement|
# **Usage examples:**
**BARCODENET-36365 Improve recognition speed for Aztec** 
The recognition speed has been improved for Aztec in MaxQuality mode.
The recognition of 3D-distorted Aztec code has been enhanced by new target detection.

Sample code:

{{< highlight xml >}}

 BarCodeReader reader = new BarCodeReader("img2_b_edited.jpg", DecodeType.AZTEC);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Output:

{{< highlight xml >}}

 Aztec: int main() { AztecCode azt; return 0;} 0123456789 efghijklmnoprstuvwxyz

{{< /highlight >}}

**BARCODENET-36383 Improve recognition speed for Aztec by reducing the number of passes** 
The recognition speed has been improved by means of:

- Skipping very similar targets (centers are close);
- Reducing recognition passes;

Sample:

{{< highlight xml >}}

 long stopwatch = System.currentTimeMillis();

BarCodeReader reader = new BarCodeReader("manyRotatedAztecs_2.png", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

int number = 0;

while (reader.read())

{

    System.out.println(number + ": " + reader.getCodeType() + ": " + reader.getCodeText());

    number++;

}

System.out.println("Time elapsed: " + (System.currentTimeMillis() - stopwatch));

{{< /highlight >}}

Results:

{{< highlight xml >}}

 0: Aztec: ABCDEF

1: Aztec: ABCDEF

2: Aztec: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

3: Aztec: 4565345456545456

4: Aztec: 012345678901234567890123456789

5: Aztec: AaBbCc

6: Aztec: AaBbCc

7: Aztec: AaBbCc

8: Aztec: www.8qr.de/123aq

9: Aztec: AaBbCc

Time elapsed: 00:00:00.7664338

{{< /highlight >}}

**BARCODENET-36387 Incorrect recognition of CodabloackF with AllSupportedTypes**

- Calculation of vertexes for CodablockF has been fixed;
- The postfilter for filtering out the duplicates of Code128 results has been added;

Sample code:

{{< highlight xml >}}

 BarCodeReader reader = new BarCodeReader("Codablock-F_Example.png", DecodeType.ALL_SUPPORTED_TYPES);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 CodablockF: Codablock-F Example

{{< /highlight >}}

{{< highlight xml >}}

 BarCodeReader reader = new BarCodeReader("codablockfAndCode128_4.png", DecodeType.ALL_SUPPORTED_TYPES);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 Code128: Code128 sample

CodablockF: Codablock F sample

{{< /highlight >}}

**BARCODENET-36398 Reading Barcode from a BMP image is generating exception** 
Code sample

{{< highlight xml >}}

 String filename = "img_16_notworking.bmp";

BarCodeReader barcodeReader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);

int counter = 0;

while (barcodeReader.read())

{

    counter++;

    System.out.println(" -- Symbol:" + barcodeReader.getCodeType() + " Code :" + barcodeReader.getCodeText());

}

barcodeReader.close();

{{< /highlight >}}

Result:

{{< highlight xml >}}

 -- Symbol:DataMatrix Code :O60000001670817681446463

{{< /highlight >}}

**BARCODENET-36381 Remove line offset for CodablockF (GraphicsUnit.Pixel)** 
Code sample:

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder("012345601234560123456", EncodeTypes.CODABLOCK_F);

builder.setGraphicsUnit(GraphicsUnit.Pixel);

builder.save("CodaBlockF.png");

{{< /highlight >}}

**BARCODENET-36419 Too small quiet zone for ITF14 when border type is Frame or FrameOut** 
Code sample

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder();

builder.setCodeText("1234567890");

builder.setEncodeType(EncodeTypes.ITF_14);

builder.setITF14BorderType(ITF14BorderType.FrameOut);

builder.setxDimension(1f);

builder.save("ITF14_quietZone.png");

{{< /highlight >}}

**BARCODENET-36353 Barcode reader is unable to recognize the barcode on an image** 
The issue will not be fixed due to low image quality provided.
It was scanned with low resolution.
In order to have an image recognized well, the scan resolution has to be 300 dpi, at least.

**BARCODENET-36406 Unable to recognize PDF417 barcode from a drivers' license image** 
The issue will not be fixed due to low image quality provided.
In order to have an image recognized well, the scan resolution has to be 300 dpi, at least.

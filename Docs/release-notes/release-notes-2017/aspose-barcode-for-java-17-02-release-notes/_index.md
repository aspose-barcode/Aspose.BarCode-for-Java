---
title: Aspose.BarCode for Java 17.02 - Release Notes
type: docs
weight: 110
url: /java/aspose-barcode-for-java-17-02-release-notes/
---

Aspose.BarCode for Java has been updated to version 17.02 and we are pleased to announce it.
The following is a list of changes in this version of Aspose.BarCode.
### **Features and Improvements**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODEJAVA-171|Improve Aspose.Barcode for Java for reducing memory consumption|Enhancement|
|BARCODENET-36457|[Unable to successfully read code39standard barcode from PNG image](https://www.aspose.com/community/forums/thread/820819/code39standard-barcode-is-not-recognized.aspx)|Enhancement|
|BARCODENET-36456|[QR code is not recognizing](https://www.aspose.com/community/forums/thread/820069/aspose-barcode-reader-for-qr-code-appears-to-be-not-working-for-the-attached-document.aspx)|Enhancement|
|BARCODENET-36450|Restore code128 uses a bitmap spliting in the histogram algorithm|Enhancement|
# **Usage examples**
**BARCODENET-36457 Unable to successfully read code39standard barcode from PNG image**

Sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("bar.png", DecodeType.ALL_SUPPORTED_TYPES);

reader.setRecognitionMode(RecognitionMode.MaxBarCodes);

while (reader.read())

{

   System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight java >}}

 Code39Standard: 16-23285

{{< /highlight >}}

**BARCODENET-36456 QR code is not recognizing**

Sample:

{{< highlight java >}}

 String filename = "image1_out.png";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.QR);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (reader.read())

{

   System.out.println(reader.getBarCodeDecodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight java >}}

 QR: B8BFFEE8-75C2-47EA-AF95-A608BF574771-201701-1501761-29238569-18259579-8929-1988-07-26

{{< /highlight >}}

**BARCODENET-36450 Restore code128 uses a bitmap spliting in the histogram algorithm**

Code sample

{{< highlight java >}}

 String filename = "42.tif";

BarCodeReader barcodeReader = new BarCodeReader(filename, DecodeType.CODE_128);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (barcodeReader.read())

{

   System.out.println(" -- Symbol:" + barcodeReader.getCodeType() + " Code :" + barcodeReader.getCodeText());

}

{{< /highlight >}}

{{< highlight java >}}

 Result:

 -- Symbol:Code128 Code :AZ000000014942

{{< /highlight >}}

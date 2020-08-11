---
title: Read Barcodes with Aspose.BarCode APIs
type: docs
weight: 10
url: /java/read-barcodes-with-aspose-barcode-apis/
---

## **Barcode Recognition**
{{% alert color="primary" %}} 

Aspose.BarCode for Java contains two powerful classes, BarCodeGenerator and BarCodeReader, handling barcode image generation and recognition respectively.

BarCodeReader reads most commonly used 1D and 2D barcodes, detecting them anywhere.

{{% /alert %}} 
### **Supported Symbologies**
Supported generation and recognition barcode symbologies:

|**Symbology**|**Generation**|**Recognition**|
| :- | :- | :- |
|Australia Post|Yes|Yes|
|Code39Standard|Yes|Yes|
|Code39Extended|Yes|Yes|
|Code93Standard|Yes|Yes|
|Code93Extended|Yes|Yes|
|DataMatrix|Yes|Yes|
|Code128|Yes|Yes|
|Code11|Yes|Yes|
|Codabar|Yes|Yes|
|EAN8|Yes|Yes|
|EAN13|Yes|Yes|
|EAN14|Yes|Yes|
|EAN128|Yes|Yes|
|BooklandEAN|Yes|Yes|
|Interleaved2of5|Yes|Yes|
|ITF14|Yes|Yes|
|Standard2of5|Yes|Yes|
|UPCA|Yes|Yes|
|UPCE|Yes|Yes|
|USPS OneCode|Yes|Yes|
|PatchCode|Yes|Yes|
|Planet|Yes|Yes|
|Postnet|Yes|Yes|
|DataMatrix|Yes|Yes|
|QR|Yes|Yes|
|SSCC18|Yes|Yes|
|Pdf417|Yes|Yes|
|MSI|Yes|Yes|
|Supplement Plus 2|Yes|Yes|
|Supplement Plus 5|Yes|Yes|
|Matrix 2 of 5|Yes|Yes|
### **Scanning a Barcode from a Picture**
The following example demonstrates how to scan a picture of a barcode image using Aspose.BarCode.

|![todo:image_alt_text](http://i.imgur.com/S5k75Xf.jpg)|
| :- |
|**Figure: A barcode image**|


|![todo:image_alt_text](http://i.imgur.com/97XU28P.jpg)|
| :- |
|**Figure: Scan result**|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-basic_features-Barcode_Recognition-Barcode_Recognition.java" >}}
## **Supported Image Formats**
[BarCodeReader](https://apireference.aspose.com/java/barcode/com.aspose.barcode.barcoderecognition/package-frame) accepts many standard image formats.

- **BMP** – BMP is a standard format to store device-independent and application-independent images. The number of bits per pixel (1, 4, 8, 15, 24, 32, or 64) for a given BMP file is specified in the file header. BMP files with 24 bits per pixel are common. BMP files are usually not compressed.
- **Graphics Interchange Format (GIF)** – GIF is a common format for images that appear on Web pages. GIFs are compressed, but no information is lost in the compression process; a decompressed image is exactly the same as the original. One color in a GIF can be designated as transparent so that the image will have the background color of any Web page that displays it. GIFs store at most 8 bits per pixel, so they are limited to 256 colors.
- **Joint Photographic Experts Group (JPEG)** – JPEG is a compression scheme that works well for natural scenes such as scanned photographs. Some information is lost in the compression process, but often the loss is imperceptible to the human eye. JPEGs store 24 bits per pixel, so they are capable of displaying more than 16 million colors. JPEGs do not support transparency. * **Portable Network Graphics (PNG)** – The PNG format retains many of the advantages of the GIF format but also provides capabilities beyond those of GIF. Like GIF files, PNG files are compressed with no loss of information. PNG files can store colors with 8, 24, or 48 bits per pixel and grayscales with 1, 2, 4, 8, or 16 bits per pixel. In contrast, GIF files can use only 1, 2, 4, or 8 bits per pixel. A PNG file can also store an alpha value for each pixel, which specifies the degree to which the color of that pixel is blended with the background color.
- **Tag Image File Format (TIFF)** – TIFF is a flexible and extendable format that is supported by a wide variety of platforms and image-processing applications. TIFF files can store images with an arbitrary number of bits per pixel and can employ a variety of compression algorithms.

|![todo:image_alt_text](http://i.imgur.com/j26G0LQ.jpg)|
| :- |
|**Figure: BarCodeReader reads in a JPG image**|


|![todo:image_alt_text](http://i.imgur.com/BZm6N0X.png)|
| :- |
|**Figure: Output after reading**|
#### **Java**
{{< gist "aspose-barcode" "e49c13ebe68efe7c059e32871b13a3ab" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-basic_features-SupportedImageFormats-.java" >}}
## **Recognizing Specific Barcode Symbology**
This article shows the basic settings of the BarCodeReader class.
### **A Barcode's Symbology Type**
The examples below specifies the symbology type in the constructor of the BarCodeReader class and use the Read() method to recognize barcodes in an image.

When we already know the symbology type that will be passed to the reader, this is the most efficient way to write the program. Recognition speeds up considerably when the barcode type is known.

|![todo:image_alt_text](http://i.imgur.com/h5sWyXL.jpg)|
| :- |
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-basic_features-SpecificBarcodeSymbology-SpecificBarcodeSymbology.java" >}}

|![todo:image_alt_text](http://i.imgur.com/SBzZiCy.jpg)|
| :- |
|**Figure: Scan result**|
For unknown barcode types, either call the Read() method with no arguments or programmatically loop through every symbology. This slows down recognition.

**Java**

{{< highlight csharp >}}

 reader.read()

{{< /highlight >}}
#### **BarCodeReadType**
The BarCodeReader class' getReadType() method returns the symbology type of the recognized barcode. Continuing the sample above, the first recognized barcode's SymbologyType is:

**Java**

{{< highlight csharp >}}

 BarCodeReadType symbologyType = reader.getReadType();

{{< /highlight >}}

For barcode symbologies with variations, it's original SymbologyType or superset SymbologyType is returned. For example, both Code39Standard and Code39Extended barcodes are recognized as Code39Extended barcode.
#### **Code Text**
The BarCodeReader class' GetCodeText() method returns a string representing the barcode's decoded data.

**Java**

{{< highlight csharp >}}

 String strCodeText = reader.getCodeText();

{{< /highlight >}}
## **Recognizing Multiple Symbologies in Single Image**
There might be situations when there is more than one barcode in an image. Aspose.BarCode can easily recognize all the barcodes of supported symbology types. This can be done by specifying the symbology type or setting Symbology.AllSupportedTypes.

The image below contains two barcodes of the Code39Standard and Pdf417 types.

**Two barcodes, one image**

|![todo:image_alt_text](http://i.imgur.com/5prpHFS.png)|
| :- |
Since the BarCodeReader.read() method returns a Boolean value, it is possible to call it in a while loop to recognize all the barcodes in an image. For the above image, the read() method returns true for the first barcode, and then again for the second barcode. It returns false in the third iteration.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-basic_features-RecognizingMultipleSymbologiesInSingleImage-RecognizingMultipleSymbologiesInSingleImage.java" >}}

The above code snippet assumes that we already know the symbology types of the barcodes in the image and specified the symbologies. If the symbologies are not known in advance, use BarCodeReadType.AllSupportedTypes to check for any symbology type.

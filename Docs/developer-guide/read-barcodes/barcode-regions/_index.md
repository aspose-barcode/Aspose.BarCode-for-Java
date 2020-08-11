---
title: Barcode Regions
type: docs
weight: 40
url: /java/barcode-regions/
---

## **Getting Barcode Region Information from the Image**
This article shows how to read an image and get the regions for all recognized barcodes in the image.

The barcode region is the part of the image that only contains the barcode itself. In a large image, there may be text or other images as well as the barcode. Getting the barcode region separates the barcode from other elements in the image by detecting their edges.

To find barcode regions:

1. Read the barcodes in the image using the BarCodeReader.read() method.
1. Get the barcode region using the BarCodeReader.getRegion() method which returns an instance of type BarCodeRegion.
1. Get the barcode's x and y coordinates using the BarCodeRegion.Points() method.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-BarcodeRegionInformationFromTheImage-BarcodeRegionInformationFromTheImage.java" >}}

|![todo:image_alt_text](http://i.imgur.com/kA5n3ex.jpg)|
| :- |
|**Figure: Input image**|
After getting the region information using the above program, we get the following output.

{{< highlight csharp >}}

 Top left coordinates: X = 16, Y = 17

Bottom left coordinates: X = 174, Y = 17

Bottom right coordinates: X = 174, Y = 72

Top right coordinates: X = 16, Y = 72

Codetext: TEST-123

{{< /highlight >}}
## **Marking Barcode Regions in an Image**
This article shows how to read an image and mark the barcode regions for all recognized barcodes in the image. The barcode region is the part of the image that only contains the barcode itself. In a large image, there may be other texts or images as well as the barcode. Getting the barcode region separates the barcodes from other elements in the image by marking their edges or by filling them with a color.

To mark a barcode region:

1. Read the barcodes in the image using the BarCodeReader.read() method.
1. Get the region of the barcode using the BarCodeReader.getRegion() method, which returns an instance of the BarCodeRegion type.
1. Draw edges around the barcode using the BarCodeRegion.drawBarCodeEdges() method.

|![todo:image_alt_text](http://i.imgur.com/zrei3p8.png)|
| :- |
|**Figure: The image used for barcode recognition**|
After marking the barcode regions and drawing the edges of the barcodes, we get the following image.

|![todo:image_alt_text](http://i.imgur.com/qy4666y.png)|
| :- |
|**Figure: Output image**|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-MarkingBarcodeRegionsInAnImage-MarkingBarcodeRegionsInAnImage.java" >}}
## **Read Barcode from Specific Region of Image**
This article explains how to scan only a part of an image that contains a barcode. Suppose that we have a large image, for example, 800x600 pixels, and our barcode is in the top left corner of the image at 100 x 50 pixels. We can optimize the barcode scanning by specifying the area, instead of scanning the whole image. But we need to know in advance where the barcode will be located. The barcode reader will not scan any other parts of the image if we specify the area we want it to read.

The following image is 333x127 pixels. The size of the barcode is only 94x18 pixels and it is located on the top right corner of the image. The rest of the image contains some text in this example. But in your case, it might contain any other text and image.

|![todo:image_alt_text](http://i.imgur.com/FtNOSW2.png)|
| :- |
|**Figure: Input image**|
If we specify an area of (0, 0, 100, 50), the barcode reader will scan only this area and will be able to recognize the barcode. Doing so greatly increases the speed of recognition because the barcode reader will not look for barcodes in the rest of the image.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-ReadBarcodeFromSpecificRegionOfImage-ReadBarcodeFromSpecificRegionOfImage.java" >}}

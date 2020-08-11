---
title: Advanced Barcode Recognition Features
type: docs
weight: 30
url: /java/advanced-barcode-recognition-features/
---

## **Barcode Orientation**
Sometimes, the barcode in an image might be at an angle, for example at 90 degrees, as shown below. In this case, the barcode reader tries to detect the barcode in all possible directions. This means that it takes longer to detect the barcode.

|![todo:image_alt_text](http://i.imgur.com/CPeAhb8.jpg)|
| :- |
|**Figure: A rotated barcode**|
If you know the angle of the barcode in advance, set orientation in the BarCodeReader class to reduce the recognition time.

The barcode above is at a 90 degree angle and has an orientation of Rotate90. The orientation is set using BarCodeReader.setOrientationHints() method and passing Orientation as an argument.
### **Supported Orientation Hints**
Aspose.BarCode for Java supports the following orientation hints:

- NoRotate – the barcode is not rotated.
- Rotate90 – the barcode is rotated 90 degrees.
- Rotate180 – the barcode is rotated 180 degrees (it is, in effect, upside down).
- Rotate270 – the barcode is rotated 270 degrees.

Below is a code sample that recognizes the barcode in the image above.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-BarcodeOrientation-BarcodeOrientation.java" >}}
## **Better and Faster Image Processing for Barcode Recognition**
Aspose.BarCode for Java provides better and faster barcode recognition using the following image processing algorithms:

- **Complex background** 
  ImageBinarization.ComplexBackground
  This technique can locate a barcode region in the complex or colored background through region-based image analysis.
- **Invert image** 
  ImageBinarization.InvertImage
  This technique inverts the image before recognition. It helps the code to recognize images with a black background and white, or near, barcode.
- **Line Codes Restoration Filter** 
  ImageBinarization.LineCodesRestoration
  This filter restores corrupted strokes for 1D barcodes. This degradation could come from dirty, dusty products or discarded barcode resulting in touched or missing parts of bars.
- **Median smoothing image processing** 
  ImageBinarization.MedianSmoothing
  Median smoothing removes the noise from the image while preserving the image edges. This technique gives a perfect result. In more complicated images, less data is lost by taking the median.
  - setMedianSmoothingWindowSize: Sets the median smoothing window size. Typical values are 3 or 4. The default value is 3. The recognition hint MedianSmoothing must be set. For noisy images, 4 is good value.

The following code snippet shows how to use median smoothing processing technique when recognizing a barcode.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-FasterImageProcessingForBarcodeRecognition-FasterImageProcessingForBarcodeRecognition.java" >}}
## **Get BarCode Recognition Quality in Percent**
The BarCodeReader.getRecognitionQuality method gets the recognition quality in percentage. It works for only 1D and postal barcodes.

The code sample below shows how to get recognition quality.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-GetBarCodeRecognitionQualityInPercent-GetBarCodeRecognitionQualityInPercent.java" >}}
## **Recognizing Single Wiped Bars in Pattern**
Aspose.BarCode provides public property AllowOneDWipedBarsRestoration to the QualitySettings which allows to recognize barcodes with single wiped/glued bars in pattern. This property is enabled by default in HighQuality, MaxBarCodes modes. Currently this property can be used for Code128, GS1Code128, SCC14, EAN14, SSCC18, AustralianPosteParcel and SwissPostParcel barcode types.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-SingleWipedBarsInPattern-SingleWipedBarsInPattern.java" >}}
## **Read Multiple Macropdf417 Barcode Images**
[Generate Multiple MacroPdf417 Barcodes for Large or Multiple Code Text Values](http://www.aspose.com/docs/display/barcodejava/Generate+Multiple+MacroPdf417+Barcodes+for+Large+or+Multiple+Code+Text+Values) showed how to generate multiple Macropdf417 barcodes for multiple code text values. This article shows how to create a program that recognizes barcodes generated in that article.

Barcode images are placed in a folder and are recognized with the BarCodeReader class. The BarCodeReader class returns the segment ID, file ID and last segment flag (which is either true or false) for each for the MacroPdf417 barcodes.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-ReadMultipleMacropdf417BarcodeImages-ReadMultipleMacropdf417BarcodeImages.java" >}}
## **Get all Possible 1D Barcodes from an Image**
The BarCodeReader.getAllPossibleBarCodes method is used only for 1D barcodes. It returns an array of all possible barcodes found in an image. This array holds all information about the barcodes like code text, symbology, recognition percentage and region details.

The code sample below shows how to use the getAllPossibleBarCodes method.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-GetAllPossible1DBarcodesFromAnImage-GetAllPossible1DBarcodesFromAnImage.java" >}}
## **Generate Multiple Barcodes on a Single Image**
Aspose.BarCode for Java can create multiple optimized barcodes on a single image. It also supports generating multiple types of barcodes such as postal, QR, PDF417, EAN, Code 39, Code128, ISBN, MSI, GS1 etc.

The sample code below creates one image that contains several barcodes.

|![todo:image_alt_text](http://i.imgur.com/lwY6Qwg.png)|
| :- |
|**Figure: A sample image showing multiple barcodes**|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-GenerateMultipleBarcodesOnASingleImage-GenerateMultipleBarcodesOnASingleImage.java" >}}

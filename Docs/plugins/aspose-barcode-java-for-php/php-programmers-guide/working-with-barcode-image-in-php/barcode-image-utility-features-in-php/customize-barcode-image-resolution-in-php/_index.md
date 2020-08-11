---
title: Customize Barcode Image Resolution in PHP
type: docs
weight: 10
url: /java/customize-barcode-image-resolution-in-php/
---

## **Aspose.BarCode - Customize Barcode Image Resolution**
To Customize Barcode Image Resolution using **Aspose.Barcode Java for PHP**, simply invoke **CustomizeBarcodeImageResolution** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$bb = new BarCodeBuilder();

\# Set the Code text for the barcode

$bb->setCodeText("1234567");

\# Set the symbology type to Code128

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code128);

\# Create an instance of resolution

$resolutionMode=new ResolutionMode();

$bb->setResolution(new Resolution(200,400,$resolutionMode->Graphics));

$bb->save($dataDir . "CustomizeBarcodeImageResolution.jpg");

\# Display Status.

print "Customized Barcode Image Resolution, please check the output file.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Customize Barcode Image Resolution (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/CustomizeBarcodeImageResolution.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/CustomizeBarcodeImageResolution.php)

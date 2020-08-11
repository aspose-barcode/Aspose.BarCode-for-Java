---
title: Set Size Unit for the Barcode Image in PHP
type: docs
weight: 40
url: /java/set-size-unit-for-the-barcode-image-in-php/
---

## **Aspose.BarCode - Set Size Unit for the Barcode Image**
To Set Size Unit for the Barcode Image using **Aspose.Barcode Java for PHP**, simply invoke **SetBarcodeImageUnitSize** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$symbology = new Symbology();

$bb = new BarCodeBuilder("12345678", $symbology->Code128);

\# Measurement is Milimeter

$graphicsUnit=new GraphicsUnit();

$bb->setGraphicsUnit($graphicsUnit->Point);

\# Set the bar height to 3 points

$bb->setBarHeight(3.0);

$bb->save($dataDir . "SetBarcodeImageUnitSize.jpg");

\# Display Status.

print "Set Barcode Image Unit Size, please check the output file.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Set Size Unit for the Barcode Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/SetBarcodeImageUnitSize.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/SetBarcodeImageUnitSize.php)

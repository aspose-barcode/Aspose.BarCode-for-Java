---
title: Set Height of the Bars in the Barcode Image in PHP
type: docs
weight: 40
url: /java/set-height-of-the-bars-in-the-barcode-image-in-php/
---

## **Aspose.BarCode - Set Height of the Bars**
To Set Height of the Bars using **Aspose.Barcode Java for PHP**, simply invoke **SetBarsHeight** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 $bb = new BarCodeBuilder();

\# Set up code text (data to be encoded)

$bb->setCodeText("1234567");

\# Set the symbology type to Code128

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code128);

\# Set the bar height to be 3 milimeter

$bb->setBarHeight(3.0);

$bb->save($dataDir . "barcode3.jpg");

\# Set the bar height to be 7 milimeter

$bb->setBarHeight(7.0);

$bb->save($dataDir . "barcode7.jpg");

\# Set the bar height to be 11 milimeter

$bb->setBarHeight(11.0);

$bb->save($dataDir . "barcode11.jpg");

\# Display Status.

print "Barcode with different heights has been created successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Set Height of the Bars (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/SetBarsHeight.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/SetBarsHeight.php)

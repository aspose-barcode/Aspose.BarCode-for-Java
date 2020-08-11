---
title: Rotate Barcode Image in PHP
type: docs
weight: 40
url: /java/rotate-barcode-image-in-php/
---

## **Aspose.BarCode - Rotate Barcode Image**
To Rotate Barcode Image using **Aspose.Barcode Java for PHP**, simply invoke **RotateImage** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$bb = new BarCodeBuilder();

\# Set the code text of the barcode

$bb->setCodeText("12345678");

\# Roate clockwise for 180 degree (upside down)

$bb->setRotationAngleF(180);

\# Set the symbology type to code39

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code39Extended);

$bb->save($dataDir . "Rotate.jpg");

\# Display Status.

print "Done with image rotation, please check the output file.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Rotate Barcode Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/RotateImage.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/RotateImage.php)

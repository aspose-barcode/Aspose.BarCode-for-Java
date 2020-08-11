---
title: Set Aspect Ratio of Barcodes in PHP
type: docs
weight: 30
url: /java/set-aspect-ratio-of-barcodes-in-php/
---

## **Aspose.BarCode - Set Aspect Ratio of Barcodes**
To Set Aspect Ratio of Barcodes using **Aspose.Barcode Java for PHP**, simply invoke **SetAspectRatio** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$builder = new BarCodeBuilder();

$symbology=new Symbology();

$builder->setSymbologyType($symbology->Pdf417);

$builder->setCodeText("1234567890");

\# Set Aspect Ratio to 3:2 or 1.5

$builder->setAspectRatio(1.5);

\# Save the image

$builder->save($dataDir . "SetAspectRatio.jpg");

\# Display Status

print "Set Aspect Ratio Successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Set Aspect Ratio of Barcodes (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWith2DBarcodes/Utility2DBarcodeFeatures/SetAspectRatio.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWith2DBarcodes/Utility2DBarcodeFeatures/SetAspectRatio.php)

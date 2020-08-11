---
title: Colorize any Part of the Barcode Image in PHP
type: docs
weight: 10
url: /java/colorize-any-part-of-the-barcode-image-in-php/
---

## **Aspose.BarCode - Colorize any Part of the Barcode Image**
To Colorize any Part of the Barcode Image using **Aspose.Barcode Java for PHP**, simply invoke **SetBarcodeImageColor** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$bb = new BarCodeBuilder();

$color = new Color();

\# Set the background color of the barcode

$bb->setBackColor($color->YELLOW);

\# Set the foreground color of the barcode

$bb->setForeColor($color->BLUE);

\# Set border's color

$bb->setBorderColor($color->RED);

\# Set the code text's color

$bb->setCodeTextColor($color->RED);

\# Caption's color

$bb->getCaptionAbove()->setForeColor($color->darkGray);

$bb->getCaptionBelow()->setForeColor($color->CYAN);

$bb->save($dataDir . "color.jpg");

\# Display Status.

print "Applied color to barcode image, please check the output file.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Colorize any Part of the Barcode Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageColor.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageColor.php)

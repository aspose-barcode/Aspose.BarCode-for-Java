---
title: Working with Image Borders in PHP
type: docs
weight: 60
url: /java/working-with-image-borders-in-php/
---

## **Aspose.BarCode - Working with Image Borders**
To Work with Image Borders using **Aspose.Barcode Java for PHP**, simply invoke **SetBarcodeImageBorders** module. Here you can see example code.

**PHP Code**

{{< highlight php >}}

 # Instantiate barcode object

$bb = new BarCodeBuilder();

$margins = new MarginsF();

\# 6 milimeter space from left, right, top and bottom side of border

$margins->setLeft(2);

$margins->setRight(2);

$margins->setTop(2);

$margins->setBottom(2);

\# Set border's width to be 0.5 milimeter

$bb->setBorderWidth(0.5);

\# Border will be visible

$bb->setBorderVisible(true);

\# Set the border's color to red

$color=new Color();

$bb->setBorderColor($color->RED);

\# Set margins

$bb->setMargins($margins);

$bb->save($dataDir . "border.jpg");

\# Display Status.

print "Set border margins, please check the output file.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Working with Image Borders (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageBorders.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageBorders.php)

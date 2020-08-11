---
title: Creating a QR Barcode in PHP
type: docs
weight: 40
url: /java/creating-a-qr-barcode-in-php/
---

## **Aspose.BarCode - Creating a QR Barcode**
To Create a QR Barcode using **Aspose.Barcode Java for PHP**, simply invoke **CreatingQRBarcode** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$builder = new BarCodeBuilder();

$symbology=new Symbology();

$builder->setSymbologyType($symbology->QR);

$builder->setCodeText("1234567890");

\# Hide code text

$codeLocation=new CodeLocation();

$builder->setCodeLocation($codeLocation->None);

$builder->setRotationAngleF(90);

\# Save the image to your system and set its image format to Jpeg

$builder->save($dataDir . "CreatingQRBarcode.jpg");

\# Display Status

print "Created QR Barcode Successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Creating a QR Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingQRBarcode.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingQRBarcode.php)

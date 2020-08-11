---
title: Creating a DataMatrix Barcode
type: docs
weight: 10
url: /java/creating-a-datamatrix-barcode/
---

## **Aspose.BarCode - Creating a DataMatrix Barcode**
To Create a DataMatrix Barcode using **Aspose.Barcode Java for PHP**, simply invoke **CreatingDataMatrixBarcode** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$builder = new BarCodeBuilder();

$symbology=new Symbology();

$builder->setSymbologyType($symbology->DataMatrix);

$builder->setCodeText("1234567890");

\# Save the image to your system and set its image format to Jpeg

$builder->save($dataDir . "CreatingDataMatrixBarcode.jpg");

\# Display Status

print "Created DataMatrix Barcode Successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Creating a DataMatrix Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingDataMatrixBarcode.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingDataMatrixBarcode.php)

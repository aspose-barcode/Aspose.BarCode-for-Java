---
title: Set Code Text for Barcode in PHP
type: docs
weight: 30
url: /java/set-code-text-for-barcode-in-php/
---

## **Aspose.BarCode - Set Code Text for Barcode**
To Set Code Text for Barcode using **Aspose.Barcode Java for PHP**, call **set_codetext** method of **CodeText** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 public static function set_codetext($dataDir=null){

\# Instantiate barcode object

$bb = new BarCodeBuilder();

\# Set the code text for the barcode

$bb->setCodeText("Aspose-123");

\# Set the symbology type to Code128

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code128);

\# Set the width of the bars to 0.5 millimeter

$bb->setxDimension(0.5);

\# save the barcode image to file

$bb->save($dataDir . "codetext.out.jpg");

\# Print message

print "Barcode image generated successfully.".PHP_EOL;

}

{{< /highlight >}}
## **Download Running Code**
Download **Set Code Text for Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/UtilityFeatures/CodeText.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/UtilityFeatures/CodeText.php)

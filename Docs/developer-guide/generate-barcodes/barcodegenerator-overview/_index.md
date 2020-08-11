---
title: BarcodeGenerator Overview
type: docs
weight: 20
url: /java/barcodegenerator-overview/
---

## **Generating Barcode using BarcodeGenerator API**
Aspose.Barcode API lets you generate barcodes keeping in view real use cases like document printing and UI design. The Aspose.BarCode.Generation.BarCodeGenerator class is simple, yet powerful enough to work with Barcode generations. With this enhanced API as compared to the previous version of API i.e. BarcodeBuilder, you can generate and work with barcodes in more flexible ways. This article illustrates the usage of the API for working with Barcodes.
### **Generating Barcode with Restricted Barcode Size**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-GenerateBarcodeWithRestrictedBarcodeSize-GenerateBarcodeWithRestrictedBarcodeSize.java" >}}
### **Generating Barcode without Restricted Barcode Size**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-GenerateBarcodeWithoutRestrictedBarcodeSize-GenerateBarcodeWithoutRestrictedBarcodeSize.java" >}}
### **Get Generated Barcode Size**
if you just want to know the barcode size without saving the image you can call method RecalculateValues() and get barcode size.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-GetGeneratedBarcodeSize-GetGeneratedBarcodeSize.java" >}}
### **Units based Barcode Generation**
All measurement values can get and set in pixels, millimeters, inches at the same time. You can easily change the resolution of the image and prepare the same barcode for printing, LCD screens or retina-displays.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-UnitsBasedBarcodeGeneration-UnitsBasedBarcodeGeneration.java" >}}
### **Grouping Properties by Barcode Type**
Properties for specific barcode types are grouped. Such specific properties like QR_EncodeType of DataMatrix_Ecc are grouped by type to simplify all the API for new users.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-GroupingPropertiesByBarcodeType-GroupingPropertiesByBarcodeType.java" >}}
### **Getting Default Text for Generated Barcode**
BarCodeGenearator has predefined default CodeText, so you can just explore the default CodeText format for the specific type.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-GettingDefaultCodeTextForGeneratedBarcode-GettingDefaultCodeTextForGeneratedBarcode.java" >}}
### **Generate GS1DataMatrix Barcode with wrapping text**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-GS1DatamatrixBarcodeWithWrappingText-GS1DatamatrixBarcodeWithWrappingText.java" >}}
### **Generate Code16K Barcode**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-GenerateCode16KBarCode-GenerateCode16KBarCode.java" >}}
### **Generate DotCode Barcode**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-ImplementDotCodeForBarcode-ImplementDotCodeForBarcode.java" >}}
### **Generate Barcode with Auto Size Interpolation**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-ImplementInterpolationAutoSizemode-ImplementInterpolationAutoSizemode.java" >}}
### **Generate MaxiCode Barcode**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-ImplementMaxiCodeForBarcode-ImplementMaxiCodeForBarcode.java" >}}
### **Generate UpcaGs1DatabarCoupon Barcode**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-GenerateBarcode-ImplementUpcaGs1DatabarCoupon-ImplementUpcaGs1DatabarCoupon.java" >}}

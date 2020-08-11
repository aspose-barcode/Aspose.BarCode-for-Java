---
title: Optimize Scan
type: docs
weight: 20
url: /java/optimize-scan/
---

## **Optimized Scan**
{{% alert color="primary" %}} 

There are two ways to address recognition optimization; the first optimizes accuracy, the second speed.

{{% /alert %}} 
### **Pinpointing Barcode**
- SymbologyType setting. If the barcode type is unknown, try not to pass the symbology type (BarCodeReadType) to the constructor. Instead, recognize separately.
- The BarCodeReader class' internal optimization schema sacrifices accuracy for speed if the symbology type is not specified in the constructor.
### **Speed Up**
- Reduce image size. Larger images will lead to longer processing time.
## **Switch Barcode Recognition Modes According to the Requirement**
Aspose.BarCode for Java provides the recognition modes that can help the developers to quickly set up and tune the processing speed and quality in a way which is the most appropriate for the application needs.

- **Hight Performance** 
  QualitySettings.HighPerformance
  HighPerformance recognition quality preset. High-quality barcodes are recognized well in this mode.
- **Hight Quality** 
  QualitySettings.HighQuality
  HighQuality recognition quality preset. This preset is developed for low-quality barcodes.
- **Max Barcodes** 
  QualitySettings.MaxBarCodes
  MaxBarCodes recognition quality preset. This preset is developed to recognize all possible barcodes, even incorrect barcodes.
- **Normal Quality** 
  QualitySettings.NormalQuality
  Normal quality recognition quality preset. Suitable for most of the barcodes.
### **Quality Settings Example**
The following code snippet shows how to switch the barcode recognition modes:
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-SwitchBarcodeRecognitionModes-SwitchBarcodeRecognitionModes.java" >}}
## **Using Manual Hints to Optimize Scan**
Below is a code sample that turns off rotation algorithms and increases the recognition speed.

Note: It is available only for Datamatrix and the linear barcodes.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-Src-main-java-com-aspose-barcode-examples-barcode_recognition-advanced_features-UsingManualHintsToOptimizeScan-UsingManualHintsToOptimizeScan.java" >}}

---
title: Public API Changes in Aspose.BarCode 6.0.0
type: docs
weight: 20
url: /java/public-api-changes-in-aspose-barcode-6-0-0/
---

{{% alert color="primary" %}} 

This document describes changes to the Aspose.BarCode API from version 5.8.0 to 6.0.0, that may be of interest to module/application developers. It includes not only new and updated public methods, but also a description of any changes in the behavior behind the scenes in Aspose.BarCode. 

{{% /alert %}} 
### **The BarCodeReader.getAllPossibleBarCodes method is added**
Users can now retrieve all possible 1D barcodes using a single method call. The BarCodeReader.getAllPossibleBarCodes method allows them to do so. Please find the details of this feature in the following help topic: [Get all Possible 1D Barcodes from an Image](http://www.aspose.com/docs/display/barcodejava/Get+all+Possible+1D+Barcodes+from+an+Image).
### **The BarCodeReader.getRecognitionQuality method is added**
Barcode recognition percentage gives a clear picture based on the quality and performance parameters. The BarCodeReader.getRecognitionQuality method helps with that. Please find the details of this feature in the following help topic: [Get BarCode Recognition Quality in Percent](http://www.aspose.com/docs/display/barcodejava/Get+BarCode+Recognition+Quality+in+Percent)
### **The BarCodeBuilder.setThrowExceptionWhenCodeTextIncorrect method is added**
If the code text is incorrect and the value is set to true, an exception is thrown; else, the code text is corrected to match the barcode’s specification. An exception is always thrown for Databar symbologies if the code text is incorrect. It is never thrown for AustraliaPost, SingapurePost, Code39Extended, Code93Extended, Code16K, and Code128 symbologies if codetext is incorrect. Please see the following topic for help: [1D Barcodes with Optional Exception Message in Case of Wrong Code Text](http://www.aspose.com/docs/display/barcodejava/1D+Barcodes+with+Optional+Exception+Message+in+Case+of+Wrong+Code+Text)
### **The BarCodeBuilder.saveAsEmf methods have been removed**
The functionality was not implemented in earlier barcode versions and the both methods have been removed now since earlier methods threw the exception NotImplementedException.
### **The Code128CodeBuilder and Code128CodeSet classes have been removed**
Now all codesets set and switched automatically according to code 128 specification. No need for code 128 builder to be public anymore. Additionally, all references to this class have been removed too: IBarCodeControl.getCode128CodeSet(), IBarCodeControl.setCode128CodeSet(Code128CodeSet), BarCodeBuilder.getCode128CodeSet(), BarCodeBuilder.setCode128CodeSet(int).
### **The BinarizedBitmap class has been removed**
This class was obsolete and is not useful anymore. Since we have refactored the BarCode engine there is no need to use it directly and this class has been removed from the public API. Additionally, all the methods expecting BinarizedBitmap have been removed from the public API.
### **The field RecognitionHints.ImageBinarization.HLS has been removed**
We have greatly refactored the BarCode engine and now we have found that HLS filters work the same way as GrayNormalization filter do. Previously HLS filter has been used for binarization but currently we use some other binarization algorithms. The following code:

**Java**

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader(image, BarCodeReadType.Code39Standard);

reader.setImageBinarizationHints(RecognitionHints.ImageBinarization.HLS);

{{< /highlight >}}

May be safely refactored to this with the new BarCode engine:

**Java**

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader(image, BarCodeReadType.Code39Standard);

{{< /highlight >}}

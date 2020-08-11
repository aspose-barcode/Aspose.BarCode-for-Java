---
title: Aspose.BarCode for Java 7.9.0 Release Notes
type: docs
weight: 70
url: /java/aspose-barcode-for-java-7-9-0-release-notes/
---

Aspose.BarCode for Java has been updated to version 7.9.0 and we are pleased to announce it. The following is a list of changes in this version of Aspose.BarCode.
### **Features and Improvements**

|**Key** |**Summary** |**Category** |
| :- | :- | :- |
|BARCODENET-34430 |Implement new algorithm to recognize 3D-distorted Aztec codes |New Feature |
|BARCODENET-34421 |Generate data matrix barcode with text on its right side |New Feature |
|BARCODENET-34433 |Not able to recognize rectangular Aztec codes |Enhancement |
|BARCODENET-34422 |Verify APTCACheck for Aspose.Barcode DLL |Enhancement |
|BARCODENET-34410 |Unable to Read the QR barcode successfully from a PDF file |Enhancement |
|BARCODENET-34398 |Unable to Read the QR barcode successfully from a TIFF image |Enhancement |
|BARCODENET-34390 |Unable to read QR Code barcode from a BMP image |Enhancement |
### **Public API and Backward Incompatible Changes**
###### **BARCODENET-34421 Generate data matrix barcode with text on its right side** 
***1)*** New Code location value **CodeLocation.Right** has been added to the enumeration: ***CodeLocation;***

***2)*** New methods **getDisplay2DText** and **setDisplay2DText** have been added to the **BarCodeBuilder** class;

Code sample

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder(

"GTIN:898978777776665655

  UID: 121212121212121212

  Batch:GH768

  Exp.Date:150923", Symbology.DataMatrix);

builder.setCodeLocation(CodeLocation.Right);

builder.setMargins(new MarginsF(0,0,0,0));

//builder.setCodeTextSpace(0); // not recommended small space

builder.save("codetextRight.png");


{{< /highlight >}}

In order to avoid including all the names into the codetext, the property called "*Display2DText*" should be used.
See the sample below.

Code sample:

{{< highlight xml >}}

 String gtin = "898978777776665655";

String uid = "121212121212121212";

String batch = "GH768";

String expDate = "150923";

String textToEncode = gtin + uid + batch + expDate; // or  "(01)"+ gtin + "(..)"+ uid + ...

String textToDisplay = "GTIN:" + gtin + "\nUID:" + uid + "\nBatch:" + batch + "\nExp.Date:" + expDate;

BarCodeBuilder builder = new BarCodeBuilder(textToEncode, Symbology.DataMatrix);

builder.setDisplay2DText(textToDisplay);

builder.setCodeLocation(CodeLocation.Right);

builder.setMargins(new MarginsF(0,0,0,0));

//builder.setCodeTextSpace(0); // not recommended small space

builder.save("codetextRightDisplay.png");

{{< /highlight >}}

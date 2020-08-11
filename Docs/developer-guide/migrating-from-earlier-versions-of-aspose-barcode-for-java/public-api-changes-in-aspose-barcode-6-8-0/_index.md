---
title: Public API Changes in Aspose.BarCode 6.8.0
type: docs
weight: 30
url: /java/public-api-changes-in-aspose-barcode-6-8-0/
---

{{% alert color="primary" %}} 

This document describes changes to the Aspose.BarCode API from version 6.5.0 to 6.8.0, that may be of interest to module/application developers. It includes not only new and updated public methods, but also a description of any changes in the behavior behind the scenes in Aspose.BarCode. 

{{% /alert %}} 
### **Method com.aspose.barcode.BarCodeBuilder.setRows() is updated**
Updated method com.aspose.barcode.BarCodeBuilder.setRows(). Now this method allows to specify number of rows for new symbology DatabarExpandedStacked. If setting is illegal then rows will be calculated automatically.

{{< highlight java >}}

 BarCodeBuilder builder = new BarCodeBuilder(

    "(01)90012345678908(3932)04066USD778899",

    Symbology.DatabarExpandedStacked);

builder.setRows(1);

builder.save("stacked1Rows.png");

{{< /highlight >}}
### **Field com.aspose.barcode.Symbology.DatabarExpandedStacked is added**
It specifies that the data should be encoded with Databar expanded stacked barcode specification.

{{< highlight java >}}

 // Initialize BarCodeBuilder class object

BarCodeBuilder builder = new BarCodeBuilder();

// Set code text

builder.setCodeText("(01)90012345678908(3932)04055GBP");

// Set symbology type

builder.setSymbologyType(Symbology.DatabarExpandedStacked);

// Save barcode image

builder.save("databarexpandedstacked.png");

{{< /highlight >}}
### **Field com.aspose.barcode.Symbology.DatabarStacked is added**
It specifies that the data should be encoded with Databar stacked barcode specification.

{{< highlight java >}}

 // Initialize BarCodeBuilder class object

BarCodeBuilder builder = new BarCodeBuilder();

// Set code text

builder.setCodeText("(01)00012345678905");

// Set symbology type

builder.setSymbologyType(Symbology.DatabarStacked);

// Save barcode image

builder.save("databarstacked.png");

{{< /highlight >}}
### **Field com.aspose.barcode.Symbology.DatabarStackedOmniDirectional is added**
It specifies that the data should be encoded with Databar stacked omni-directional barcode specification.

{{< highlight java >}}

 // Initialize BarCodeBuilder class object

BarCodeBuilder builder = new BarCodeBuilder();

// Set code text

builder.setCodeText("(01)00034567890125");

// Set symbology type

builder.setSymbologyType(Symbology.DatabarStackedOmniDirectional);

// Save barcode image

builder.save("databarstackedomnidirectional.png");

{{< /highlight >}}
### **Field com.aspose.barcoderecognition.BarCodeReadType.DatabarExpandedStacked is added**
Added support for Databar expanded stacked recognition.

{{< highlight java >}}

 // Initialize BarCodeReader class

BarCodeReader reader = new BarCodeReader("databarexpandedstacked.png", BarCodeReadType.DatabarExpandedStacked);

// scan barcode image		

if (reader.read())

{

    System.out.println(reader.getCodeText());

}

{{< /highlight >}}
### **Field com.aspose.barcoderecognition.BarCodeReadType.DatabarStacked is added**
Added support for Databar stacked recognition.

{{< highlight java >}}

 // Initialize BarCodeReader class

BarCodeReader reader = new BarCodeReader("databarstacked.png", BarCodeReadType.DatabarStacked);

// scan barcode image		

if (reader.read())

{

    System.out.println(reader.getCodeText());

}

{{< /highlight >}}
### **Field com.aspose.barcoderecognition.BarCodeReadType.DatabarStackedOmniDirectional is added**
Added support for Databar stacked omni-directional recognition.

{{< highlight java >}}

 // Initialize BarCodeReader class

BarCodeReader reader = new BarCodeReader("databarstackedomnidirectional.png", BarCodeReadType.DatabarStackedOmniDirectional);

// scan barcode image		

if (reader.read())

{

    System.out.println(reader.getCodeText());

}

{{< /highlight >}}
### **Field Symbology.PatchCode is added**
We’ve added encoding support of Patch code symbology, whereas decoding capability is already present.

{{< highlight java >}}

 BarCodeBuilder builder = new BarCodeBuilder("Patch T", Symbology.PatchCode);

builder.save("PatchCode.png");

{{< /highlight >}}
### **Filter ImageBinarization.LineCodesRestoration is added**
We’ve added a new Line Codes Restoration filter. This filter restores corrupted strokes for 1D barcodes. This degradation could come from dirty, dusty products or discarded barcode resulting in touched or missing parts of the bars.

{{< highlight java >}}

 // Create an instance of BarCodeReader and set image and symbology type to recognize

BarCodeReader reader = new BarCodeReader(@"c:\temp\MyBarcode.png", BarCodeReadType.Code39Standard);

// Set grayscale image processing

reader.setImageBinarizationHints(ImageBinarization.LineCodesRestoration);

// Try to recognize all possible barcodes in the image

while (reader.read())

{

    // Display the codetext

    System.out.println("Codetext: " + reader.getCodeText());

}

// Close the reader

reader.close();

{{< /highlight >}}
### **Filter ImageBinarization.Grayscale is removed**
We’ve removed GrayScale filter.

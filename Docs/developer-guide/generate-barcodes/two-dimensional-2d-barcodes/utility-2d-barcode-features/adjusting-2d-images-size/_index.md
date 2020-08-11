---
title: Adjusting 2D Images Size
type: docs
weight: 20
url: /java/adjusting-2d-images-size/
---

{{% alert color="primary" %}} 

The size of a barcode image depends on many factors. The following settings all affect the size of the overall image:

**Metrics**:

- [setResolution()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BaseGenerationParameters#setResolution-float-) – higher resolution will lead to a larger image on the same screen or printer.

**The overall image**:

- [setAutoSizeMode()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BaseGenerationParameters#getAutoSizeMode--) – sets the mode by which the barcode automatically resizes.
- [getBarHeight()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeParameters#getBarHeight--) – The height of 1D barcodes' bars in Unit value.
- [getBarCodeWidth()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BaseGenerationParameters#getImageWidth--) – only valid when AutoSizeMode property is set to AutoSizeMode.NEAREST.
- [getBarCodeHeight()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BaseGenerationParameters#getImageHeight--) – only valid when AutoSizeMode property is set to AutoSizeMode.NEAREST.
- [getPadding()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeParameters#getPadding--) – padding around the core BarCode image. 
- [getCaptionAbove()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BaseGenerationParameters#getCaptionAbove--) – controlled by setVisible(), setAlignment(), setText(), setColor(), getFont(), and getSpace().
- [getCaptionBelow()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BaseGenerationParameters#getCaptionBelow--) – controlled by setVisible(), setAlignment(), setText(), setColor(), getFont(), and getSpace().

**The core barcode**:

- [getxDimension()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeParameters#getXDimension--) – x-dimension is the smallest width of the unit of BarCode bars or spaces.
- [setWideNarrowRatio()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeParameters#setWideNarrowRatio-float-) – the ratio of wide bars to narrow bars or wide spaces to narrow spaces for some types of barcodes.
- [setCodeText()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator#setCodeText-java.lang.String-) – data to be encoded, different types of BarCode may have different CodeText length restrictions.
- [getCodeTextParameters()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeParameters#getCodeTextParameters--) – contains specific configuration properties for codetext of barcode like as setLocation(), setColor(), setAlignment(), getFont(), and getSpace().  

Each specific type of barcode may have different semantic demands, then it will override or ignore the above settings. For example, DataMatrix is a square based barcode. The AspectRatio setting is meaningless to DataMatrix because AspectRatio has to be 1 for square modules. [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) simply ignores illegal settings and decides on its own during the process.

{{% /alert %}}

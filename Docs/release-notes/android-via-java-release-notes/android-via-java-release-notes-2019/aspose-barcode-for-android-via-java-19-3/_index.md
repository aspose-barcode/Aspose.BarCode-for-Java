---
title: Aspose.BarCode for Android via Java 19.3
type: docs
weight: 100
url: /java/aspose-barcode-for-android-via-java-19-3/
---

{{% alert color="primary" %}} 

This page contains release notes information for Aspose.BarCode for Android via Java 19.3.

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODEANDROID-171|Develop BarCodeControl for Android application |Enhancement|
|BARCODEANDROID-199|Provide an opportunity to customize barcode size|Enhancement|
|BARCODEANDROID-212|Provide an opportunity to choose to generate from attributes or from entered parameters|Enhancement|
|BARCODEANDROID-216|Add properties and attributes for image offset|Enhancement|
|BARCODEANDROID-217|Add attributes for barcode size|Enhancement|
## **Usage examples:**


**BARCODEANDROID-171 - Develop BarCodeControl for Android application**

<com.aspose.barcode.component.BarCodeControl
`   `android:id="@+id/bar_code_control"
`   `android:layout_width="400dp"
`   `android:layout_height="300dp"/>

BarCodeControl barCodeControl = (BarCodeControl) findViewById(R.id.bar_code_control);
barCodeControl.setCodeText(chosenCodeText);
barCodeControl.setEncodeType(chosenEncodeType);
barCodeControl.setBarCodeImageWidth(chosenBarcodeWidth);
barCodeControl.setBarCodeImageHeight(chosenBarcodeHeight);
barCodeControl.setGraphicsUnit(chosenGraphicsUnit);
barCodeControl.setAutoSizeMode(chosenAutoSizeMode);
barCodeControl.setImageLeftOffset(chosenImageLeftOffset);
barCodeControl.setImageTopOffset(chosenImageTopOffset);
barCodeControl.invalidate();



**BARCODEANDROID-212 - Provide an opportunity to choose to generate from attributes or from entered parameters**

<com.aspose.barcode.component.BarCodeControl
` `android:id="@+id/bar_code_control"
` `android:layout_width="400dp"
` `android:layout_height="300dp"
` `android:background="@drawable/text_view_border_red"
` `app:codeText="55544555"
` `app:encodeType="UPCE"
` `app:barCodeImageWidth="150"
` `app:barCodeImageHeight="75"
` `app:graphicsUnit="points"
` `app:autoSizeMode="Interpolation"
` `app:imageLeftOffset="174"
` `app:imageTopOffset="76"/>



BarCodeControl barCodeControl = (BarCodeControl) findViewById(R.id.bar_code_control);
barCodeControl.setGenerateFromAttributes(true);
## **Public API and Backward Incompatible Changes**
Following members have been added:

- Class  com.aspose.barcode.component.BarCodeControl
- Method com.aspose.barcode.component.setCodeText(String):void
- Method com.aspose.barcode.component.getCodeText():String
- Method com.aspose.barcode.component.setEncodeType(String):void
- Method com.aspose.barcode.component.getEncodeType():String
- Method com.aspose.barcode.component.setGenerateFromAttributes(boolean):void
- Method com.aspose.barcode.component.isGenerateFromAttributes():boolean
- Method com.aspose.barcode.component.setBarCodeImageWidth(String):void
- Method com.aspose.barcode.component.getBarCodeImageWidth():String
- Method com.aspose.barcode.component.setBarCodeImageHeight(String):void
- Method com.aspose.barcode.component.getBarCodeImageHeight():String
- Method com.aspose.barcode.component.setGraphicsUnit(String):void
- Method com.aspose.barcode.component.getGraphicsUnit():String
- Method com.aspose.barcode.component.setImageLeftOffset(String):void
- Method com.aspose.barcode.component.getImageLeftOffset():String
- Method com.aspose.barcode.component.setImageTopOffset(String):void
- Method com.aspose.barcode.component.getImageTopOffset():String
- Method com.aspose.barcode.component.setAutoSizeMode(String):void
- Method com.aspose.barcode.component.getAutoSizeMode():String

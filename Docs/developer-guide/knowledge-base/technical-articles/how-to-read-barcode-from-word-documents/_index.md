---
title: How to Read Barcode from Word Documents
type: docs
weight: 10
url: /java/how-to-read-barcode-from-word-documents/
---

{{% alert color="primary" %}} 

This article explains how to use [Aspose.BarCode for Java](https://apireference.aspose.com/java/barcode/) to recognize barcodes from Microsoft Word documents.

{{% /alert %}} 
### **Reading Barcodes for Microsoft Word Documents**
for the purpose of this article, the task has been broken down into two separate tasks:

1. [Generate a Microsoft Word document](/barcode/java/how-to-read-barcode-from-word-documents-html/), complete with barcode.
1. [Read the barcode](/barcode/java/how-to-read-barcode-from-word-documents-html/) in the Word document.

The [code sample](/barcode/java/how-to-read-barcode-from-word-documents-html/) does both steps.
#### **Generate Barcode and Insert into Word Document**
1. First, use Aspose.BarCode for Java and generate a barcode image.
1. Save the image to file.
1. Use [Aspose.Words for Java](http://www.aspose.com/products/words) to create a Microsoft Word document and insert this barcode in that document.
#### **Extract Images from Word Document and Read Barcodes**
Recognition:

1. Extract images from the Microsoft Word document using [Aspose.Words for Java](http://www.aspose.com/products/words).
1. Pass the images to Aspose.BarCode for Java for recognition.

Below is a complete Java program that generates and recognizes barcodes from Microsoft Word documents.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-Src-main-java-com-aspose-barcode-examples-technical_articles-RecognitionFromWord-RecognitionFromWord.java" >}}

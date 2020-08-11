---
title: How to Read Barcode from PDF Documents
type: docs
weight: 20
url: /java/how-to-read-barcode-from-pdf-documents/
---

{{% alert color="primary" %}} 

This article shows how to use [Aspose.Pdf for Java](http://www.aspose.com/products/pdf) to recognize barcodes from PDF documents.

{{% /alert %}} 
### **Recognizing Barcodes from PDF Files**
The examples performs two tasks:

1. [Generates a PDf file](/barcode/java/how-to-read-barcode-from-pdf-documents-html/) with a barcode in it.
1. [Recognizes the barcode](/barcode/java/how-to-read-barcode-from-pdf-documents-html/) in the PDF.

The [code sample](/barcode/java/how-to-read-barcode-from-pdf-documents-html/) below does both tasks in one go.
#### **Generate Barcode and Insert in PDF Document**
1. Use Aspose.BarCode for Java to generate a barcode image.
1. save the image to disk.
1. Use [Aspose.Pdf for Java](http://www.aspose.com/community/files/72/java-components/aspose.pdf-for-java/default.aspx) to create a Adobe PDF document and insert this barcode into it.
#### **Extract Images from PDF Document and Read Barcodes**
1. Extract images from the PDF document using Aspose.Pdf.Kit for Java.
1. Pass the images to [Aspose.BarCode for Java](http://www.aspose.com/community/files/72/java-components/aspose.barcode-for-java/default.aspx) for barcode recognition.

Below is a complete Java program that generates and recognizes barcodes from Adobe PDF documents.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-technical_articles-RecognitionFromPDF-RecognitionFromPDF.java" >}}





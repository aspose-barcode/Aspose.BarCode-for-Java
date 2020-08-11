---
title: Read BarCode from Specific Region of Image in Ruby
type: docs
weight: 70
url: /java/read-barcode-from-specific-region-of-image-in-ruby/
---

## **Aspose.BarCode - Read Barcode from Specific Region of Image**
To Read Barcode from Specific Region of Image using **Aspose.Barcode Java for Ruby**, simply invoke **ReadBarcodeFromSpecificRegion** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

\# Open the stream. Read only access is enough for Aspose.BarCode to load an image.

stream = Rjb::import('java.io.FileInputStream').new(data_dir + "test.png")

\# Create an instance of BarCodeReader class

\# and specify an area to look for the barcode   

barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')

reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(stream, Rjb::import('java.awt.Rectangle').new(0, 0, 10, 50), barcode_reader_type.Code39Standard)

\# TRead all barcodes in the provided area

while reader.read() == true

    # Display the codetext and symbology type of the barcode found

    puts "Codetext: " + reader.getCodeText().to_s + " Symbology: " + reader.getReadType().to_s

end

\# Close reader

reader.close()

{{< /highlight >}}
## **Download Running Code**
Download **Read Barcode from Specific Region of Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeRecognition/readbarcodefromspecificregion.rb)

---
title: Introducing BarCode Recognition in Ruby
type: docs
weight: 10
url: /java/introducing-barcode-recognition-in-ruby/
---

## **Aspose.BarCode - Barcode Recognition**
To Recognize Barcode using **Aspose.Barcode Java for Ruby**, simply invoke **RecognizeBarcode** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

\# initialize barcode reader

img = data_dir + "barcode.jpg"

barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')

rd = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

\# read barcode

while rd.read()

    # print the code text, if barcode found

    puts "CodeText: " + rd.getCodeText().to_s



    # print the symbology type

    puts "Type: " + rd.getReadType().to_s

end

{{< /highlight >}}
## **Download Running Code**
Download **Barcode Recognition (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeRecognition/recognizebarcode.rb)

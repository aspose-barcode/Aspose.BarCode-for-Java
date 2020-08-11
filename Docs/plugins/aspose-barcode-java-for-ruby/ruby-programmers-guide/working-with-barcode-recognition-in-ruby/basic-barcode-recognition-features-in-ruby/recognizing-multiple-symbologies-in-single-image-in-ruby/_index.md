---
title: Recognizing Multiple Symbologies in Single Image in Ruby
type: docs
weight: 20
url: /java/recognizing-multiple-symbologies-in-single-image-in-ruby/
---

## **Aspose.BarCode - Recognizing Multiple Symbologies in Single Image**
To Recognize Multiple Symbologies in Single Image using **Aspose.Barcode Java for Ruby**, simply invoke **RecognizeMultipleSymbologies** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

\# initialize barcode reader

img = data_dir + "symbologies.png"

barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')

rd = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.AllSupportedTypes)

\# read barcode

while rd.read()

    # print the code text, if barcode found

    puts "CodeText: " + rd.getCodeText().to_s



    # print the symbology type

    puts "Type: " + rd.getReadType().to_s

end

{{< /highlight >}}
## **Download Running Code**
Download **Recognizing Multiple Symbologies in Single Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeRecognition/recognizemultiplesymbologies.rb)

---
title: Get BarCode Recognition Quality in Percent in Ruby
type: docs
weight: 40
url: /java/get-barcode-recognition-quality-in-percent-in-ruby/
---

## **Aspose.BarCode - Get BarCode Recognition Quality in Percent**
To Get BarCode Recognition Quality in Percent using **Aspose.Barcode Java for Ruby**, simply invoke **GetBarcodeRecognitionQuality** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

img = data_dir + "barcode.jpg"

\# initialize barcode reader

barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')

reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

\# Call read method

while reader.read()

    puts "Barcode CodeText: " + reader.getCodeText().to_s + " Barcode Type: " + reader.getReadType().to_s

    percent = reader.getRecognitionQuality()

    puts "Barcode Quality Percentage: " + percent.to_s

end

\# Close reader

reader.close()

{{< /highlight >}}
## **Download Running Code**
Download **Get BarCode Recognition Quality in Percent (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeRecognition/getbarcoderecognitionquality.rb)

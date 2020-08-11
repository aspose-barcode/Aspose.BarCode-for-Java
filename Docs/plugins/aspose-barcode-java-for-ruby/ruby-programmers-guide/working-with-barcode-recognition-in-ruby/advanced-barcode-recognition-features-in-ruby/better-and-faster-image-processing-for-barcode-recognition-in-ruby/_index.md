---
title: Better and Faster Image Processing for BarCode Recognition in Ruby
type: docs
weight: 20
url: /java/better-and-faster-image-processing-for-barcode-recognition-in-ruby/
---

## **Aspose.BarCode - Better and Faster Image Processing for Barcode Recognition**
To do Better and Faster Image Processing for Barcode Recognition using **Aspose.Barcode Java for Ruby**, simply invoke **FastAndBetterBarcodeProcessing** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

img = data_dir + "test.png"

\# initialize barcode reader

barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')

reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

\# Set recognition hints

reader.setImageBinarizationHints(Rjb::import('com.aspose.barcoderecognition.RecognitionHints$ImageBinarization').MedianSmoothing)

reader.setMedianSmoothingWindowSize(4)

\# Call read method

while reader.read()

    puts "Barcode CodeText: " + reader.getCodeText().to_s

end

\# Close reader

reader.close()

{{< /highlight >}}
## **Download Running Code**
Download **Better and Faster Image Processing for Barcode Recognition (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeRecognition/fastandbetterbarcodeprocessing.rb)

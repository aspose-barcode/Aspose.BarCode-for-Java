module Asposebarcodejava
  module SaveBarcodeImageToTtreams
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set symbology of the barcode
        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

        # Set code text
        builder.setCodeText("123456")

        # Initialize ByteArrayOutputStream object
        out_stream = Rjb::import('java.io.ByteArrayOutputStream').new

        # Save barcode image
        builder.save(out_stream, Rjb::import('com.aspose.barcode.BarCodeImageFormat').Jpeg)

        # Display Status.
        puts "Save Barcode Image to Streams, please check the output file."
    end
  end
end

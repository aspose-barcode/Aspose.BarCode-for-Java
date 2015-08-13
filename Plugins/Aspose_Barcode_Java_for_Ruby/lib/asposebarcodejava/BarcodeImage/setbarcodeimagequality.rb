module Asposebarcodejava
  module SetBarcodeImageQuality
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set the code text of the barcode
        bb.setCodeText("12345678")

        # Set the graphics drawing hint to be Anti Alias
        bb.setImageQuality(Rjb::import('com.aspose.barcode.ImageQualityMode').AntiAlias)

        # Set the symbology type to code39
        bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Extended)

        bb.save(data_dir + "SetBarcodeImageQuality.jpg")

        # Display Status.
        puts "Set Barcode Image Quality, please check the output file."
    end
  end
end

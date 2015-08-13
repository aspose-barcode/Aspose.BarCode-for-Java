module Asposebarcodejava
  module CustomizeBarcodeImageResolution
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set the Code text for the barcode
        bb.setCodeText("1234567")

        # Set the symbology type to Code128
        bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

        # Create an instance of resolution
        bb.setResolution(Rjb::import('com.aspose.barcode.Resolution').new(200,400,Rjb::import('com.aspose.barcode.ResolutionMode').Graphics))

        bb.save(data_dir + "CustomizeBarcodeImageResolution.jpg")

        # Display Status.
        puts "Customized Barcode Image Resolution, please check the output file."
    end
  end
end

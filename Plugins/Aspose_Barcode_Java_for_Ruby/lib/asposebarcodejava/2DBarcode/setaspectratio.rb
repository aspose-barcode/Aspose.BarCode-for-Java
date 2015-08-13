module Asposebarcodejava
  module SetAspectRatio
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Pdf417)

        builder.setCodeText("1234567890")
        
        # Set Aspect Ratio to 3:2 or 1.5
        builder.setAspectRatio(1.5)

        # Save the image
        builder.save(data_dir + "SetAspectRatio.jpg")

        # Display Status
        puts "Set Aspect Ratio Successfully."
    end
  end
end

module Asposebarcodejava
  module PatchCode
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set Symbology type
        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').PatchCode)
        
        # Set code text
        builder.setCodeText("Patch T")

        # Save the image to your system and set its image format to Jpeg
        builder.save(data_dir + "PatchCode.jpg")

        # Display Status
        puts "Generated PatchCode Successfully."
    end
  end
end

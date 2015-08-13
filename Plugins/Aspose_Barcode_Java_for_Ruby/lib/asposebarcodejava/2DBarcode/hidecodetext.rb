module Asposebarcodejava
  module HideCodeText
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').DataMatrix)

        builder.setCodeText("The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");
        
        builder.setCodeLocation(Rjb::import('com.aspose.barcode.CodeLocation').None)

        font = Rjb::import('java.awt.Font')
        builder.setCodeTextFont(font.new("Serif", font.BOLD + font.ITALIC, 20))

        # Save the image
        builder.save(data_dir + "HideCodeText.jpg")

        # Display Status
        puts "Hide Code Text Successfully."
    end
  end
end

module Asposebarcodejava
  module StartStopSymbol
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        # Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("$ 123:50", Rjb::import('com.aspose.barcode.Symbology').Codabar)
        
        # Set the codabar start symbol to A
        builder.setCodabarStartSymbol(Rjb::import('com.aspose.barcode.CodabarSymbol').A)
        
        # Set the codabar stop symbol to D
        builder.setCodabarStopSymbol(Rjb::import('com.aspose.barcode.CodabarSymbol').D)
        
        # Save the image to disk in PNG format
        builder.save(data_dir + "barcode.out.png")
        
        # Print message
        puts "Barcode image generated successfully."
    end
  end
end

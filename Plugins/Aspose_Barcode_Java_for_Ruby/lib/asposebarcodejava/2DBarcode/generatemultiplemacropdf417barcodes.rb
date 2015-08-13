module Asposebarcodejava
  module GenerateMultipleMacroPdf417Barcodes
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').MacroPdf417)

        # Create array for storing multiple barcodes
        i = 1
        size = 4
        list_code_text = ["code-1", "code-2", "code-3", "code-last"]
        str_file_id = 1
        
        # Check the listbox for getting codetext and generating the barcodes
        for i in 1..4
            builder.setCodeText(list_code_text[i - 1])
            # fileID should be same for all the generated barcodes
            builder.setMacroPdf417FileID(str_file_id)
            
            # Assign segmentID in increasing order (1,2,3,....)
            builder.setMacroPdf417SegmentID(i)
                
            # Save the barcode (fileid_segmentid.png)
            builder.save(data_dir + "#{i}.png")
        end
        # Display Status
        puts "Saved Images Successfully."
    end
  end
end

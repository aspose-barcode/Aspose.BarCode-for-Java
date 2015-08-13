# Aspose_Barcode_Java_for_Ruby
Aspose.Barcode Java for Ruby is a gem that demonstrates / provides the Aspose.Barcode for Java API usage examples in Ruby by using Rjb - Ruby Java Bridge.

## Installation

Execute following command.

    $ gem install asposebarcodejava

To download Aspose.BarCode for Java API to be used with these examples through RJB, Please navigate to:

http://www.aspose.com/community/files/72/java-components/aspose.barcode-for-java/

Note: Create jars folder at root of the gem folder and copy downloaded Aspose.BarCode for java component into it.

For most complete documentation of the project, check Aspose.BarCode Java for Ruby confluence wiki link:

http://www.aspose.com/docs/display/barcodejava/2.+Aspose.Barcode+Java+For+Ruby

## Usage

```ruby
require File.dirname(File.dirname(File.dirname(__FILE__))) + '/lib/asposebarcodejava'
include Asposebarcodejava
include Asposebarcodejava::RecognizeBarcode
initialize_aspose_barcode
```
Lets understand the above code
* The first line makes sure that the Aspose.BarCode is loaded and available 
* Include the files that are required to access the Aspose.BarCode
* Initialize the libraries. The aspose JAVA classes are loaded from the path provided in the aspose.yml file
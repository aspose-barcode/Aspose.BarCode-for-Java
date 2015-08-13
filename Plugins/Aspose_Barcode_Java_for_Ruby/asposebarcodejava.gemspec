# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'asposebarcodejava/version'

Gem::Specification.new do |spec|
  spec.name          = 'asposebarcodejava'
  spec.version       = Asposebarcodejava::VERSION
  spec.authors       = ['Aspose Marketplace']
  spec.email         = ['marketplace@aspose.com']
  spec.summary       = %q{A Ruby gem to work with Aspose.BarCode for Java libraries}
  spec.description   = %q{AsposeBarcodeJava is a Ruby gem that can help working with Aspose.BarCode for Java libraries}
  spec.homepage      = 'https://github.com/asposebarcode/Aspose_Barcode_Java/tree/master/Plugins/Aspose_Barcode_Java_for_Ruby'
  spec.license       = 'MIT'

spec.files         = `git ls-files`.split($/)
  spec.executables   = spec.files.grep(%r{^bin/}) { |f| File.basename(f) }
  spec.test_files    = spec.files.grep(%r{^(test|spec|features)/})
spec.require_paths = ['lib']

  spec.add_development_dependency 'bundler', '~> 1.7'
  spec.add_development_dependency 'rake', '~> 10.0'
  spec.add_development_dependency 'rspec'

  spec.add_dependency 'rjb', '~> 1.5.2'

end

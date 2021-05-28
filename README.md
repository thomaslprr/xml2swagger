# xml2swagger

This project allows you to convert an XML file into OpenAPI 2.0 (Swagger 2.0) documentation in JSON format. 

You can find a very detailed documentation [here](https://github.com/thomaslprr/xml2swagger/wiki).

## Usage 

## Dependancies

#### JAXB (Java Architecture for XML Binding)
Allows you to transform the XML structure into a Java class

#### GSON
Gson is an open source Java library for serializing and deserializing Java objects in JSON

## Module operation
The module takes an XML file as input which it will convert into Java classes (thanks to JAXB). Once the Java classes have been obtained, the module will create the json swagger 2.0 using the GSON library. The module will return the JSON code.

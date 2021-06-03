# xml2swagger

[![Github All Releases](https://img.shields.io/github/downloads/thomaslprr/xml2swagger/total.svg)]()


This project allows you to convert an XML file into OpenAPI 2.0 (Swagger 2.0) documentation in JSON format. 

**You can find a very detailed documentation** [**here**](https://github.com/thomaslprr/xml2swagger/wiki).

## Usage 

There are two ways to use the module depending on your needs.

**In any case, you will need to download the ".jar" file:** [**swagger2xml.jar**](https://github.com/thomaslprr/xml2swagger/releases/download/v1.0-beta/xml2swagger.jar)

**Use the module via the command prompt:** 

To do this you must:
- Place yourself in the directory of the ".jar" file downloaded above
- Type the command 

`java -jar xml2swagger.jar -f <file-name.xml>` 

_for example `java -jar xml2swagger.jar -f desktop/perso/example1.xml`_


-------------**or**-------------


`java -jar xml2swagger.jar -s "<xml-string>"` 

_for example `java -jar xml2swagger.jar -s "<global> <rest>...</rest> </global>"`_

Note: 

• `-s` option allows to specify an xml string `-s` means string input

• `-f` option allows to specify an .xml file address `-f` means file input

**Use the module in the Java source code:**

You can also use the module directly in the code. 

To do so, you just have to import the `.jar` file downloaded above in the buildpath of your project. 

Then you will benefit from four methods : 

| Method  | Description   |  
|---|---|
| `XmlParser.xmlFileToSwaggerJson(File file);`  | Convert xml file to swagger Json  | 
| `XmlParser.xmlStringToSwaggerJson(String xml);`  | Convert xml string to swagger Json  | 
| `XmlParser.xmlFileToJavaClass(File file)`| Convert xml file to Java class  | 
|  `XmlParser.xmlStringToJavaClass(String xml)` | Convert xml string to Java class  | 

The Java class conversion methods are there to increase the development possibilities for people using the module and to offer export methods in YAML instead of Json or the generation of PDF, HTML, etc. 


## Dependancies

#### JAXB (Java Architecture for XML Binding)
Allows you to transform the XML structure into a Java class

#### GSON
Gson is an open source Java library for serializing and deserializing Java objects in JSON

## Module operation
The module takes an XML file as input which it will convert into Java classes (thanks to JAXB). Once the Java classes have been obtained, the module will create the json swagger 2.0 using the GSON library. The module will return the JSON code.

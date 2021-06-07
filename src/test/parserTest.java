package test;

import static org.junit.Assert.*;

import java.io.File;

import org.json.JSONObject;
import org.junit.Test;

import converter.XmlParser;

public class parserTest {

	@Test
	public void petstoreTest() {
		File petstoreTest = new File("src/examples/petstore.xml");
		String petstoreStringResult = XmlParser.xmlFileToSwaggerJson(petstoreTest);
		
		JSONObject petstoreTestJson = new JSONObject(JsonSolutions.PETSTORE_SOLUTION);
		JSONObject petstoreResultJson = new JSONObject(petstoreStringResult);
				
		assertEquals(true,petstoreTestJson.similar(petstoreResultJson));
	}
	
	@Test
	public void longDefinitionExampleTest() {
		File example1File = new File("src/examples/example1.xml");
		String example1Result = XmlParser.xmlFileToSwaggerJson(example1File);
		
		JSONObject example1TestJson = new JSONObject(JsonSolutions.EXAMPLE1_SOLUTION);
		JSONObject example1ResultJson = new JSONObject(example1Result);
				
		assertEquals(true,example1TestJson.similar(example1ResultJson));
	}

}

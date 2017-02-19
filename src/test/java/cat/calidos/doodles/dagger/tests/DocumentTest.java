package cat.calidos.doodles.dagger.tests;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import org.junit.Test;

import cat.calidos.doodles.dagger2.application.Document;
import cat.calidos.doodles.dagger2.basic.DaggerDocumentComponent;
import cat.calidos.doodles.dagger2.basic.DocumentModule;
import cat.calidos.doodles.dagger2.basic.URIModule;


public class DocumentTest {

@Test
public void testCreateDocument() throws Exception {
	
	Document document = DaggerDocumentComponent.builder()
		.name("name")
		.uri(new URIModule("/url"))
		.build()
		.createDocument();
	
	assertEquals("name", document.getName());
	assertEquals("/url", document.getURI().getPath());
	assertEquals("{content:requested(/url)}",document.getContent());
	
}

}

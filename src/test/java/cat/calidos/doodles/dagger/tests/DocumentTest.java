package cat.calidos.doodles.dagger.tests;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import org.junit.Test;

import cat.calidos.doodles.dagger2.basic.DaggerDocumentComponent;
import cat.calidos.doodles.dagger2.basic.Document;
import cat.calidos.doodles.dagger2.basic.DocumentModule;
import cat.calidos.doodles.dagger2.basic.URIModule;


public class DocumentTest {

@Test
public void testDocumentComponent() throws Exception {
	
	Document document = DaggerDocumentComponent.builder()
		.documentModule(new DocumentModule("name"))
		.uRIModule(new URIModule("/url+metadata(/meta)"))
		.build()
		.createDocument();
	
	assertEquals("name", document.getName());
	assertEquals("/url+metadata(/meta)", document.getURI().getPath());
	assertEquals("{content:requested:{/url+metadata(/meta)}}",document.getContent());
	
}

}

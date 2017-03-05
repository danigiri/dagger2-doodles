/*
 *    Copyright 2017 Daniel Giribet
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package cat.calidos.doodles.dagger.tests;

import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import cat.calidos.doodles.dagger2.application.Document;
import cat.calidos.doodles.dagger2.dependencies.RequestException;
import cat.calidos.doodles.dagger2.producers.DaggerDocumentProducerComponent;
import cat.calidos.doodles.dagger2.producers.URIProducerModule;


/**
* @author daniel giribet
*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class DocumentProducerTest  {

@Test
public void testDocumentProducer() throws InterruptedException, ExecutionException {

	Document document = DaggerDocumentProducerComponent.builder()
						.name("name")
						.uri(new URIProducerModule("/url"))
						.build()
						.fetchDocument()
						.get();

	assertEquals("name", document.getName());
	assertEquals("/url", document.getURI().getPath());
	assertEquals("{content:requested(/url)}",document.getContent());

}

@Test
public void testWrongURI() throws Exception {
	
	boolean failed = false;
	try {
		DaggerDocumentProducerComponent.builder()
				.name("name")
				.uri(new URIProducerModule("wrong url"))
				.build()
				.fetchDocument()
				.get();
	} catch (ExecutionException e) {
		assertTrue(e.getCause() instanceof URISyntaxException);
		failed = true;
	}
	assertTrue(failed);
	
}


@Test
public void testRequestException() throws Exception {
	
	boolean failed = false;
	Document d = null;
	try {
		DaggerDocumentProducerComponent.builder()
				.name("name")
				.uri(new URIProducerModule("/uri-request-error"))
				.build()
				.fetchDocument()
				.get();
	} catch (ExecutionException e) {
		assertTrue(e.getCause() instanceof RequestException);
		failed = true;
	}
	assertTrue(failed);
	
}

}

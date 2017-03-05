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

package cat.calidos.doodles.dagger2.producers;

import java.net.URI;
import java.util.concurrent.ExecutionException;

import javax.inject.Named;

import cat.calidos.doodles.dagger2.application.Document;
import cat.calidos.doodles.dagger2.dependencies.Client;
import cat.calidos.doodles.dagger2.dependencies.Request;
import cat.calidos.doodles.dagger2.dependencies.RequestException;
import dagger.producers.Produced;
import dagger.producers.ProducerModule;
import dagger.producers.Produces;

/**
* @author daniel giribet
*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@ProducerModule
public class DocumentProducerModule {

@Produces
Document fetchDocument(String name, URI u, @Named("Content") Produced<String> contentProducer) {
	
	System.err.println("[module] Producer for Document called");
	String content;
	try {
		content = contentProducer.get();	
	} catch (ExecutionException e) {
		content = "NOTFOUND("+u+")";
	}
	
	return new Document(name, u, content);

}


@Produces @Named("Content")
String fetchContent(Client c, URI u) throws RequestException {
	
	Request request = DaggerRequestComponent2.builder()
						.uri(u)
						.build()
						.request();
	String content;
	try {
		content = c.performRequest(request);
	} catch (RequestException e) {
		throw new RuntimeException(e);
	} finally {
		c.close();
	}
	return "{content:"+content+"}";
}

}
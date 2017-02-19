/*
 *    Copyright 2016 Daniel Giribet
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

package cat.calidos.doodles.dagger2.basic;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import javax.inject.Named;
import javax.inject.Provider;

import cat.calidos.doodles.dagger2.application.Document;
import cat.calidos.doodles.dagger2.basic.RequestComponent.Builder;
import cat.calidos.doodles.dagger2.dependencies.Client;
import cat.calidos.doodles.dagger2.dependencies.Request;
import cat.calidos.doodles.dagger2.dependencies.RequestException;
import dagger.Module;
import dagger.Provides;

/**
* @author daniel giribet
*//////////////////////////////////////////////////////////////////////////////
@Module
public class DocumentModule {

private String name;
private String uri;

@Provides
Document provideDocument(String name, URI u, @Named("Content") String content) {
	return new Document(name, u, content);
	
}


@Provides @Named("ContentRequest") 
public Request provideContentRequest(RequestComponent.Builder rcb, URI uri) {
	RequestComponent requestBuilder;
	try {
		requestBuilder = rcb.forURI(new URIModule(uri.toString())).build();
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	return requestBuilder.get();
}


@Provides @Named("Content")
public String provideContent(Client c, @Named("ContentRequest") Request r)  {
	String content;
	try {
		content = c.performRequest(r);
	} catch (RequestException e) {
		throw new RuntimeException(e);
	} finally {
		c.close();
	}
	return "{content:"+content+"}";
	
}


}

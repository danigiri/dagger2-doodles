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

package cat.calidos.doodles.dagger2.application;

import java.net.URI;

import javax.inject.Inject;
import javax.inject.Named;

/**
* @author daniel giribet
*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Document {

private String name;
protected URI uri;
protected String content;


@Inject
public Document(String name, URI uri, @Named("Content") String content) {

	System.err.println("New Document instance with name '"+name+" @Inject(ed)");

	this.name = name;
	this.uri = uri;
	this.content = content;
	
}

public Document(String name, URI u) {

	System.err.println("New Document instance with name '"+name+" @Inject(ed)");

	this.name = name;
	this.uri = u;

}

public static String metadataUriFromContent(String c) throws Exception {

	System.err.println("metadataUriFromContent('"+ c+"') called");

	int metadataIndex = c.indexOf("/metadata");
	if (metadataIndex==-1) {
		throw new Exception("");
	}
	return c.substring(metadataIndex);
	
}

@Override
public String toString() {
	return "name:"+name+", uri="+uri+", content="+content+"";
}

public String getName() {

	return name;
}

public URI getURI() {
	
	return uri;
	
}

public String getContent() {
	return content;
}
}
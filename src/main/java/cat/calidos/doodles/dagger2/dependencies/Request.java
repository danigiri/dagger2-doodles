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

package cat.calidos.doodles.dagger2.dependencies;

import java.net.URI;

/**
* @author daniel giribet
*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Request {

private static final int REQUEST_TIME = 200;
private URI uri;

public Request(URI uri) {

	System.err.println(">New Request instance with uri '"+uri+" created");	
	
	this.uri = uri;

}


public String performRequest() throws RequestException {
	
	System.err.println(">Perform request with uri '"+uri+"' on ("+this+") called");	

	try {
		Thread.sleep(REQUEST_TIME);
	} catch (InterruptedException e) {
		throw new RequestException("Thread.sleep made me do it");
	}
	String path = uri.getPath();
	if (path.contains("request-error")) {
		throw new RequestException("Could not complete request");
	}
	
	return "requested("+uri+")";

}

}

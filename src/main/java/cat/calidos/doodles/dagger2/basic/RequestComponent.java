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

import cat.calidos.doodles.dagger2.dependencies.Request;
import dagger.Component;
import dagger.Subcomponent;

/**
* @author daniel giribet
*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@RequestScoped
@Subcomponent(modules={RequestModule.class, URIModule.class})
public interface RequestComponent {

Request get();
//RequestComponent.Builder getBuilder();

@Subcomponent.Builder 
public interface Builder {
//	Builder requestModule(RequestModule m);
	Builder forURI(URIModule m);
	RequestComponent build();
}

}

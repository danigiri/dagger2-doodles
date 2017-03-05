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

package cat.calidos.doodles.dagger2.basic;

import cat.calidos.doodles.dagger2.application.Document;
import dagger.BindsInstance;
import dagger.Component;

/**
* @author daniel giribet
*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Component(modules={DocumentModule.class, ClientModule.class, RequestModule.class, URIModule.class})
public interface DocumentComponent {

Document createDocument();

@Component.Builder
public interface Builder {
	@BindsInstance Builder name(String name);
	Builder uri(URIModule m);
	DocumentComponent build();
}

}

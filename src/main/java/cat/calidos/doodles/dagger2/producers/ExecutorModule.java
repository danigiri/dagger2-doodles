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

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import dagger.producers.Production;

@Module
final class ExecutorModule {
  @Provides
  @Production
  static Executor executor() {
	System.err.println("[module] Provider for Executor called");
	return new Executor() {
	
			
	private ExecutorService executor = Executors.newCachedThreadPool();

	@Override
	public void execute(Runnable command) {
		System.err.println("\t{Running "+command.hashCode());
		executor.execute(command);
		System.err.println("\tFinished "+command.hashCode()+"}");
	}
	};

	}
}


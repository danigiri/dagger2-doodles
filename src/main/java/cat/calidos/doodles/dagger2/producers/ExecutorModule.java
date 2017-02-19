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
	System.err.println("Provider for Executor called [module]");
	return new Executor() {
	
			
	private ExecutorService executor = Executors.newCachedThreadPool();

	@Override
	public void execute(Runnable command) {
		System.err.println("\t{Running command "+command.hashCode()+"}");
		this.executor.execute(command);
	}
	};

	}
}


package cat.calidos.doodles.dagger2.producers;

import java.util.concurrent.Executor;
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

    return Executors.newCachedThreadPool();
  }
}
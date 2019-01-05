package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDeadLock {

	ExecutorService exec = Executors.newSingleThreadExecutor();

	class RenderPageTask implements Callable<String> {
		@Override public String call() throws Exception {

			Future<String> header,footer;

			header = exec.submit(new FileLoadTask());
			footer = exec.submit(new FileLoadTask());
			return null;

		}
	}

	class FileLoadTask implements Callable {
		@Override public Object call() throws Exception {
			return null;
		}
	}
}

package com.canermirzabey.console;

import com.canermirzabey.console.ui.*;

import org.fusesource.jansi.*;
import org.fusesource.jansi.Ansi.*;
import org.jnativehook.*;
import org.jnativehook.keyboard.*;

import java.io.*;
import java.util.*;
import java.util.Map.*;
import java.util.concurrent.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public class ConsoleApplication implements NativeKeyListener {
	private static final ExecutorService                            pool               =
			Executors.newCachedThreadPool();
	private static       Map<String, Entry<View, List<Controller>>> context            = new HashMap();
	private static       Queue<View>                                viewsToBeProcessed = new LinkedBlockingQueue<>();
	private static String currentViewId;
	private final  int    height;
	private final  int    width;

	public ConsoleApplication(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public static void viewChanged(View view) {
		((Runnable) () -> viewsToBeProcessed.add(view)).run();
	}

	public synchronized <T extends View> void addView(T view, Controller<T>... controllers) {
		context.put(view.getViewId(), new Entry<View, List<Controller>>() {

			@Override
			public View getKey() {
				return view;
			}

			@Override
			public List<Controller> getValue() {
				return Arrays.asList(controllers);
			}

			@Override
			public List<Controller> setValue(List<Controller> value) {
				return value;
			}
		});
	}

	public void start(String viewId) {
		GlobalScreen.addNativeKeyListener(this);
		AnsiConsole.systemInstall();
		if (!getContext().containsKey(viewId)) {
			System.exit(0);
		}
		this.currentViewId = viewId;
		viewChanged(getContext().get(viewId).getKey());
		((Runnable) () -> {
			while (true) {
				if (!viewsToBeProcessed.isEmpty()) {
					View         view   = viewsToBeProcessed.poll();
					Future<Ansi> result = pool.submit(() -> new ViewRenderer(view).renderView());
					while (!result.isDone()) {
						if (result.isDone()) {
							pool.submit(() -> {
								out(result.get());
								return true;
							});
						}
					}
				}
			}
		}).run();
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
		if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_DOWN) {

		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
	}

	public synchronized Map<String, Entry<View, List<Controller>>> getContext() {
		return context;
	}

	private void out(Ansi ansi) {
		long startTime = System.currentTimeMillis();

		ansi.setEnabled(true);
		ansi.eraseScreen();
		ansi.scrollUp(height);
		ansi.cursor(0, 0);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(AnsiConsole.out);
		try {
			bufferedOutputStream.write(ansi.toString().getBytes());
			OutputStream outputStream = AnsiConsole.wrapOutputStream(bufferedOutputStream);
			outputStream.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		long duration = System.currentTimeMillis() - startTime;
		AnsiConsole.out().print("\n\nwrite: " + duration + "\n\n");
	}

	class ViewChangedEventTask implements Callable<Ansi> {
		private View view;

		public ViewChangedEventTask(View view) {
			this.view = view;
		}

		@Override
		public Ansi call() throws Exception {
			return new ViewRenderer(view).renderView();
		}
	}

	class KeyPressedTask implements Future<Ansi> {
		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			return false;
		}

		@Override
		public boolean isCancelled() {
			return false;
		}

		@Override
		public boolean isDone() {
			return false;
		}

		@Override
		public Ansi get() throws InterruptedException, ExecutionException {
			return null;
		}

		@Override
		public Ansi get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
			return null;
		}
	}

}

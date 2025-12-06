package simpleJGE;

import simpleJGE.abstracts.Processable;

@SuppressWarnings("unused")
// usage depends upon user implementation

public abstract class Timer implements Processable {
	private long timeLimitMillis;
	private long startTimeMillis;

	private boolean running;
	private Runnable onTimeout;

	public Timer(long timeLimitMillis) {
		this.timeLimitMillis = timeLimitMillis;
		start();
	}

	public Timer() {
		this(1000);
	}

	public void start() {
		startTimeMillis = System.currentTimeMillis();
	}

	public void setTimeLimitMillis(long timeLimitMillis) {
		this.timeLimitMillis = timeLimitMillis;
	}

	public long getTimeElapsed() {
		return System.currentTimeMillis() - startTimeMillis;
	}

	public long getTimeLeft() {
		return (startTimeMillis + timeLimitMillis) - System.currentTimeMillis();
	}

	public void setOnTimeout(Runnable onTimeout) {
		this.onTimeout = onTimeout;
	}

	@Override
	public void update() {
		if (getTimeLeft() < 0 && this.running) {
			this.running = false;
			onTimeout.run();
		}
	}
}

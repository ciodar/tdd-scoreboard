package com.ciodar.scoreboard;

public class StopWatch {

	private Clock clock;
	private long start;
	private long initialMillis;
	private boolean running;

	public StopWatch(Clock clock, long initialMillis) {
		this.clock = clock;
		this.running = false;
		this.initialMillis = initialMillis;
	}

	public long tick() {
		if (this.isRunning()) {
			long elapsed = clock.currentTimeMillis() - start;
			long remaining = initialMillis - elapsed;
			if (elapsed < 0)
				throw new IllegalStateException("Unexpected negative elapsed time");
			if (remaining <= 0) {
				this.initialMillis = 0;
				this.running = false;
			}
			else
				return remaining;
		}
		return initialMillis;
	}

	public boolean isRunning() {
		return running;
	}
	
	public void start() {
		if (isRunning())
			throw new IllegalStateException("Timer already started!");
		running = true;
		start = clock.currentTimeMillis();
	}

	public void stop() {
		long currentMillis = clock.currentTimeMillis();
		if (!isRunning())
			throw new IllegalStateException("Timer already stopped!");
		else
			running = false;
		initialMillis -= (currentMillis - start);
		start = currentMillis;
	}
}

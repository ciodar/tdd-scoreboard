package com.ciodar.scoreboard;

public class StopWatch {

	private Clock clock;
	private long start;
	private long initialTime;
	private boolean running;

	public StopWatch(Clock clock, long initialTime) {
		this.clock = clock;
		this.initialTime = initialTime;
	}

	public void start() {
		this.running = true;
		this.start = clock.currentTimeMillis();
	}

	public long tick() {
		long elapsed = clock.currentTimeMillis() - start;
		if (elapsed < 0)
			throw new RuntimeException("Unexpected negative elapsed time");
		if (initialTime - elapsed > 0)
			return initialTime - elapsed;
		else
			this.running = false;
		return 0;
	}

	public boolean isRunning() {
		return running;
	}

}

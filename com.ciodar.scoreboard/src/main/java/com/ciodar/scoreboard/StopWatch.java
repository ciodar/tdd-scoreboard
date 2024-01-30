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
		if (this.isRunning()) {
			long elapsed = clock.currentTimeMillis() - start;
			if (elapsed < 0)
				throw new IllegalStateException("Unexpected negative elapsed time");
			if (initialTime - elapsed > 0)
				return initialTime - elapsed;
			else {
				this.running = false;
				this.initialTime=0;
			}
		}
		return initialTime;
	}

	public boolean isRunning() {
		return running;
	}

	public void stop() {
		if (this.isRunning()){
			this.running=false;
			this.initialTime = this.initialTime - (clock.currentTimeMillis() - start);
			this.start = clock.currentTimeMillis();
		}
		else
			throw new IllegalStateException("Timer is already stopped");
	}

}

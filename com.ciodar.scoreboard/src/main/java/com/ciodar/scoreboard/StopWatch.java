package com.ciodar.scoreboard;

public class StopWatch {
	
	private Clock clock;
	private long start;
	private long initialTime;

	public StopWatch(Clock clock, long initialTime) {
		this.clock = clock;
		this.initialTime = initialTime;
		this.start = clock.currentTimeMillis();
	}

	public long tick() {
		long elapsed = clock.currentTimeMillis() - start;
		return (initialTime - elapsed > 0) ? initialTime - elapsed: 0;
	}

}

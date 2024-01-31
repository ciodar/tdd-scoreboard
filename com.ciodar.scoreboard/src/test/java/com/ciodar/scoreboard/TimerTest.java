package com.ciodar.scoreboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TimerTest {
	private static final long REMAINING = 10000L;
	private static final long START_TIME = 1000L;
	Clock stubClock;
	StopWatch stopWatch;

	@Before
	public void setup() {
		stubClock = mock(Clock.class);
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME);
		stopWatch = new StopWatch(stubClock, REMAINING);
		stopWatch.start();
	}

	@Test
	public void testStartStopWatchShouldReturnRemaining() {
		assertEquals(REMAINING, stopWatch.tick());
		assertEquals(true, stopWatch.isRunning());
	}

	@Test
	public void testElapsedTimeShouldDecreaseRemaining() {
		long time_passed = 1000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(REMAINING - time_passed, stopWatch.tick());
		assertEquals(true, stopWatch.isRunning());
	}

	@Test
	public void testElapsedEqualsRemainingShouldStopTimer() {
		long time_passed = 10000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(0, stopWatch.tick());
		assertEquals(false, stopWatch.isRunning());
	}

	@Test
	public void testElapsedMoreThanRemainingShouldReturnZeroAndStopTimer() {
		long time_passed = 11000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(0, stopWatch.tick());
		assertEquals(false, stopWatch.isRunning());
	}

	@Test(expected = IllegalStateException.class)
	public void testNegativeElapsedTimeShouldThrow() {
		long time_passed = -1000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		stopWatch.tick();
		fail();
	}
	
	@Test
	public void testTimerStoppedShouldNotDecreaseRemaining() {
		long time_passed=1000L;
		stopWatch.stop();
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(REMAINING, stopWatch.tick());
	}
	
	@Test
	public void testTimerStoppedAfter() {
		long beforeStop = 1000L;
		long afterStop = 2000L;
		long time_passed=START_TIME + beforeStop;
		when(stubClock.currentTimeMillis()).thenReturn(time_passed);
		stopWatch.stop();
		time_passed+= afterStop;
		when(stubClock.currentTimeMillis()).thenReturn(time_passed);
		assertEquals(REMAINING - beforeStop,stopWatch.tick());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testStartTimerWhenAlreadyRunningShouldThrow() {
		stopWatch.start();
		stopWatch.start();
		fail();
	}
	
	@Test(expected = IllegalStateException.class)
	public void testStopTimerWhenTimerNotRunningShouldThrow() {
		stopWatch.stop();
		stopWatch.stop();
		fail();
	}
}

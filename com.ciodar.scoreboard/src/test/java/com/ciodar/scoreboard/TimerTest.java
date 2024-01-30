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
	public void testStartStopwatchZeroSecondPassedShouldReturnRemaining() {
		assertEquals(REMAINING, stopWatch.tick());
		assertEquals(true, stopWatch.isRunning());
	}

	@Test
	public void testStartStopwatchOneSecondPassedShouldDecreaseRemaining() {
		long time_passed = 1000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(REMAINING - time_passed, stopWatch.tick());
		assertEquals(true, stopWatch.isRunning());
	}

	@Test
	public void testStartStopwatchTenSecondPassedShouldReturnZero() {
		long time_passed = 10000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(0, stopWatch.tick());
		assertEquals(false, stopWatch.isRunning());
	}

	@Test
	public void testStartStopWatchElevenSecondPassesShouldStopTimer() {
		long time_passed = 11000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(0, stopWatch.tick());
		assertEquals(false, stopWatch.isRunning());
	}

	@Test(expected = RuntimeException.class)
	public void testTimeRunningBackwardsShouldThrow() {
		long time_passed = -1000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		stopWatch.tick();
		fail();
	}
	
	@Test
	public void testStopTimerShouldNotDecreaseRemainingTime() {
		long time_passed=1000L;
		stopWatch.stop();
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(REMAINING, stopWatch.tick());
	}
	
	@Test
	public void testStopTimerShouldNotDecreaseAfterStop() {
		long beforeStop = 1000L;
		long afterStop = 2000L;
		long time_passed=START_TIME + beforeStop;
		when(stubClock.currentTimeMillis()).thenReturn(time_passed);
		stopWatch.stop();
		time_passed+= afterStop;
		when(stubClock.currentTimeMillis()).thenReturn(time_passed);
		assertEquals(REMAINING - beforeStop,stopWatch.tick());
	}
}

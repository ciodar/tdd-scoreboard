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
		assertEquals(stopWatch.tick(),REMAINING);
		assertEquals(true, stopWatch.isRunning());
	}
	
	@Test
	public void testStartStopwatchOneSecondPassedShouldReturnRemaining() {
		long time_passed = 1000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(stopWatch.tick(), REMAINING - time_passed);
		assertEquals(true, stopWatch.isRunning());
	}
	
	@Test
	public void testStartStopwatchTenSecondPassedShouldReturnZero() {
		long time_passed = 10000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(stopWatch.tick(), 0);
		assertEquals(false, stopWatch.isRunning());
	}
	
	@Test 
	public void testStartStopWatchElevenSecondPassesShouldReturnZero() {
		long time_passed = 11000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		assertEquals(stopWatch.tick(), 0);
		assertEquals(false, stopWatch.isRunning());
	}
	
	@Test(expected = RuntimeException.class)
	public void testTimeRunningBackwardsShouldThrow() {
		long time_passed = -1000L;
		when(stubClock.currentTimeMillis()).thenReturn(START_TIME + time_passed);
		stopWatch.tick();
		fail();
	}
}

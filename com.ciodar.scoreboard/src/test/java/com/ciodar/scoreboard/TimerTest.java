package com.ciodar.scoreboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TimerTest {
	private static final long REMAINING = 10000L;
	Clock stubClock;
	StopWatch stopWatch;
	
	
	@Before
	public void setup() {
		stubClock = mock(Clock.class);
		when(stubClock.currentTimeMillis()).thenReturn(0L);
		stopWatch = new StopWatch(stubClock, REMAINING);
	}
	
	@Test
	public void testStartStopwatchZeroSecondPassedShouldReturnRemaining() {
		assertEquals(stopWatch.tick(),REMAINING);
	}
	
	@Test
	public void testStartStopwatchOneSecondPassedShouldReturnRemaining() {
		when(stubClock.currentTimeMillis()).thenReturn(1000L);
		assertEquals(stopWatch.tick(), REMAINING - 1000L);
	}
	
	@Test
	public void testStartStopwatchTenSecondPassedShouldReturnZero() {
		when(stubClock.currentTimeMillis()).thenReturn(10000L);
		assertEquals(stopWatch.tick(), 0);
	}
	
	@Test 
	public void testStartStopWatchElevenSecondPassesShouldReturnZero() {
		when(stubClock.currentTimeMillis()).thenReturn(11000L);
		assertEquals(stopWatch.tick(), 0);
	}
}

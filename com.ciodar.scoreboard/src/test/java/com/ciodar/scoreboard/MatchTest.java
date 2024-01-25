package com.ciodar.scoreboard;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatchTest {
	
	@Test
	public void testMatchShouldStartNil() {
		Match match = new Match();
		assertEquals(match.getHomeScore(),0);
		assertEquals(match.getAwayScore(),0);
	}
	
	@Test
	public void testResumeMatchShouldStartWithScore() {
		Match match = new Match(1,1);
		assertEquals(match.getHomeScore(),1);
		assertEquals(match.getAwayScore(),1);
	}

}

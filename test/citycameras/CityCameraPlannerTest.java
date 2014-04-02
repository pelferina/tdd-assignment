/*******************************************************************************
 * Copyright (c) 2014 Gary F. Pollice
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Used in CS3733, Software Engineering at Worcester Polytechnic Institute
 *******************************************************************************/

package citycameras;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

/**
 * Sample tests for the TDD assignment in CS3733.
 * @version Mar 30, 2014
 * @author gpollice
 */
public class CityCameraPlannerTest
{
	@Test
	public void testStraightLineOfThree()
	{
		final Road[] roads = {
			new Road("A", "B"), new Road("B", "C")	
		};
		
		Collection<Road> city = new HashSet<Road>();
		for (Road r : roads) {
			city.add(r);
		}
		final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city); 
		assertEquals(1, cameraPlanner.getCameras().size());
		assertTrue(cameraPlanner.getCameras().contains("B"));
		assertTrue(cameraPlanner.hasCamera("B"));
		assertFalse(cameraPlanner.hasCamera("A"));
	}

	@Test
	public void testTriangle()
	{
		final Road[] roads = {
				new Road("A", "B"), new Road("B", "C"), new Road("A", "C")	
			};
			
			Collection<Road> city = new HashSet<Road>();
			for (Road r : roads) {
				city.add(r);
			}
			final CityCameraPlanner cameraPlanner = new CityCameraPlanner(city); 
			assertEquals(0, cameraPlanner.getCameras().size());
			assertFalse(cameraPlanner.getCameras().contains("B"));
	}
}

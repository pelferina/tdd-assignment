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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class implements the algorithms for planning the city security cameras
 * as described in the TDD assignment for CS3733. The idea is taken from the
 * <a href="http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=111006&format=html">
 * <em>Tourist Guide</em></a> problem at the Programming Challenges website.
 * 
 * @version Mar 30, 2014
 * @author Rafi Hayne
 */
public class CityCameraPlanner
{
	private HashMap<String, Neighborhood> theCity;
	private List<String> Cameras = new ArrayList<String>();

	/**
	 * The constructor takes a collection of all of the roads in the city that
	 * connect neighborhoods and initializes the instance so that it can provide
	 * the locations of the cameras via a couple of query methods.
	 * @param roads the collection of roads that connect nighborhoods in the city
	 */
	public CityCameraPlanner(Collection<Road> roads)
	{
		theCity = new HashMap<String, Neighborhood>();
		
		for (Road road: roads)
		{
			String n1 = road.getNeighborhood1();
			String n2 = road.getNeighborhood2();
			
			addNeighborhood(n1);
			addNeighborhood(n2);
			
			theCity.get(n1).addNeighbor(theCity.get(n2));
			theCity.get(n2).addNeighbor(theCity.get(n1));
		}
		
		findCameras();
		// TODO: Implement this constructor.
	}
	
	/**
	 * @return a collection of all neighborhoods containing cameras
	 */
	public Collection<String> getCameras()
	{	
		// TODO: Implement this method.
		return Cameras;
	}
	
	/**
	 * @param neighborhood the neighborhood under consideration
	 * @return true if the neighborhood has a camera
	 */
	public boolean hasCamera(String neighborhood) {
		return Cameras.contains(neighborhood);
	}
	
	private void findCameras()
	{
		Collection<Neighborhood> theHood = theCity.values();
			
		for (Neighborhood n : theHood)
		{			
			resetVisited();
			n.visit();
			removeNeighborhood(n);
			if ( !isConnected() && !Cameras.contains(n.getName()) )
				Cameras.add(n.getName());
			addNeighborhood(n);		
		}
		
		System.out.println("Found: "+Cameras.size()+" cameras");
	}
	
	private boolean addNeighborhood(String neighborhood)
	{
		if( !theCity.containsKey( neighborhood ) )
		{
			theCity.put( neighborhood , new Neighborhood(neighborhood) );
			return true;
		}
		
		return false;
	}
	
//	public void depthFirstSearch(String start)
//	{
//		Neighborhood u = theCity.get(start);
//		Collection<Neighborhood> theHood = theCity.values();
//		
//		System.out.println("DFS starting on: "+u.getName());
//
//		u.visit();
//		for(Neighborhood v : u.neighbors)
//		    if( !v.isVisited() )
//		    	depthFirstSearch(v.getName());
//		
//	}
	
	public void depthFirstSearch(String start)
	{
		Neighborhood u = theCity.get(start);
		Collection<Neighborhood> theHood = theCity.values();

		u.visit();
		for(Neighborhood v : u.neighbors)
		    if( !v.isVisited() )
		    	depthFirstSearch(v.getName());
		
	}
	
	private void resetVisited()
	{
		for (Neighborhood n : theCity.values())
			n.unVisit();
	}
	
	private boolean isConnected()
	{
		boolean connected = true;
		Object[] theHood = theCity.values().toArray();
		depthFirstSearch(((Neighborhood) theHood[0]).getName());
		
		for (Neighborhood n : theCity.values())
		{
			if ( !n.isVisited() )
			{	
				System.out.println(n.getName()+" not connected");
				connected = false;
			}
			
		}
			return connected;
	}
	
	private void removeNeighborhood(Neighborhood theNeighborhood)
	{
		System.out.println("Removing: "+theNeighborhood.getName());
		
		theCity.remove(theNeighborhood);
		for (Neighborhood n : theNeighborhood.neighbors)
			n.neighbors.remove(theNeighborhood);
	}
	
	private void addNeighborhood(Neighborhood theNeighborhood)
	{
		theCity.put(theNeighborhood.getName(),theNeighborhood);
		for (Neighborhood n : theNeighborhood.neighbors)
			n.neighbors.add(theNeighborhood);
	}
}

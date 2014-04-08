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
	/**
	 * Map of the city of Anatidae with its neighborhoods
	 */
	private HashMap<String, Neighborhood> mapOfTheCity;
	
	/**
	 * List of neighborhoods in Anatidae with cameras 
	 */
	private List<String> Cameras = new ArrayList<String>();
	
	int id;

	/**
	 * The constructor takes a collection of all of the roads in the city that
	 * connect neighborhoods and initializes the instance so that it can provide
	 * the locations of the cameras via a couple of query methods.
	 * @param roads the collection of roads that connect nighborhoods in the city
	 */
	public CityCameraPlanner(Collection<Road> roads)
	{
		mapOfTheCity = new HashMap<String, Neighborhood>();
		id = 0;
		
		for (Road road: roads)
		{
			//Finds the pair of neighborhoods connected by a road 
			String n1 = road.getNeighborhood1();
			String n2 = road.getNeighborhood2();
			
			//Adds the neighborhood to the map of the city
			addNeighborhood(n1);
			addNeighborhood(n2);
			
			//Adds the connected neighbors in the map of the city
			mapOfTheCity.get(n1).addNeighbor(mapOfTheCity.get(n2));
			mapOfTheCity.get(n2).addNeighbor(mapOfTheCity.get(n1));
		}
		
		findCameras();
	}
	
	/**
	 * 
	 * @return a collection of all neighborhoods containing cameras
	 */
	public Collection<String> getCameras()
	{	
		return Cameras;
	}
	
	/**
	 * @param neighborhood the neighborhood under consideration
	 * @return true if the neighborhood has a camera
	 */
	public boolean hasCamera(String neighborhood) {
		return Cameras.contains(neighborhood);
	}
	
	/**
	 *  finds the neighborhoods where a camera is needed and populates the list of neighborhoods with cameras
	 */
	private void findCameras()
	{
		Collection<Neighborhood> theHood = mapOfTheCity.values();
			
		//Looks for neighborhoods that are also articulation points in the map
		for (Neighborhood n : theHood)
		{			
			resetVisited();
			n.setVisited(true);
			removeNeighborhood(n);
			if ( !isConnected() && !Cameras.contains(n.getName()) )
				Cameras.add(n.getName());
			addNeighborhood(n);		
		}
		
		System.out.println("Found: "+Cameras.size()+" cameras");
	}
	
	/**
	 * @param neighborhood
	 * @return true if neighborhood was successfully added
	 */
	private boolean addNeighborhood(String neighborhood)
	{
		if( !mapOfTheCity.containsKey( neighborhood ) )
		{
			mapOfTheCity.put( neighborhood , new Neighborhood(neighborhood) );
			return true;
		}
		
		return false;
	}
	
	private void depthFirstSearch(String start)
	{
		Neighborhood u = mapOfTheCity.get(start);
		Collection<Neighborhood> theHood = mapOfTheCity.values();

		u.setVisited(true);
		for(Neighborhood v : u.neighbors)
		    if( !v.getVisited() )
		    	depthFirstSearch(v.getName());
		
	}
	
	private void resetVisited()
	{
		for (Neighborhood n : mapOfTheCity.values())
			n.setVisited(false);
	}
	
	/**
	 * @return true if the map is still connected after removal of a particular neighborhood
	 */
	private boolean isConnected()
	{
		boolean connected = true;
		Object[] theHood = mapOfTheCity.values().toArray();
		depthFirstSearch(((Neighborhood) theHood[0]).getName());
		
		for (Neighborhood n : mapOfTheCity.values())
		{
			if ( !n.getVisited() )
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
		
		mapOfTheCity.remove(theNeighborhood);
		for (Neighborhood n : theNeighborhood.neighbors)
			n.neighbors.remove(theNeighborhood);
	}
	
	/**
	 * adds the neighborhood to the map of the city of Anatidae
	 * @param theNeighborhood
	 */
	private void addNeighborhood(Neighborhood theNeighborhood)
	{
		mapOfTheCity.put(theNeighborhood.getName(),theNeighborhood);
		for (Neighborhood n : theNeighborhood.neighbors)
			n.neighbors.add(theNeighborhood);
	}
	
	public int getID()
	{
		return id++;
	}
	
	/**
	 * gets the Map of the given city 
	 * @return theCity Map of the city of Anatidae
	 */
	public HashMap<String, Neighborhood> getTheCity() {
		return mapOfTheCity;
	}

	/**
	 * sets the Map to the given city
	 * @param theCity Map of the city of Anatidae 
	 */
	public void setTheCity(HashMap<String, Neighborhood> theCity) {
		this.mapOfTheCity = theCity;
	}

	public void setCameras(List<String> cameras) {
		Cameras = cameras;
	}
}

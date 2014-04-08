package citycameras;

import java.util.HashSet;
import java.util.Set;

/**
 * A neighborhood is simply encapsulating the characteristics of the neighborhood and its connecting neighbors 
 * @version Mar 30, 2014
 * @author rhhayne, Ayesha
 *
 */
public class Neighborhood
{
	private final String name;
	private boolean visited;
	Set<Neighborhood> neighbors = new HashSet<Neighborhood>();
	
	/**
	 * The only constructor.
	 * @param name String 
	 */
	public Neighborhood(String name)
	{
		this.name = name;
		this.visited = false;
	}

	/**
	 * add a neighbor to neighbor set
	 * @param aNeighbor Neighborhood
	 */
	public void addNeighbor(Neighborhood aNeighbor)
	{
		neighbors.add(aNeighbor);
	}
	
	/**
	 * sets the visited state of the given neighborhood
	 * @param visit boolean
	 */
	public void setVisited(boolean visit)
	{
		visited = visit;
	}
	
	/**
	 * gets the visited state of the given neighborhood
	 * @return visited boolean
	 */
	public boolean getVisited()
	{
		return visited;
	}
	
	
	/**
	 * gets the name of the given neighborhood
	 * @return name String
	 */
	public String getName() {
		return name;
	}

	/**
	 * checks if a neighbor is connected to the given neighborhood 
	 * @param aNeighbor Neighborhood
	 * @return isNeighbor boolean
	 */
	public boolean isNeighbor(Neighborhood aNeighbor)
	{
		boolean isNeighbor = neighbors.contains(aNeighbor); 
		return isNeighbor;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());

		return result;
	}
}
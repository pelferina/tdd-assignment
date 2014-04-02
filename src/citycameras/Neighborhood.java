package citycameras;

import java.util.HashSet;
import java.util.Set;

/**
 * A neighborhood is a node with a list of neighboring nodes
 * @version Mar 30, 2014
 * @author rhhayne
 */
public class Neighborhood
{
	private final String name;
	private boolean visited;
	Set<Neighborhood> neighbors = new HashSet<Neighborhood>();
	
	/**
	 * The only constructor.
	 * @param name the name of this neighborhood
	 */
	public Neighborhood(String name)
	{
		this.name = name;
		this.visited = false;
	}

	/**
	 * add a neighbor to neighbor set
	 */
	public void addNeighbor(Neighborhood aNeighbor)
	{
		neighbors.add(aNeighbor);
	}
	
	public void visit()
	{
		visited = true;
	}
	
	public boolean isVisited()
	{
		return visited;
	}
	
	public void unVisit() 
	{
		this.visited = false;
	}
	
	public String getName() {
		return name;
	}
	
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
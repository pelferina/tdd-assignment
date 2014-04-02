package citycameras;

import java.util.HashSet;
import java.util.Set;

/**
 * A road is simply a pair of neighborhoods. The road connects these two
 * neighborhoods directly.
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
	 * @param neighborhood1 one of the neighborhoods that the road connects
	 * @param neighborhood2 the second road connected by the neighborhoods
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
	
	public String getName() {
		return name;
	}

	public boolean isNeighbor(Neighborhood aNeighbor)
	{
		return neighbors.contains(aNeighbor);
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

	public void unVisit() {
		this.visited = false;
		
	}
}
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

/**
 * A road is simply a pair of neighborhoods. The road connects these two
 * neighborhoods directly.
 * @version Mar 30, 2014
 * @author gpollice
 */
public class Road
{
	private final String neighborhood1, neighborhood2;
	
	/**
	 * The only constructor.
	 * @param neighborhood1 one of the neighborhoods that the road connects
	 * @param neighborhood2 the second road connected by the neighborhoods
	 */
	public Road(String neighborhood1, String neighborhood2)
	{
		this.neighborhood1 = neighborhood1;
		this.neighborhood2 = neighborhood2;
	}

	/**
	 * @return the neighborhood1
	 */
	public String getNeighborhood1()
	{
		return neighborhood1;
	}

	/**
	 * @return the neighborhood2
	 */
	public String getNeighborhood2()
	{
		return neighborhood2;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((neighborhood1 == null) ? 0 : neighborhood1.hashCode());
		result = prime * result
				+ ((neighborhood2 == null) ? 0 : neighborhood2.hashCode());
		return result;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Road other = (Road) obj;
		if (neighborhood1 == null) {
			if (other.neighborhood1 != null)
				return false;
		} else if (!neighborhood1.equals(other.neighborhood1))
			return false;
		if (neighborhood2 == null) {
			if (other.neighborhood2 != null)
				return false;
		} else if (!neighborhood2.equals(other.neighborhood2))
			return false;
		return true;
	}
}

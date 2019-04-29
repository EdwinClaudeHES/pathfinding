
package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

public class MyNode
	{

	//TODO Mettre Final
	private int index;
	private int x;
	private int y;
	private Array<Connection<MyNode>> connections;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public MyNode(final int index, final int x, final int y, final int capacity)
		{
		this.index = index;
		this.x = x;
		this.y = y;
		this.connections = new Array<Connection<MyNode>>(capacity);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public int getIndex()
		{
		return index;
		}

	public Array<Connection<MyNode>> getConnections()
		{
		return connections;
		}

	@Override
	public String toString()
		{
		return "IndexedNodeFake [index=" + index + ", x=" + x + ", y=" + y + ", connections=" + connections + "]";
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getX()
		{
		return this.x;
		}

	public int getY()
		{
		return this.y;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	}

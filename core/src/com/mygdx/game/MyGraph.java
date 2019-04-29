
package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class MyGraph implements IndexedGraph<MyNode>
	{

	public Array<MyNode> nodes;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public MyGraph(Array<MyNode> nodes)
		{
		this.nodes = nodes;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	@Override
	public Array<Connection<MyNode>> getConnections(MyNode fromNode)
		{
		return fromNode.getConnections();
		}

	@Override
	public int getIndex(MyNode node)
		{
		return node.getIndex();
		}

	@Override
	public int getNodeCount()
		{
		return nodes.size;
		}

	//	public Array<MyNode> getNodes()
	//		{
	//		return this.nodes;
	//		}
	}

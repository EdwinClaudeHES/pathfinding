
package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Heuristic;

public class ManhattanDistance implements Heuristic<MyNode>
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public ManhattanDistance()
		{
		// TODO Auto-generated constructor stub
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public float estimate(MyNode node, MyNode endNode)
		{
		// TODO Auto-generated method stub
		return Math.abs(endNode.getX() - node.getX()) + Math.abs(endNode.getY() - node.getY());
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}

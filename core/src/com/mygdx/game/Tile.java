
package com.mygdx.game;

public class Tile
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Tile(int x, int y, int width, int height)
		{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public int getX()
		{
		return this.x;
		}

	public int getY()
		{
		return this.y;
		}

	public int getWidth()
		{
		return this.width;
		}

	public int getHeight()
		{
		return this.height;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int x;
	private int y;
	private int width;
	private int height;
	}

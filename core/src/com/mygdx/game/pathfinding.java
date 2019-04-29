
package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;

public class pathfinding extends ApplicationAdapter
	{

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;

	//Base
	Tile start;
	Tile dest;

	//Pathfinding
	List<Tile> listObstacle = new ArrayList<Tile>();

	//Graphe
	private MyGraph graph;
	private IndexedAStarPathFinder<MyNode> pathfinder;
	private GraphPath<MyNode> outPath;

	private boolean searchResult;

	@Override
	public void create()
		{
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		listObstacle.add(new Tile(4 * 32, 5 * 32, 32, 32));
		listObstacle.add(new Tile(5 * 32, 5 * 32, 32, 32));
		listObstacle.add(new Tile(6 * 32, 5 * 32, 32, 32));

		start = new Tile(0, 0, 32, 32);
		dest = new Tile(10 * 32, 10 * 32, 32, 32);

		this.graph = createGraph();
		pathfinder = new IndexedAStarPathFinder<MyNode>(graph);
		outPath = new DefaultGraphPath<MyNode>();

		ManhattanDistance md = new ManhattanDistance();

		System.out.println("Test : ");
		System.out.println("Distance Manhattan : " + md.estimate(graph.nodes.get(0), graph.nodes.get(42)));
		System.out.println("Start { X :" + graph.nodes.get(0).getX() + " , Y : " + graph.nodes.get(0).getY() + "}");
		System.out.println("Dest { X :" + graph.nodes.get(42).getX() + " , Y : " + graph.nodes.get(42).getY() + "}");

		System.out.println("Tile :");
		System.out.println("Start : " + getIndex(start.getX(), start.getX()));
		System.out.println("Dest : " + getIndex(dest.getX(), dest.getY()));
		//
		//		System.out.println("MyNode : ");
		//		System.out.println("Dest { X :" + graph.nodes.get(dest.getIndex()).getX() + " , Y : " + graph.nodes.get(dest.getIndex()).getY() + "}");

		searchResult = pathfinder.searchNodePath(graph.nodes.get(getIndex(start.getX(), start.getX())), graph.nodes.get(getIndex(dest.getX(), dest.getY())), new ManhattanDistance(), outPath);

		}

	@Override
	public void render()
		{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		shapeRenderer.begin(ShapeType.Filled);

		shapeRenderer.setColor(Color.LIGHT_GRAY);

		for(int y = 0; y < 20; y++)
			{
			for(int x = 0; x < 20; x++)
				{
				shapeRenderer.rect(x * 32, y * 32, 32, 32);
				}
			}

		shapeRenderer.setColor(Color.YELLOW);

		//		System.out.println(outPath.getCount());

		int i = 0;

		for(MyNode node:outPath)
			{
			shapeRenderer.rect(node.getX(), node.getY(), 32, 32);
			//			System.out.println("I {" + i + "}[" + node.getX() + "][" + node.getY() + "]");
			i++;
			}

		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(start.getX(), start.getY(), start.getWidth(), start.getHeight());

		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.rect(dest.getX(), dest.getY(), dest.getWidth(), dest.getHeight());

		shapeRenderer.setColor(Color.RED);

		for(Tile obstacle:listObstacle)
			{
			shapeRenderer.rect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
			}

		shapeRenderer.end();
		batch.end();
		}

	@Override
	public void dispose()
		{
		batch.dispose();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private MyGraph createGraph()
		{
		int numRow = 20;
		int numCol = 20;
		int tileWidth = 32;
		int tileHeight = 32;

		MyNode[][] nodes = new MyNode[numCol][numRow];
		Array<MyNode> indexedNodes = new Array<MyNode>(numCol * numRow);

		boolean[][] tiles = tabIsSolid(numRow, numCol, tileWidth, tileHeight);

		int index = 0;
		int posX = 0;
		int posY = 0;

		for(int y = 0; y < numRow; y++)
			{
			posX = 0;
			for(int x = 0; x < numCol; x++, index++)
				{
				nodes[x][y] = new MyNode(index, posX, posY, 8);
				indexedNodes.add(nodes[x][y]);
				posX += tileWidth;
				}

			posY += tileHeight;
			}

		for(int y = 0; y < numRow; y++, index++)
			{
			for(int x = 0; x < numCol; x++, index++)
				{
				if (tiles[x][y])
					{
					continue;
					}

				if (x - 1 >= 0 && !tiles[x - 1][y])
					{
					nodes[x][y].getConnections().add(new DefaultConnection<MyNode>(nodes[x][y], nodes[x - 1][y]));
					}

				if (x + 1 < numCol && !tiles[x + 1][y])
					{
					//					System.out.println("Connection x +1 :");
					//					System.out.println("Node[" + x + "][" + y + "] : X : " + nodes[x][y].getX() + " Y : " + nodes[x][y].getY());
					//					System.out.println("Node[" + (x + 1) + "][" + y + "] : X : " + nodes[x + 1][y].getX() + " Y : " + nodes[x + 1][y].getY());
					nodes[x][y].getConnections().add(new DefaultConnection<MyNode>(nodes[x][y], nodes[x + 1][y]));
					}

				if (y - 1 >= 0 && !tiles[x][y - 1])
					{
					nodes[x][y].getConnections().add(new DefaultConnection<MyNode>(nodes[x][y], nodes[x][y - 1]));
					}

				if (y + 1 < numRow && !tiles[x][y + 1])
					{

					nodes[x][y].getConnections().add(new DefaultConnection<MyNode>(nodes[x][y], nodes[x][y + 1]));
					}

				if ((y + 1 < numRow && x + 1 < numCol) && !tiles[x + 1][y + 1])
					{

					nodes[x][y].getConnections().add(new DefaultConnection<MyNode>(nodes[x][y], nodes[x + 1][y + 1]));
					}

				if ((y + 1 < numRow && x - 1 >= 0) && !tiles[x - 1][y + 1])
					{

					nodes[x][y].getConnections().add(new DefaultConnection<MyNode>(nodes[x][y], nodes[x - 1][y + 1]));
					}

				if ((y - 1 >= 0 && x - 1 >= 0) && !tiles[x - 1][y - 1])
					{

					nodes[x][y].getConnections().add(new DefaultConnection<MyNode>(nodes[x][y], nodes[x - 1][y - 1]));
					}

				if ((y - 1 >= 0 && x + 1 < numCol) && !tiles[x + 1][y - 1])
					{

					nodes[x][y].getConnections().add(new DefaultConnection<MyNode>(nodes[x][y], nodes[x + 1][y - 1]));
					}

				//				System.out.println("Node[" + x + "][" + y + "] : X : " + nodes[x][y].getX() + " Y : " + nodes[x][y].getY());
				//
				//				System.out.println("Node : X : " + x + " Y : " + y);
				//				for(Connection<MyNode> c:nodes[x][y].getConnections())
				//					{
				//					System.out.println("X : " + c.getToNode().getX() + " Y : " + c.getToNode().getY());
				//					}

				}
			}

		return new MyGraph(indexedNodes);

		}

	private boolean[][] tabIsSolid(int numRow, int numCol, int tileWidth, int tileHeight)
		{

		boolean[][] tab = new boolean[numCol][numRow];

		int index = 0;
		for(int y = 0; y < numRow; y++)
			{
			for(int x = 0; x < numCol; x++, index++)
				{
				tab[x][y] = false;
				}

			}

		for(Tile obstacle:listObstacle)
			{
			int xObs = obstacle.getX() / obstacle.getWidth();
			int yObs = obstacle.getY() / obstacle.getHeight();

			tab[xObs][yObs] = true;
			}

		return tab;
		}

	//TODO
	private int getIndex(int x, int y)
		{
		int height = 32;
		int width = 32;
		int nbCol = 20;
		int nbRow = 20;
		int index = 0;
		index += (y / height) * nbRow;
		index += (x / width);
		return index;
		}
	}

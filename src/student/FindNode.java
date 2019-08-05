package student;

import java.util.HashMap;
import java.util.Map;

import game.Edge;
import game.Node;
import game.Tile;

public class FindNode extends Node{

    /** The unique numerical identifier of this Node */
    private final long id;
    /** Represents the edges outgoing from this Node */

    private final Map<Node,Edge> outgoing;
    private final Map<Node,Edge> incoming;
    
    /** Extra state that belongs to this node */
    private final Tile tile;
    
	FindNode(long givenId, Tile t) {
		this.id= givenId;
        this.outgoing = new HashMap<>();
        this.incoming = new HashMap<>();
        
        this.tile= t;
	}

}
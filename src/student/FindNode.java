package student;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import game.Edge;
import game.Node;
import game.Tile;

public class FindNode implements graph.Node<FindNode, FindEdge>{
	
    private final Map<FindNode,FindEdge> outgoing;
    private final Map<FindNode,FindEdge> incoming;
    private final long id;
	
	public FindNode(long uniqueID) {
		this.id=uniqueID;
        this.outgoing = new HashMap<>();
        this.incoming = new HashMap<>();
	}
	
	/**Return the FindEdge of this FindNode that connects to FindNode q. 
     * Throw an IllegalArgumentException if FindEdge doesn't exist */
    public FindEdge getFindEdge(FindNode q) {
    	if (!this.outgoing.containsKey(q))
    		throw new IllegalArgumentException();
    	return this.outgoing.get(q);
    }
    
    public void addFindEdge(FindEdge e) {
    	if (e.source() == this) {
    		outgoing.put(e.target(), e);
    		incoming.put(e.target(), e.twin());
    	}
    	else if (e.target() == this) {
    		outgoing.put(e.source(), e.twin());
    		incoming.put(e.target(), e);
    	}
    	else throw new IllegalArgumentException("can only add an FindEdge connected to the FindNode");
    }
    
    /**  Return the unique Identifier of this FindNode. */
    public long getId() {
        return id;
    }
	
    /** Return true iff ob is a FindNode with the same id as this one. */
    @Override public boolean equals(Object ob) {
        if (ob == this) return true;
        if (!(ob instanceof FindNode)) return false;
        return id == ((FindNode)ob).id;
    }
    
    @Override public int hashCode() {
        return Objects.hash(id);
    }

	@Override
	public Map<FindNode, ? extends FindEdge> outgoing() {
		return outgoing;
	}

	@Override
	public Map<FindNode, ? extends FindEdge> incoming() {
		return incoming;
	}



}
package student;

import java.util.Map;

import game.Edge;
import game.Node;
import graph.Graph;

public class FindEdge implements graph.LabeledEdge<FindNode, FindEdge,Integer>{

    private final FindNode src; /** The Node this edge is coming from */
    private final FindNode dest; /** The node this edge is going to */
    public final int length; /** The length of this edge */
    private FindEdge twin;

    /** Constructor: an FindEdge from src to dest with length length. */
    public FindEdge(FindNode src, FindNode dest, int length) {
    	this(src,dest,length,null);
    	twin = new FindEdge(dest,src,length,this);
    }

    private FindEdge(FindNode src, FindNode dest, int length, FindEdge twin) {
        this.src    = src;
        this.dest   = dest;
        this.length = length; 
        this.twin   = twin;
    }
    
    /** Constructor: an FindEdge that is isomorphic to isomorphism. */
    public FindEdge(FindEdge e, Map<FindNode,FindNode> isomorphism) {
        this(isomorphism.get(e.src), isomorphism.get(e.dest), e.length);
    }

    /** Return the <tt>FindNode</tt> on this <tt>FindEdge</tt> that is not equal to <tt>n</tt>.
     * Throws an <tt>IllegalArgumentException</tt> if <tt>n</tt> is not in this <tt>FindEdge</tt>.
     * @param n A <tt>FindNode</tt> on this <tt>FindEdge</tt>
     * @return The <tt>FindNode</tt> not equal to <tt>n</tt> on this <tt>FindEdge</tt>
     */
    public FindNode getOther(FindNode n) {
        if (src == n)  return dest;
        if (dest == n) return src;
        throw new IllegalArgumentException("getOther: FindEdge must contain provided FindNode");

    }
    
    public FindEdge twin() {
    	return twin;
    }
    
    /** Return the length of this <tt>FindEdge</tt> */
    public int length() {
        return length;
    }

    /** Return the source of this FindEdge. */
    public FindNode getSource() {
        return src;
    }

    /** Return destination of FindEdge */
    public FindNode getDest() {
        return dest;
    }

	@Override
	public FindNode source() {
		return src;
	}

	@Override
	public FindNode target() {
		return this.dest;
	}

	public Integer label() {
		return this.length;
	}

}

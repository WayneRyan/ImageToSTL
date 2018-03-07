
public class Triangle {
	public Vertex v1,v2,v3;
	
	/**
	 * @param v1 set Vertex one
	 * @param v2 set Vertex two
	 * @param v3 set Vertex three
	 */
	public Triangle(Vertex v1, Vertex v2, Vertex v3){
		this.v1=v1;
		this.v2=v2;
		this.v3=v3;
		
	}
	
	/**
	 * @return a vector perpendicular to the triangle with unit length following right hand rule
	 */
	public  Vertex getNormal(){
		Vertex U = v3.subtract(v1);
		Vertex v = v2.subtract(v1);
		double x = v.z*U.y-v.y*U.z;
		double y = v.x*U.z-v.z*U.x;
		double z = v.y*U.x-v.x*U.y;
		double length= Math.sqrt(x*x+y*y+z*z);
		x=x/length;
		y=y/length;
		z=z/length;
		return new Vertex(x,y,z);
	}
	
	public String toScaledString(double sX, double sY, double sZ){
		Vertex normal = getNormal();
		String output= "   "+ "facet normal " + normal + "\n";
		output+= "   "+"   "+"outer loop \n";
		output+= "   "+"   "+"   "+""+v3.toScaledString(sX,sY,sZ)+ "\n";
		output+= "   "+"   "+"   "+""+ v1.toScaledString(sX,sY,sZ)+ "\n";
		output+= "   "+"   "+"   "+""+v2.toScaledString(sX,sY,sZ)+ "\n";
		output+= "   "+"   "+"endloop \n";
		output+= "   "+"endfacet";
		return output;
	}
	
	public String toString(){
		Vertex normal = getNormal();
		String output= "facet normal " + normal.x+ " " + normal.y + " " + normal.z + "\n";
		output+= "outer loop \n";
		output+= ""+v1.toString()+ "\n";
		output+= ""+ v2.toString()+ "\n";
		output+= ""+v3.toString()+ "\n";
		output+= "endloop \n";
		output+= "endfacet";
		return output;
	}
	
	/**
	 * @return true if none of the vertices are the same
	 */
	public boolean hasArea(){
		if (v1.equals(v2))return false;
		if (v1.equals(v3))return false;
		if (v3.equals(v2))return false;
		return true;
	}
	
	/**
	 * @param t a Triangle
	 * @return true if the triangles have same vertices without respect to order
	 */
	public boolean equals(Triangle t){
		if(t==null)return false;
		if(!t.has(v1))return false;
		if(!t.has(v2))return false;
		if(!t.has(v3))return false;
        if(!has(t.v1))return false;		
        if(!has(t.v2))return false;		
        if(!has(t.v3))return false;		
		return true;
	}
	
	/**
	 * @param v a Vertex
	 * @return true if the triangle contains the vertex
	 */
	private boolean has(Vertex v){
		return v1==v || v2==v || v3==v;
	}

}

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.vecmath.Point3f;


public class Vertex {
	
	//breaking encapsulation, simple container class
	public double x,y,z;
	/**
	 * @param x sets x value
	 * @param y sets y value
	 * @param z sets z value
	 */
	public Vertex(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public String toString(){
		return formatDecimal(x) + " "+ formatDecimal(y) +" "+ formatDecimal(z);
	}
	
	public String toScaledString(double sX, double sY, double sZ){
		return "vertex "+ formatDecimal(x*sX) + " "+ formatDecimal((1-y)*sY) +" "+ formatDecimal(z*sZ);
	}
	
	private String formatDecimal(double d){
		DecimalFormat formatter = new DecimalFormat("0.0000000E000");
		String formatted = formatter.format(d);
		int startLength = 9;
		if(formatted.charAt(0) == '-')startLength++;
		String start = formatted.substring(0, startLength);
		String end = formatted.substring(startLength+1);
		if(end.charAt(0)!='-'){
			end = "+" + end;
		}
		start += "e"+end;
		return start;
	}
	
	public void deepCopy(Vertex v){
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public static Vertex getAverage(ArrayList<Vertex> all){
		Vertex retVal = new Vertex(0,0,0);
		for(Vertex v : all){
			retVal.x += v.x;
			retVal.y += v.y;
			retVal.z += v.z;
		}
		retVal.x /= all.size();
		retVal.y /= all.size();
		retVal.z /= all.size();
		return retVal;
	}
	
	/**
	 * Precondition: Normalized (range 0-1) beforehand
	 * @return Point3f with the model being centered around 0
	 */
	public Point3f getPoint3f(){
		// Note: in image coordinates y increases top to bottom and (0,0)=top left
		//       in model coordinates y increases bottom to top and (0,0)=mid center
		return new Point3f((float)(x-0.5), (float)(0.5-y), (float)(z-0.5));
	}
	
	/**
	 * @param shift sets the offset amount
	 * @param scale sets the amount to scale by
	 */
	public void normalize(Vertex shift, Vertex scale){
		this.x -= shift.x;
		this.y -= shift.y;
		this.z -= shift.z;
		this.x *= scale.x;
		this.y *= scale.y;
		this.z *= scale.z;
		
	}
	
	/**
	 * Postcondition: this and other unmodified
	 * @param other a valid Vertex 
	 * @return Vertex with this - other  (Order matters)
	 */
	public Vertex subtract( Vertex other){
		return new Vertex(x-other.x, y-other.y,z-other.z);
	}

}

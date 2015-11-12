package kmeans;

import java.util.ArrayList;

public class Cluster {
	private Ponto center;
	private ArrayList<Ponto> contains;
	private Double variance;
	
	//Constructor
	
	

	public Cluster(ArrayList <Ponto> ar){
		int index = (int)(Math.random()*150);
		Ponto pt = ar.get(index);
		
		this.center = pt;
		this.contains = new ArrayList<Ponto>();
		
	}
	
	//Adds Ponto to itself
	
	public void addPonto(Ponto p){
		this.contains.add(p);
	}
	
	//Recalculate new center based on its points mean 
	
	public void CalculateNewCenter(){
		int size = this.contains.size();
		if(size == 0){
			//System.out.println("Empty Cluster");
		}
		else{
		double sumX = 0;
		double sumY = 0;
		double sumZ = 0;
		double sumW = 0;
		for (int i = 0; i < size ; i++){
			sumX += this.contains.get(i).getX();
			sumY += this.contains.get(i).getY();
			sumZ += this.contains.get(i).getZ();
			sumW += this.contains.get(i).getW();
		}
		this.center.setX(sumX/size);
		this.center.setY(sumY/size);
		this.center.setZ(sumZ/size);
		this.center.setW(sumW/size);
	}
	}
	
	//Verify if the Cluster has changed position
	
	public boolean hasChangedPosition(){
		double oldx = this.center.getX();
		double oldy = this.center.getY();
		double oldz = this.center.getZ();
		double oldw = this.center.getW();
		this.CalculateNewCenter();
		if(this.getCenter().getX() == oldx && this.getCenter().getY() == oldy &&
			this.getCenter().getZ() == oldz && this.getCenter().getW() == oldw){
			return false;
		}
		return true;
	}
	
	public void calculateVariance(){
		if(this.getContains().size() == 0){
			this.variance = 0.0;
		}
		else{
			Double sum = 0.0 ;
			for (int i = 0; i < this.getContains().size(); i++) {
				sum += AuxiliaryFunctions.calculateEuclideanDistance4Dimensions(this.getCenter(), this.getContains().get(i));
			}
			this.variance = sum/this.getContains().size();
		}
	}
	// Getters & setters
	
	public Ponto getCenter() {
		return center;
	}
	public void setCenter(Ponto center) {
		this.center = center;
	}
	public ArrayList<Ponto> getContains() {
		return contains;
	}
	public void setContains(ArrayList<Ponto> contains) {
		this.contains = contains;
	}
	
	public Double getVariance() {
		return variance;
	}

	public void setVariance(Double variance) {
		this.variance = variance;
	}
	
}

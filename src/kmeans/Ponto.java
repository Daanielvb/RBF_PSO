package kmeans;

import java.util.ArrayList;


public class Ponto {
		private double x;
		private double y;
		private double z;
		private double w;
		private ArrayList<Double> distances;
		
		// Constructor 
		
		public Ponto(double x,double y,double z,double w){
			this.x = x;
			this.y = y;
			this.z = z;
			this.w = w;
			this.distances = new ArrayList<Double>();
		}
		
		public Ponto(ArrayList<Double> entrada){
			this.x = entrada.get(0);
			this.y = entrada.get(1);
			this.z = entrada.get(2);
			this.w = entrada.get(3);
			this.distances = new ArrayList<Double>();
		}
		
		// Adds a new distance to the relativeDistances Array
		
		public void addRelativeDistances(double dist){
			this.distances.add(new Double(dist));
		}
		
		// Auxiliary functions to get smallest distance element and index 
		
		public double getClosest(){
			double min = this.distances.get(0);
			for(int i = 0; i < this.distances.size(); i++){
				if(this.distances.get(i) < min){
					min = distances.get(i);
				}
			}
			return min;
		}
		
		public int getClosestElementIndex(){
			double min = this.distances.get(0);
			int index = 0;
			for(int i = 0; i < this.distances.size(); i++){
				if(this.distances.get(i) < min){
					min = distances.get(i);
					index = distances.indexOf(min);
				}
			}
			return index;
		}
		
		public String printCoordinates(){
			return " X:" + this.x + " Y:" + this.y + " Z: " + this.z + " W:" + this.w;
		}
		// Getters & setters
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		
		public double getZ() {
			return z;
		}

		public void setZ(double z) {
			this.z = z;
		}

		public double getW() {
			return w;
		}

		public void setW(double w) {
			this.w = w;
		}

		public ArrayList<Double> getDistances() {
			return distances;
		}
		public void setDistances(ArrayList<Double> distances) {
			this.distances = distances;
		}

}

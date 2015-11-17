package kmeans;

public class AuxiliaryFunctions {
	
	/*
	 * Euclidean distance in 4 dimensions between Ponto
	 */
	public static double calculateEuclideanDistance4Dimensions(Ponto v, Ponto w){
		double result = Math.sqrt(Math.pow((w.getX() - v.getX()),2) + Math.pow((w.getY() - v.getY()),2) +
						Math.pow((w.getZ() - v.getZ()),2) + Math.pow((w.getW() - v.getW()),2) );
		return result;
	}
	
	/*
	 * Eucliadean distance in 4 dimensions between Ponto and Cluster
	 */
	public static double calculateEuclideanDistance4Dimensions(Ponto v, Cluster c){
		double result = Math.sqrt(Math.pow((c.getCenter().getX() - v.getX()),2) + Math.pow((c.getCenter().getY() - v.getY()),2) +
						Math.pow((c.getCenter().getZ() - v.getZ()),2) + Math.pow((c.getCenter().getW() - v.getW()),2) );
		return result;
	}
	
	/*
	 * Calculates RBF weights between input and intermediate layers
	 */
	public static double calculateRBFWeights(Ponto centro,Ponto p,double varianca ){
		return Math.exp(-Math.pow(calculateEuclideanDistance4Dimensions(centro,p),2)/2* (Math.pow(varianca,2)));
	}
	
	
	
}

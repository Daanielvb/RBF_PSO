package kmeans;

public class AuxiliaryFunctions {
	
	/*
	 * Distancia euclidiana de 4 dimensões entre pontos
	 */
	public static double calculateEuclideanDistance4Dimensions(Ponto v, Ponto w){
		double result = Math.sqrt(Math.pow((w.getX() - v.getX()),2) + Math.pow((w.getY() - v.getY()),2) +
						Math.pow((w.getZ() - v.getZ()),2) + Math.pow((w.getW() - v.getW()),2) );
		return result;
	}
	
	/*
	 * Distancia euclidianda de 4 dimensões entre um ponto e um Cluster
	 */
	public static double calculateEuclideanDistance4Dimensions(Ponto v, Cluster c){
		double result = Math.sqrt(Math.pow((c.getCenter().getX() - v.getX()),2) + Math.pow((c.getCenter().getY() - v.getY()),2) +
						Math.pow((c.getCenter().getZ() - v.getZ()),2) + Math.pow((c.getCenter().getW() - v.getW()),2) );
		return result;
	}
	
	/*
	 * Formula de calculo dos pesos entre camada de entrada e intermediaria 
	 * da RBF a ser usada após o Kmeans
	 */
	public static double calculateRBFWeights(Ponto centro,Ponto p,double varianca ){
		return Math.exp(-Math.pow(calculateEuclideanDistance4Dimensions(centro,p),2)/2* (Math.pow(varianca,2)));
	}
	
	
	
}

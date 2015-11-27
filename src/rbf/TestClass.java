package rbf;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import kmeans.Kmeans;
import kmeans.Ponto;

public class TestClass {

	public static void main(String[] args) throws FileNotFoundException {
		LeitorEntradaRBF le = new LeitorEntradaRBF();
		le.lerEntrada();
		le.converterBaseParaPonto();
		Kmeans k = new Kmeans();
		k.kmeans(4, le.getConjuntoCaracteristicas());
		RBF rbf = new RBF(k);
		Ponto p = new Ponto(0.1,0.123,0.3,0.2);
		System.out.println(rbf.getRbfs().get(0).getVariance());
		System.out.println(rbf.getRbfs().get(0).getCenter().printCoordinates());
		rbf.calculateRBFWeights(p);
		List <Double> finalWeights = new ArrayList<Double>();
		finalWeights.add(0.23);
		finalWeights.add(0.77);
		finalWeights.add(0.221);
		finalWeights.add(0.99);
		System.out.println(rbf.calculateRBFOutput(le.getBaseEntrada().get(0).get(4),rbf.getIntermediateWeights(), finalWeights));
		

//		
//		

//		
//		System.out.println(k.printAllClustersCenter());
//		System.out.println(k.printAllClustersVariance());
//		
//		System.out.println(k.getClusters().get(0).getContains().size());
//		System.out.println(k.getClusters().get(1).getContains().size());
//		System.out.println(k.getClusters().get(2).getContains().size());
//		System.out.println(k.getClusters().get(3).getContains().size());
		
		
		
//		Pso pso = new Pso(5,20,3);
//		System.out.println(pso.gBest);
	}

}

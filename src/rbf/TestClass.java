package rbf;

import java.io.FileNotFoundException;

import pso.Pso;

public class TestClass {

	public static void main(String[] args) throws FileNotFoundException {
//		LeitorEntradaRBF le = new LeitorEntradaRBF();
//		le.lerEntrada();
//		le.converterBaseParaPonto();
//		
//		
//		Kmeans k = new Kmeans();
//		k.kmeans(4, le.getConjuntoCaracteristicas());
//		
//		System.out.println(k.printAllClustersCenter());
//		System.out.println(k.printAllClustersVariance());
//		
//		System.out.println(k.getClusters().get(0).getContains().size());
//		System.out.println(k.getClusters().get(1).getContains().size());
//		System.out.println(k.getClusters().get(2).getContains().size());
//		System.out.println(k.getClusters().get(3).getContains().size());
		
		
		
		Pso pso = new Pso(5,20,3);
		System.out.println(pso.gBest);
	}

}

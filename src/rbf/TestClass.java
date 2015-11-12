package rbf;

import kmeans.Kmeans;

public class TestClass {

	public static void main(String[] args) {
		LeitorEntradaRBF le = new LeitorEntradaRBF();
		le.lerEntrada();
		le.converterBaseParaPonto();
		Kmeans k = new Kmeans();
		k.setDatabase(le.getConjuntoCaracteristicas());
		k.generateKClusters(4);
		boolean flag = true;
		//Processamento do kmeans
		while (flag){
			k.calculateRelativeDistances();
			k.insertIntoClusters();
			flag = k.recalculateClusters();	
			if(flag){
			k.clearClusters();
			k.clearPontos();
			}
		}
		//Calculo da varianca
		for (int i = 0; i < k.getClusters().size(); i++) {
			k.getClusters().get(i).calculateVariance();
		}
		System.out.println(k.getClusters().get(0).getCenter().printCoordinates());
		System.out.println(k.getClusters().get(1).getCenter().printCoordinates());
//		System.out.println(k.getClusters().get(2).getCenter().printCoordinates());
//		System.out.println(k.getClusters().get(3).getCenter().printCoordinates());
//		System.out.println(k.getClusters().get(4).getCenter().printCoordinates());

		System.out.println(k.getClusters().get(0).getVariance());
		System.out.println(k.getClusters().get(1).getVariance());
//		System.out.println(k.getClusters().get(2).getVariance());
//		System.out.println(k.getClusters().get(3).getVariance());
//		System.out.println(k.getClusters().get(4).getVariance());

		
	}

}

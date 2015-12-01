package rbf;

import java.io.FileNotFoundException;

import kmeans.Kmeans;
import pso.Particula;

public class TestClass {

	public static void main(String[] args) throws FileNotFoundException {
		LeitorEntradaRBF le = new LeitorEntradaRBF();
		le.lerEntrada();
		le.converterBaseParaPonto();
		Kmeans k = new Kmeans();
		k.kmeans(4, le.getConjuntoCaracteristicas());
		RBF rbf = new RBF(k,le);
		Particula p = new Particula(4);
		rbf.calculateErrorPercentage(p, 4);
		
		
		
	//	System.out.println(rbf.getLeitorEntrada().getConjuntoCaracteristicas().size());

	}

}

package rbf;

import java.util.ArrayList;
import java.util.List;

import kmeans.AuxiliaryFunctions;
import kmeans.Cluster;
import kmeans.Kmeans;
import kmeans.Ponto;
import pso.Particula;

public class RBF {
	private static List<Cluster> rbfs;
	private static LeitorEntradaRBF leitorEntrada;
	static int contador = 0;

	public RBF(Kmeans k, LeitorEntradaRBF leitor) {
		this.rbfs = k.getClusters();
		this.leitorEntrada = leitor;
		
	}

	/*
	 * Varre a base de entrada e o numero de neuronios para calcular os pesos
	 * intermediarios e posteriormente o % de erro
	 */
	public static double calculateErrorPercentage(Particula p, int numNeuronios) {
		double erro = 0;
		for (int j = 0; j < getLeitorEntrada().getConjuntoCaracteristicas().size(); j++) {
			// Array com valores temporarios de Y
			ArrayList<Double> Y = new ArrayList<Double>();
			for (int i = 0; i < numNeuronios; i++) {
				Y.add(AuxiliaryFunctions.calculateRBFWeights(getRbfs().get(i).getCenter(), // c
						getLeitorEntrada().getConjuntoCaracteristicas().get(j),
						getRbfs().get(i).getVariance()));
			}
			// Somatorio das multiplicacoes de Y x W
			double somatorio = 0;
			for (int z = 0; z < Y.size(); z++) {
				somatorio += p.posicao[z] * Y.get(z);
			}
			erro += Math.abs(getLeitorEntrada().getBaseEntrada().get(j).get(4) - somatorio);
			// System.out.println("erro antes do return: " + erro);
		}
		//contador++;
		
		erro = erro / getLeitorEntrada().getBaseEntrada().size();
		System.out.println("erro depois do return: " + erro);
		//System.out.println(contador);
		//return erro / getLeitorEntrada().getBaseEntrada().size();
		return erro;
	}

	public static List<Cluster> getRbfs() {
		return rbfs;
	}

	public void setRbfs(List<Cluster> rbfs) {
		this.rbfs = rbfs;
	}

	public static LeitorEntradaRBF getLeitorEntrada() {
		return leitorEntrada;
	}

	public void setLeitorEntrada(LeitorEntradaRBF leitorEntrada) {
		this.leitorEntrada = leitorEntrada;
	}

}

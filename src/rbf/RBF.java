package rbf;

import java.util.ArrayList;
import java.util.List;

import kmeans.AuxiliaryFunctions;
import kmeans.Cluster;
import kmeans.Kmeans;
import kmeans.Ponto;
import pso.Particula;


public class RBF {
	private List<Cluster> rbfs;
	private LeitorEntradaRBF leitorEntrada;


	
	
	public RBF(Kmeans k, LeitorEntradaRBF leitor){
		this.rbfs = k.getClusters();
		this.leitorEntrada = leitor;		
	}
	
	
	/*
	 * Varre a base de entrada e o numero 
	 * de neuronios para calcular os pesos intermediarios 
	 * e posteriormente o % de erro
	 */
	public double calculateErrorPercentage(Particula p, int numNeuronios){
		double erro = 0;
		for (int j = 0; j < this.getLeitorEntrada().getConjuntoCaracteristicas().size(); j++) {
			// Array com valores temporarios de Y
			ArrayList<Double> Y = new ArrayList<Double>();
			for (int i = 0; i < numNeuronios; i++) {
				Y.add(AuxiliaryFunctions.calculateRBFWeights(this.getRbfs().get(i).getCenter(), // c 
					this.getLeitorEntrada().getConjuntoCaracteristicas().get(j), this.getRbfs().get(i).getVariance()));							
			}
			// Somatorio das multiplicacoes de Y x W
			double somatorio = 0;
			for (int z = 0; z < Y.size(); z++) {
				somatorio += p.posicao[z] * Y.get(z);
			}
			erro += Math.abs(this.getLeitorEntrada().getBaseEntrada().get(j).get(4) - somatorio);	
		}
		return erro/this.getLeitorEntrada().getBaseEntrada().size();
	}


	public List<Cluster> getRbfs() {
		return rbfs;
	}


	public void setRbfs(List<Cluster> rbfs) {
		this.rbfs = rbfs;
	}

	
	public LeitorEntradaRBF getLeitorEntrada() {
		return leitorEntrada;
	}

	public void setLeitorEntrada(LeitorEntradaRBF leitorEntrada) {
		this.leitorEntrada = leitorEntrada;
	}

	
}

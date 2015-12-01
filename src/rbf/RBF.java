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
	

	private List<Double> intermediateWeights;
	private int count;
	private int fitness;
	
	
	public RBF(Kmeans k, LeitorEntradaRBF leitor){
		this.rbfs = k.getClusters();
		this.count = 0;
		this.fitness = 0;
		this.leitorEntrada = leitor;		
	}
	
	
	/*
	 * Varre a base de entrada e o numero 
	 * de neuronios para calcular os pesos intermediarios 
	 * e posteriormente o % de erro
	 */
	public void calculateErrorPercentage(Particula p, int numNeuronios){
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
			erro += this.getLeitorEntrada().getBaseEntrada().get(j).get(4) - somatorio;
			
		}
		System.out.println(erro/150);
	}
	/*
	 * Calculate intermediate Weights
	 */
	
	//Something here is suspicious, it always generates weights around 0.99
	// Precisa ficar mais claro quem é o ponto P para calculo do peso (Falar com o professor)
	public void calculateRBFWeights(Ponto p){  
		for (int i = 0; i < rbfs.size(); i++) {
			this.intermediateWeights.add(AuxiliaryFunctions.calculateRBFWeights(rbfs.get(i).getCenter(), p, rbfs.get(i).getVariance()));			
		}
	}
	
	/*
	 * HAVENT BEEN TESTED FROM HERE BELOW 
	 */
	
	
	/*
	 * Checks if the expected result was found after weights 
	 * There`s something suspicous around here too
	 */
	public boolean calculateRBFOutput(double expected,List<Double> intermediateWeights , List<Double> finalWeights ){
		double sum = 0;
		for (int i = 0; i < intermediateWeights.size(); i++) {
			sum += intermediateWeights.get(i) * finalWeights.get(i);
		}
		System.out.println(sum);
		if (sum == expected){
			return true;
		}
		return false;
	}

	/*
	 * Calculates the RBF fitness for certain input and finalWeights
	 * Intermediate weights are fixed after kmeans execution
	 */
	public void calculateFitness(LeitorEntradaRBF input, List<Double> finalWeights){
		boolean output;
		for (int i = 0; i < input.getBaseEntrada().size(); i++) {
			output = calculateRBFOutput(input.getBaseEntrada().get(i).get(4),this.intermediateWeights,finalWeights);
			if(output){
				this.count++;
			}
		}
		this.fitness = count/input.getTamanho();
	}
	
	
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public List<Cluster> getRbfs() {
		return rbfs;
	}


	public void setRbfs(List<Cluster> rbfs) {
		this.rbfs = rbfs;
	}


	public List<Double> getIntermediateWeights() {
		return intermediateWeights;
	}


	public void setIntermediateWeights(List<Double> intermediateWeights) {
		this.intermediateWeights = intermediateWeights;
	}
	
	public LeitorEntradaRBF getLeitorEntrada() {
		return leitorEntrada;
	}

	public void setLeitorEntrada(LeitorEntradaRBF leitorEntrada) {
		this.leitorEntrada = leitorEntrada;
	}

	
}

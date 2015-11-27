package rbf;

import java.util.ArrayList;
import java.util.List;

import kmeans.AuxiliaryFunctions;
import kmeans.Cluster;
import kmeans.Kmeans;
import kmeans.Ponto;


public class RBF {
	private List<Cluster> rbfs;
	private List<Double> intermediateWeights;
	private int count;
	private int fitness;
	
	
	public RBF(Kmeans k){
		this.rbfs = k.getClusters();
		this.count = 0;
		this.fitness = 0;
		this.intermediateWeights = new ArrayList<Double>();
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
	
}

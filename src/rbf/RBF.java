package rbf;

import java.util.ArrayList;
import java.util.List;

import kmeans.AuxiliaryFunctions;
import kmeans.Cluster;
import kmeans.Kmeans;
import kmeans.Ponto;
import pso.Particula;

public class RBF {
	public static List<Cluster> rbfs;
	public static LeitorEntradaRBF leitorEntrada;
	public static double contadorAcertos = 0;

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
		//System.out.println("zerou o erro");
		for (int j = 0; j < getLeitorEntrada().getConjuntoCaracteristicas().size(); j++) {
			// Array com valores temporarios de Y
			ArrayList<Double> Y = new ArrayList<Double>();
			for (int i = 0; i < numNeuronios; i++) {
				Y.add(AuxiliaryFunctions.calculateRBFWeights(getRbfs().get(i).getCenter(), // c
						getLeitorEntrada().getConjuntoCaracteristicas().get(j),
						getRbfs().get(i).getVariance()));
			}
			//System.out.println(Y.toString());
			// Somatorio das multiplicacoes de Y x W
			double somatorio = 0;
			for (int z = 0; z < Y.size(); z++) {
				somatorio += p.getPosicao()[z] * Y.get(z);
			}
			erro += Math.abs(getLeitorEntrada().getBaseEntrada().get(j).get(4) - somatorio); // ajeitar! erro esta incrementando para TODAS as amostras
			// System.out.println("erro antes do return: " + erro);
		}
		//contador++;
		
		erro = erro / getLeitorEntrada().getBaseEntrada().size();
		//System.out.println("erro depois do return: " + erro);
		//System.out.println(contador);
		//return erro / getLeitorEntrada().getBaseEntrada().size();
		return erro;
	}
	
	public static double calculateFinalClassification(Particula gBest, int numNeuronios) { // faz a classificacao final, apos a otimizacao de pesos da rbf
		/*
		 * somatorio = pesos * h(x), onde x= amostra, pesos vem de gBest. O procedimento eh realizado para toda a base de dados
		 * h(x) = exp(- (u - c)^2 / 2sigma^2)
		 */
		for (int j = 0; j < getLeitorEntrada().getConjuntoCaracteristicas().size(); j++) {
			double resultado = 0;
			
			ArrayList<Double> Y = new ArrayList<Double>();
			for (int i = 0; i < numNeuronios; i++) { // calcula h(x)
				
				Y.add(AuxiliaryFunctions.calculateRBFWeights(getRbfs().get(i).getCenter(), // c
						getLeitorEntrada().getConjuntoCaracteristicas().get(j),
						getRbfs().get(i).getVariance()));
			}
			// Somatorio das multiplicacoes de Y= h(x) x W da amostra
			double somatorio = 0;
			for (int z = 0; z < Y.size(); z++) {
				somatorio += gBest.getPosicao()[z] * Y.get(z); // w * h(x)
			}
			resultado = getLeitorEntrada().getBaseEntrada().get(j).get(4) - somatorio;
			//System.out.println(resultado);
			resultado = resultado <= 0 ? -1 : 1;
			// System.out.println(resultado);
			// System.out.println(getLeitorEntrada().getBaseEntrada().get(j).get(4) + "\n");
			if (getLeitorEntrada().getBaseEntrada().get(j).get(4) == resultado){
				contadorAcertos++;
			}
		}
		return (contadorAcertos/(getLeitorEntrada().getBaseEntrada().size())) * 100;
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

package pso;

import java.util.ArrayList;
import java.util.Random;

public class Particula {

	public float fitness = 0; // solucao encontrada (taxa de acerto do kmeans)
	public static float pBest = 0; // melhor solucao encontrada ate o momento (pbest no pseudo-c)
	public static double[] posicao; // vetor de pontos que definem a posicao da particula no hiperplano
	public static ArrayList<double[]> posicoes; // memoria de posicoes antes dos deslocamentos
	public static double[] velocidade; // velocidade da particula durante o deslocamento

	public Particula(int numNeuronios) { //construtor
		this.posicao = new double[numNeuronios]; // cria a posicao d-dimensional
		for (int i = 0; i < posicao.length; i++) { // preenche o vetor de posicoes d-dimensional da particula (usando apenas um vetor para todos os pontos no hiperplano)
			Random numRandom = new Random();
			posicao[i] = (numRandom.nextDouble() * 2) - 1; // intervalo [-1, 1] (George pediu pra deixar esse intervalo pro espaco...)
		}
		for (int i = 0; i < posicao.length; i++) {
			velocidade[i] = 0; // iniciando o vetor de velocidades com zero
		}
	}
	
	public static void calculaFitness(){
		// aqui colocar metodo estatico que traz a taxa de acerto do kmeans e faz fitness = txKmeans (esperar integracao)
	}
	
	public static void calculaVelocidade(){
		Random numRandom = new Random();
		// velocidade = velocidade + (c1 = 2) * numRandom.nextDouble() * (pBest - posicao) + (c2 = 2) * numRandom.nextDouble() * (gBest - posicao); <--- esperando George tirar uma duvida
	}
	
	public static void deslocarParticula(){
		posicoes.add(posicao); // adiciona a ultima posicao conhecida antes de atualizar
		// posicao = pBest + velocidade; <--- esperando George tirar uma duvida
	}
}
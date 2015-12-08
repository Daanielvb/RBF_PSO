package pso;

import java.util.Random;

public class Particula {

	public static double fitness; // solucao encontrada (taxa de erro medio da RBF)
	public static Particula pBest; // melhor solucao ja encontrada pela particula (posicao e fitness guardados)
	public static double[] posicao;
	public static double[] velocidade;

	public Particula(int numNeuronios) { //construtor
		posicao = new double[numNeuronios];
		velocidade = new double[numNeuronios];
		fitness = -1; // ja que a taxa de erro pode estar entre 0 e 2. necessario inicializar assim por causa da primeira comparacao do metodo define_pBest.
		for (int i = 0; i < posicao.length; i++) { 
			Random numRandom = new Random(); // preenche aleatoriamente o vetor de coordenadas d-dimensional da particula
			posicao[i] = (numRandom.nextDouble() * 2) - 1; // intervalo [-1, 1]
			velocidade[i] = numRandom.nextDouble() * 10;
		}
	}
	
	public static void calculaVelocidade(){
		// da equacao v[] = v[] + c1 * rand() * (pbest[] - present[]) + c2 * rand() * (gbest[] - present[])
		Random random = new Random();
		double c1 = 2 * random.nextDouble();
		double c2 = 2 * random.nextDouble();
		
		double[] d_pBest = new double [posicao.length]; // vetor pBest
		double[] d_gBest = new double [posicao.length]; // vetor gBest
		
		//decidi fazer varios lacos for somente para melhorar a legibilidade da equacao
		
		for (int i = 0; i < d_pBest.length; i++) { // subtrai os vetores pBest e posicao utilizando o metodo do triangulo e multiplica pelo fator de aprendizado c1
			d_pBest[i] = posicao[i] * (-1);
			d_pBest[i] += pBest.posicao[i];
			d_pBest[i] += c1;
		}
		
		for (int i = 0; i < d_gBest.length; i++) { // subtrai os vetores gBest e posicao utilizando o metodo do triangulo e multiplica pelo fator de aprendizado c2
			d_gBest[i] = posicao[i] * (-1);
			d_gBest[i] += Pso.gBest.posicao[i];
			d_gBest[i] += c2;
		}
		
		for (int i = 0; i < d_pBest.length; i++) { // soma os vetores d_pBest e d_gBest e salva em d_pBest como um vetor resultante
			d_pBest[i] += d_pBest[i] + d_gBest[i];
		}
		
		for (int i = 0; i < velocidade.length; i++) { // finalmente, soma os vetores d_pBest velocidade
			velocidade[i] += d_pBest[i];
		}
	}
	
	public static void deslocarParticula(){
		for (int i = 0; i < posicao.length; i++) {
			posicao[i] = pBest.posicao[i] + velocidade[i];
		}
	}
}
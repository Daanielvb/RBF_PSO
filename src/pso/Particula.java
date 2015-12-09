package pso;

import java.util.Arrays;
import java.util.Random;

public class Particula {

	private double fitness; // solucao encontrada (taxa de erro medio da RBF)
	private Particula pBest; // melhor solucao ja encontrada pela particula (posicao e fitness guardados)
	private double[] posicao;
	private double[] velocidade;
	

	public Particula(int numNeuronios) { //construtor
		
		this.posicao = new double[numNeuronios];
		this.velocidade = new double[numNeuronios];
		this.fitness = 3; // ja que a taxa de erro pode estar entre 0 e 2. necessario inicializar assim por causa da primeira comparacao do metodo define_pBest.
		for (int i = 0; i < this.posicao.length; i++) { 
			Random numRandom = new Random(); // preenche aleatoriamente o vetor de coordenadas d-dimensional da particula
			this.posicao[i] = (numRandom.nextDouble() * 2) - 1; // intervalo [-1, 1]
			this.velocidade[i] = 0;
		}
	}
	
	public void calculaVelocidade(){
		// da equacao v[] = v[] + c1 * rand() * (pbest[] - present[]) + c2 * rand() * (gbest[] - present[])
		Random random = new Random();
		double c1 = 2 * random.nextDouble();
		double c2 = 2 * random.nextDouble();
		
		double[] d_pBest = new double [this.posicao.length]; // vetor pBest
		double[] d_gBest = new double [this.posicao.length]; // vetor gBest
		
		//decidi fazer varios lacos for para melhorar a legibilidade da equacao
		
		for (int i = 0; i < d_pBest.length; i++) { // subtrai os vetores pBest e posicao utilizando o metodo do triangulo e multiplica pelo fator de aprendizado c1
			d_pBest[i] = this.posicao[i] * (-1);
			d_pBest[i] = d_pBest[i] + pBest.getPosicao()[i];
			d_pBest[i] = d_pBest[i] + c1;
		}
		
		for (int i = 0; i < d_gBest.length; i++) { // subtrai os vetores gBest e posicao utilizando o metodo do triangulo e multiplica pelo fator de aprendizado c2
			d_gBest[i] = posicao[i] * (-1);
			d_gBest[i] += Pso.gBest.posicao[i];
			d_gBest[i] += c2;
		}
		
		for (int i = 0; i < d_pBest.length; i++) { // soma os vetores d_pBest e d_gBest e salva em d_pBest como um vetor resultante
			d_pBest[i] += d_pBest[i] + d_gBest[i];
		}
		
		for (int i = 0; i < this.velocidade.length; i++) { // finalmente, soma os vetores d_pBest velocidade
			this.velocidade[i] += d_pBest[i];
		}
		
		/*System.out.println("vetor d_pBest" + Arrays.toString(d_pBest));
		System.out.println("vetor d_gBest" + Arrays.toString(d_gBest));
		System.out.println("vetor velocidade" + Arrays.toString(velocidade));
		System.out.println("vetor pBest posicao" + Arrays.toString(pBest.posicao));
		System.out.println("vetor gBest posicao" + Arrays.toString(Pso.gBest.posicao));*/
	}
	
	public void deslocarParticula(){
		for (int i = 0; i < this.posicao.length; i++) {
			this.posicao[i] = this.pBest.posicao[i] + this.velocidade[i];
		}
	}
	
	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public Particula getpBest() {
		return pBest;
	}

	public void setpBest(Particula pBest) {
		this.pBest = pBest;
	}

	public double[] getPosicao() {
		return posicao;
	}

	public void setPosicao(double[] posicao) {
		this.posicao = posicao;
	}

	public double[] getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(double[] velocidade) {
		this.velocidade = velocidade;
	}
}
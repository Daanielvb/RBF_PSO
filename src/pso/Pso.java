package pso;

import java.util.ArrayList;

/* PSO: tecnica de otimizacao do tipo estocastica (aleatoria) inspirada no comportamento social
 * de . Possui muitas similaridades com tecnicas de evolucao computacional, como os Algoritmos
 * Geneticos.
 * 
 * http://www.swarmintelligence.org/
 * 
 * O PSO possui uma populacao (enxame) de solucoes candidatas (as particulas). As particulas
 * movem-se no espaco de busca de acordo com algumas formulas simples. Os movimentos das par-
 * ticulas sao guiados pela sua melhor posicao conhecida no espaco de busca, assim como a melhor
 * posicao conhecida do enxame. O pocesso e repetido e, e esperado, mas nao garantido, que uma
 * solucao satisfatoria sera, eventualmente, descoberta.
 * 
 * https://en.wikipedia.org/wiki/Particle_swarm_optimization
 * 
 * ---------------------------------------------------------------------------------------------
 * Pseudo-codigo:
 * 
 * For each particle 
 * 	Initialize particle
 * END
 *	
 * Do
 *	For each particle 
 *		Calculate fitness value
 *	    If the fitness value is better than the best fitness value (pBest) in history
 *	        set current value as the new pBest
 *	End
 *	
 *	Choose the particle with the best fitness value of all the particles as the gBest
 *	For each particle 
 *	   Calculate particle velocity according equation (a)
 *	   Update particle position according equation (b)
 *   End 
 * While maximum iterations or minimum error criteria is not attained
 * 
 * http://www.swarmintelligence.org/tutorials.php
 * 
 * ---------------------------------------------------------------------------------------------
 * Equacoes:
 * - Velocidade (a):
 * 		v[] = v[] + c1 * rand() * (pbest[] - present[]) + c2 * rand() * (gbest[] - present[])
 * - Posicao (b):
 *  	present[] = persent[] + v[]
 *  
 *  v[] is the particle velocity, persent[] is the current particle (solution).
 *  pbest[] *  and gbest[] are defined as stated before.
 *  rand () is a random number between (0,1).
 *  c1, c2 are learning factors. usually c1 = c2 = 2.
 *   
 *  http://www.swarmintelligence.org/tutorials.php
 */
public class Pso {

	public static float gBest; // Melhor valor (fitness/solucao) do enxame.
	public static Particula particulaVencedora;
	private static ArrayList<Particula> particulas;

	private static void criaEnxame(int numParticulas, int numNeuronios) {
		// cria um enxame com n particulas (definido pelo usuario; geralmente
		// entre 20 e 50) que sao d-dimensionais (definido pelo numero de
		// neurinios da camada escondida da rbf).
		for (int i = 0; i < numParticulas; i++) {
			Particula p = new Particula(numNeuronios); // ja com a posicao
			// aleatoria
			particulas.add(p);
		}
	}

	public static void defineVencedor(int i) {
		particulaVencedora = particulas.get(i);
	}

	public static void defineMelhorGlobal() { // auto-explicativo
		gBest = particulas.get(0).pBest;
		for (int i = 0; i < particulas.size(); i++) {
			if (particulas.get(i).pBest > gBest) {
				gBest = particulas.get(i).pBest;
				defineVencedor(i); // define a particula vencedora
			}
		}
	}

	public static void iniciaPSO(int numIteracoes, int numParticulas, int numNeuronios) {
		criaEnxame(numParticulas, numNeuronios);
		for (int i = 0; i < numIteracoes; i++) {
			for (int j = 0; j < particulas.size(); j++) {
				Particula.calculaFitness();
				particulas.get(j).pBest = (particulas.get(j).pBest < particulas.get(j).fitness)
						? particulas.get(j).fitness : particulas.get(j).pBest;
			}
			defineMelhorGlobal();
			for (int j = 0; j < particulas.size(); j++) {
				particulas.get(j).calculaVelocidade();
				particulas.get(j).deslocarParticula();
			}
		}
	}
}
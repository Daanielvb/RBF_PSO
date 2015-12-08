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

	public static Particula gBest; // Particula com melhor fitness do enxame (posicao e fitness guardados)
	public static ArrayList<Particula> enxame;

	private static void criaEnxame(int numParticulas, int numNeuronios) {
		enxame = new ArrayList<Particula>();
		// cria um enxame com n particulas (definido pelo usuario; geralmente
		// entre 20 e 50) que sao d-dimensionais (definido pelo numero de
		// neurinios da camada escondida da rbf).
		for (int i = 0; i < numParticulas; i++) {
			Particula p = new Particula(numNeuronios); // ja com a posicao aleatoria
			enxame.add(p);
		}
	}

	public static void define_gBest() { // define quem eh a particula gBest e guarda uma copia
		for (int i = 0; i < enxame.size(); i++) {
			if (enxame.get(i).fitness < gBest.fitness) {
				gBest = new Particula(enxame.size());
				gBest.posicao = enxame.get(i).posicao.clone();
				gBest.fitness = enxame.get(i).fitness;
			}
		}
	}
	
	public static void define_pBest(Particula p, int numNeuronios){
		if (p.fitness < p.pBest.fitness) {
			p.pBest = new Particula(numNeuronios);
			p.pBest.posicao = p.posicao.clone();
			p.pBest.fitness = p.fitness;
		}
	}

	public Pso(int numNeuronios, int numEpocas, int numParticulas) {
		criaEnxame(numParticulas, numNeuronios);
		gBest = enxame.get(0);
		for (int i = 0; i < numEpocas; i++) {
			for (int j = 0; j < enxame.size(); j++) {
				enxame.get(j).fitness = rbf.RBF.calculateErrorPercentage(enxame.get(j), numNeuronios); // calcula o fitness da particula
				define_pBest(enxame.get(j), numNeuronios);
			}
			define_gBest();
			for (int j = 0; j < enxame.size(); j++) {
				enxame.get(j).calculaVelocidade();
				enxame.get(j).deslocarParticula();
			}
		}
	}
	
	public Particula FuncaoGeral(int numNeuronios, int numEpocas, int numParticulas) {
		Pso abelhas = new Pso(numNeuronios, numEpocas, numParticulas);
		return gBest;
	}
}
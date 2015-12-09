package pso;

import java.util.ArrayList;
import java.util.Arrays;

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
	private ArrayList<Particula> enxame;
	private final double v_min = -2;
	private final double v_max = 2;
	
	public Pso(int numNeuronios, int numEpocas, int numParticulas) {
		criaEnxame(numParticulas, numNeuronios);
		gBest = new Particula(numNeuronios);
		gBest.setFitness(enxame.get(0).getFitness());
		gBest.setPosicao(enxame.get(0).getPosicao().clone());
		System.out.println(Arrays.toString(gBest.getPosicao()));
		for (int i = 0; i < enxame.size(); i++) {
			enxame.get(i).setpBest(enxame.get(i));
		}
		
		for (int i = 0; i < numEpocas; i++) {
			for (int j = 0; j < this.enxame.size(); j++) {
				this.enxame.get(j).setFitness(rbf.RBF.calculateErrorPercentage(this.enxame.get(j), numNeuronios)); // calcula o fitness da particula
				define_pBest(this.enxame.get(j));
			}
			define_gBest();
			for (int j = 0; j < this.enxame.size(); j++) {
				this.enxame.get(j).calculaVelocidade();
				for (int k = 0; k < this.enxame.get(j).getVelocidade().length; k++) {
					if (this.enxame.get(j).getVelocidade()[k] < v_min){
						this.enxame.get(j).getVelocidade()[k] = v_min;
						//System.out.println("entrou if 1 " + enxame.get(j).getVelocidade()[k]);
					}else if (this.enxame.get(j).getVelocidade()[k] > v_max){
						this.enxame.get(j).getVelocidade()[k] = v_max;
						//System.out.println("entrou if 2 " + enxame.get(j).getVelocidade()[k]);
					}
				}
				this.enxame.get(j).deslocarParticula();
			}
		}
	}

	private void criaEnxame(int numParticulas, int numNeuronios) {
		this.enxame = new ArrayList<Particula>();
		// cria um enxame com n particulas (definido pelo usuario; geralmente
		// entre 20 e 50) que sao d-dimensionais (definido pelo numero de
		// neurinios da camada escondida da rbf).
		Particula p;
		for (int i = 0; i < numParticulas; i++) {
			p = new Particula(numNeuronios); // ja com a posicao aleatoria
			this.enxame.add(p);
		}
	}

	public void define_gBest() { // define quem eh a particula gBest e guarda uma copia
		for (int i = 0; i < this.enxame.size(); i++) {
			if (this.enxame.get(i).getFitness() < gBest.getFitness()) {
				gBest.setPosicao(this.enxame.get(i).getPosicao().clone());
				gBest.setFitness(this.enxame.get(i).getFitness());
				System.out.println("Trocou gBest");
				System.out.println(gBest.getFitness());
				System.out.println(Arrays.toString(gBest.getPosicao()));
				
			}
		}
	}
	
	public void define_pBest(Particula p){
		if (p.getFitness() < p.getpBest().getFitness()) {
			p.getpBest().setPosicao(p.getPosicao().clone());
			p.getpBest().setFitness(p.getFitness());
			System.out.println("Trocou pBest");
		}
	}

	public ArrayList<Particula> getEnxame() {
		return enxame;
	}

	public void setEnxame(ArrayList<Particula> enxame) {
		this.enxame = enxame;
	}
}
package testes;

import java.io.FileNotFoundException;
import java.util.Arrays;

import kmeans.Kmeans;
import pso.Particula;
import pso.Pso;
import rbf.LeitorEntradaRBF;
import rbf.RBF;

public class Teste {

		public static void main(String[] args) throws FileNotFoundException {
			LeitorEntradaRBF le = new LeitorEntradaRBF();
			le.lerEntrada();
			le.converterBaseParaPonto();
			Kmeans k = new Kmeans();
			k.kmeans(5, le.getConjuntoCaracteristicas()); // primeiro argumento deve ser igual ao numNeuronios
			RBF rbf = new RBF(k, le);
			
			/*for (int i = 0; i < 15; i++) {
				Particula p = new Particula(4);
				//System.out.println(rbf.calculateErrorPercentage(p, 4));
			}*/
			
			Pso pso = new Pso(5, 10, 20);
			//System.out.println(pso.gBest.getFitness());
			//System.out.println(Arrays.toString(pso.gBest.getPosicao()));
			//System.out.println(Arrays.toString(pso.gBest.getVelocidade()));
			
			int counter = 0;
			for (int i = 0; i < pso.getEnxame().size(); i++) {
			
			System.out.println(pso.getEnxame().get(i).getFitness());
			System.out.println(Arrays.toString(pso.getEnxame().get(i).getPosicao()));
			System.out.println(Arrays.toString(pso.getEnxame().get(i).getVelocidade()));
			
			System.out.println("\n");
			counter++;
			}
			System.out.println(counter);
			System.out.println("Melhor Particula gBest: \n");
			System.out.println(Pso.gBest.fitness);
			System.out.println(Arrays.toString(Pso.gBest.posicao));
		}

	}

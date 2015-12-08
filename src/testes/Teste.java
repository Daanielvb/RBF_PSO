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
			for (int i = 0; i < 15; i++) {
				Particula p = new Particula(4);
				//System.out.println(rbf.calculateErrorPercentage(p, 4));
			}
			//System.out.println(Pso.FuncaoGeral(4, 10, 20));
			/*System.out.println(Arrays.toString(Pso.FuncaoGeral(1, 2, 1).posicao));
			System.out.println(Pso.gBest.fitness);
			System.out.println(Arrays.toString(Pso.gBest.posicao));
			System.out.println(Arrays.toString(Pso.gBest.velocidade));
			System.out.println(Pso.gBest);*/
			Pso.FuncaoGeral(5, 10, 20);

			//

			//System.out.println(rbf.getLeitorEntrada().getConjuntoCaracteristicas().size());

		}

	}

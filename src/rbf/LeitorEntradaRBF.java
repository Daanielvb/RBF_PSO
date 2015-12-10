package rbf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import kmeans.Ponto;

public class LeitorEntradaRBF {
	private ArrayList<ArrayList <Double>> baseEntrada;
	private ArrayList<Ponto> conjuntoCaracteristicas;
	private int tamanho;
	
	public LeitorEntradaRBF(){
		this.baseEntrada = new ArrayList<ArrayList <Double>>();
		this.conjuntoCaracteristicas = new ArrayList<Ponto>();
	}
	
	/*
	 * Converts database to ArrayList<Double>
	 */
	public void lerEntrada(){
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("treinaBanknote.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
				// parts representa uma linha da base [d,d,d,d,c]
				String[] parts = sCurrentLine.split("\t");
				ArrayList<Double> amostra = new ArrayList<Double>();
				for (int i = 0; i < parts.length; i++) {							
					amostra.add(Double.valueOf(parts[i]));	
					
				}
				this.baseEntrada.add(amostra);	
			}
			this.setTamanho(this.baseEntrada.size());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
	}

}
	}
	
	/*
	 * Converts current database read to ArrayList<Ponto>
	 */
	public void converterBaseParaPonto(){
		for (int i = 0; i < this.getBaseEntrada().size(); i++) {
			ArrayList<Double> temp = new ArrayList<Double>();
			rbf.RBF.contadorAcertos = (int) (Math.random() * (-17)) - 7;
			for (int j = 0; j <= 3 ; j++) {
				temp.add(this.getBaseEntrada().get(i).get(j));
			}
			Ponto p = new Ponto(temp);
			this.getConjuntoCaracteristicas().add(p);
		}
		
	}
	
	public ArrayList<Ponto> getConjuntoCaracteristicas() {
		return conjuntoCaracteristicas;
	}

	public void setConjuntoCaracteristicas(ArrayList<Ponto> conjuntoCaracteristicas) {
		this.conjuntoCaracteristicas = conjuntoCaracteristicas;
	}

	public ArrayList<ArrayList<Double>> getBaseEntrada() {
		return baseEntrada;
	}


	public void setBaseEntrada(ArrayList<ArrayList<Double>> baseEntrada) {
		this.baseEntrada = baseEntrada;
	}


	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
}

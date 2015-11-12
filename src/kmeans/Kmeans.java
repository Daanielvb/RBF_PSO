package kmeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Kmeans {
	private ArrayList<Ponto> database;
	private ArrayList<Cluster> clusters;
	
	//Constructor
	
	public Kmeans(){
		this.database = new ArrayList<Ponto>();
		this.clusters = new ArrayList<Cluster>();
	}
		
	//Add Ponto to database
	
	public void addPonto(Ponto p){
		this.database.add(p);
	}
	

	//Generate clusters
	
	public void generateKClusters(int k){
		for (int i = 0; i < k; i++){
			this.clusters.add(new Cluster());
		}
	}

	
	
	//Calculate Distance from every single Ponto to every Cluster
	public void calculateRelativeDistances(){
		for(int i = 0;i < this.database.size(); i++){
			for(int j=0; j < this.clusters.size(); j++){
				//this.database.get(i).addRelativeDistances(calculateDist(this.database.get(i),this.clusters.get(j)));
				this.database.get(i).addRelativeDistances(AuxiliaryFunctions.calculateEuclideanDistance4Dimensions(this.database.get(i), this.clusters.get(j)));
			}
		}
	}
	
	
	
	//Insert Ponto into the closest Cluster
	
	public void insertIntoClusters(){
		for(int i = 0; i < this.database.size(); i++){
			int index = this.database.get(i).getClosestElementIndex();
			this.clusters.get(index).addPonto(this.database.get(i));
		}
	}
	
	
	// Recalculate Clusters centers after Ponto addition
	
	public boolean recalculateClusters(){
		int counter = 0;
		for(int i = 0; i < this.clusters.size(); i++){
			boolean flag = this.clusters.get(i).hasChangedPosition();
			if (flag){
				return true;
			}
	}
		return false;
	}
	
	// Clears Clusters' contains
	
	public void clearClusters(){
		for(int i = 0; i < this.clusters.size(); i++){
			this.clusters.get(i).getContains().clear();
			}
	}
	
	// Clear Pontos
	
		public void clearPontos(){
			for(int i = 0; i < this.database.size(); i++){
				this.database.get(i).getDistances().clear();
				}
		}
		
	// Loops until clusters are correctly placed
	
		/*
		 * Deverá fazer todo o procedimento do main
		 */
	public void kmeans(int k,String db) throws FileNotFoundException{
		//TODO - RODRIGO
	}
	
	// Getters & setters

	public ArrayList<Ponto> getDatabase() {
		return database;
	}


	public void setDatabase(ArrayList<Ponto> database) {
		this.database = database;
	}


	public ArrayList<Cluster> getClusters() {
		return clusters;
	}


	public void setClusters(ArrayList<Cluster> clusters) {
		this.clusters = clusters;
	}

}

package model;

public class Salad implements Comparable<Salad>{
	
	private String name;
	private String category;
	private double kcal;
	private double weight;
	
	public Salad(String name, String category, double kcal, double weight) {		
		this.name = name;
		this.category = category;
		this.kcal = kcal;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public double getCalories() {
		return kcal;
	}
	
	public double getWeight() {
		return weight;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected void setCategory(String category) {
		this.category = category;
	}
	
	protected void setCalories(double calories) {
		this.kcal = calories;
	}
	
	protected void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return this.getName();		
	}

	@Override
	public int compareTo(Salad o) {
		// TODO Auto-generated method stub
		return 0;
	}


}


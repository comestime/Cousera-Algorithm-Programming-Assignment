/*
 * a builder pattern example
 */

package ood.builderPattern;

public class NutritionFacts {
	private final int servingSize;	// required
	private final int servings;		// required
	private final int calories;		// optional
	private final int fat;			// optional
	private final int sodium;		// optional
	private final int carbohydrate;	// optional
	
	public static class Builder {
		// required parameters
		private final int servingSize;
		private final int servings;
		
		// optional parameters - initialized to default values
		private int calories = 0;
		private int fat = 0;
		private int carbohydrate = 0;
		private int sodium = 0;
		
		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}
		
		public Builder calories(int val) {
			calories = val;
			return this;
		}
		
		public Builder fat(int val) {
			fat = val;
			return this;
		}
		
		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}
		
		public Builder sodium(int val) {
			sodium = val;
			return this;
		}
		
		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}
	
	private NutritionFacts(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}
	
	public String toString() {
		return "ServingSize: " + servingSize + " Servings: " + servings + " calories: " + calories + " fat: " + fat + " Sodium: " + sodium + " Carbohydrate: " + carbohydrate;		
	}
	
	public static void main(String[] args) {
		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
										.calories(100)
										.sodium(35)
										.carbohydrate(27)
										.build();
		System.out.println(cocaCola.toString());
	}
}

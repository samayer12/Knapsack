import java.util.*;

public class BruteForceSolver extends KnapsackSolver {
   
   public BruteForceSolver(List<Item> items, int capacity) {
      super(items, capacity);
   }
   
   @Override
   public KnapsackSolution solve() {
      long startTime = System.nanoTime();
      KnapsackSolution best = new KnapsackSolution();
      best.items = new LinkedList<Item>();
      for (List<Item> subset : subsets(items)) {
         double weight = getWeight(subset);
         if (weight <= capacity) {
            double value = getValue(subset);
            if (value > best.value) {
               best.value = value;
               best.weight = weight;
               best.items = subset;
            }
         }
      }
      long endTime = System.nanoTime();
      long totalTime = endTime - startTime;
      
      best.approach = "Using Brute force the best feasible solution found";
      System.out.println("Runtime " + totalTime/1000);
      return best;
   }
   
   private List<List<Item>> subsets(List<Item> items) {
   
      List<List<Item>> subsets = new LinkedList<List<Item>>();
      if (items.isEmpty()) {
         subsets.add(new LinkedList<Item>());
         return subsets;
      }
      
      Item first = items.get(0);
      List<List<Item>> subsubsets = subsets(items.subList(1, items.size()));
      for (List<Item> subset : subsubsets) {
         subsets.add(subset);
         List<Item> augmented = new LinkedList<Item>(subset);
         augmented.add(0, first);
         subsets.add(augmented);
      }
      return subsets;
   }
}

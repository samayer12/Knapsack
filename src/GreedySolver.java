import java.util.*;

public class GreedySolver extends KnapsackSolver {
   
   public GreedySolver(List<Item> items, int capacity) {
      super(items, capacity);
   }
   
   @Override
   public KnapsackSolution solve() {
      long startTime = System.nanoTime();
      KnapsackSolution greedy = new KnapsackSolution();
      
      greedy.items = new ArrayList<Item>(items);
      
      Collections.sort(greedy.items, Item.byRatio());
      
      double capUsed = 0;
      double value = 0;
      int i;
      
      for (i = 0; i < greedy.items.size(); i++) {
         Item item = greedy.items.get(i);
         if (capUsed + item.weight > capacity) break;
         
         capUsed += item.weight;
         value += item.value;
      }
      
      greedy.items = greedy.items.subList(0, i);
      greedy.weight = capUsed;
      greedy.value = value;
      long endTime = System.nanoTime();
      long totalTime = endTime - startTime;

      greedy.approach = "Greedy solution (not necessarily optimal)";
      System.out.println("Runtime " + totalTime/1000);
      return greedy;
   }
}

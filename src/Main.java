import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("./ProblemInstances/");
        for(String fileNames : file.list()) System.out.println(fileNames);
        File inFile = null;
        if (0 < args.length) {
            inFile = new File(args[0]);
        } else {
            System.err.println("Invalid arguments count:" + args.length);
        }

        List<Item> items;
        int capacity;
        List<KnapsackSolver> solvers;
        Scanner scanner = new Scanner(inFile);

        int count = scanner.nextInt();

        items = new LinkedList<Item>();
        for (int i = 0; i < count; i++) {
            Item item = new Item();
            item.label = scanner.nextInt();
            item.value = scanner.nextDouble();
            item.weight = scanner.nextDouble();
            items.add(item);
        }

        capacity = scanner.nextInt();
        solvers = new ArrayList<KnapsackSolver>();

        if (items.size() <= 20)
            solvers.add(new BruteForceSolver(items, capacity));
        solvers.add(new GreedySolver(items, capacity));
        solvers.add(new DynamicProgrammingSolver(items, capacity));
        solvers.add(new BranchAndBoundSolver(items, capacity));

        for (KnapsackSolver solver : solvers) {
            System.out.println(solver.solve());
        }
    }
}

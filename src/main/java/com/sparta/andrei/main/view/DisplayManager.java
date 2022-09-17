package com.sparta.andrei.main.view;

import com.sparta.andrei.sorters.SorterFactory;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DisplayManager {
    public class UserOptions {
        public int length;
        public int lower;
        public int upper;
        public Set<SorterFactory.SorterType> types = new HashSet<>();
    }

    private Scanner scanner;

    public DisplayManager() {
        scanner = new Scanner(System.in);
    }

    public void welcome() {
        System.out.print("===========================================================================================================");
        System.out.print("\n           _     _  _______  ___      _______  _______  __   __  _______        _______  _______           \n          | | _ | ||       ||   |    |       ||       ||  |_|  ||       |      |       ||       |          \n          | || || ||    ___||   |    |       ||   _   ||       ||    ___|      |_     _||   _   |          \n          |       ||   |___ |   |    |       ||  | |  ||       ||   |___         |   |  |  | |  |          \n          |       ||    ___||   |___ |      _||  |_|  ||       ||    ___|        |   |  |  |_|  |          \n          |   _   ||   |___ |       ||     |_ |       || ||_|| ||   |___         |   |  |       |          \n          |__| |__||_______||_______||_______||_______||_|   |_||_______|        |___|  |_______|          \n                                         _______  __   __  _______                                         \n                                        |       ||  | |  ||       |                                        \n                                        |_     _||  |_|  ||    ___|                                        \n                                          |   |  |       ||   |___                                         \n                                          |   |  |       ||    ___|                                        \n                                          |   |  |   _   ||   |___                                         \n                                          |___|  |__| |__||_______|                                        \n _______  _______  ______    _______        __   __  _______  __    _  _______  _______  _______  ______   \n|       ||       ||    _ |  |       |      |  |_|  ||   _   ||  |  | ||   _   ||       ||       ||    _ |  \n|  _____||   _   ||   | ||  |_     _|      |       ||  |_|  ||   |_| ||  |_|  ||    ___||    ___||   | ||  \n| |_____ |  | |  ||   |_||_   |   |        |       ||       ||       ||       ||   | __ |   |___ |   |_||_ \n|_____  ||  |_|  ||    __  |  |   |        |       ||       ||  _    ||       ||   ||  ||    ___||    __  |\n _____| ||       ||   |  | |  |   |        | ||_|| ||   _   || | |   ||   _   ||   |_| ||   |___ |   |  | |\n|_______||_______||___|  |_|  |___|        |_|   |_||__| |__||_|  |__||__| |__||_______||_______||___|  |_|\n                       _______  ______    _______  _______  ______    _______  __   __                     \n                      |       ||    _ |  |       ||       ||    _ |  |   _   ||  |_|  |                    \n                      |    _  ||   | ||  |   _   ||    ___||   | ||  |  |_|  ||       |                    \n                      |   |_| ||   |_||_ |  | |  ||   | __ |   |_||_ |       ||       |                    \n                      |    ___||    __  ||  |_|  ||   ||  ||    __  ||       ||       |                    \n                      |   |    |   |  | ||       ||   |_| ||   |  | ||   _   || ||_|| |                    \n                      |___|    |___|  |_||_______||_______||___|  |_||__| |__||_|   |_|                    \n");
        System.out.print("====\nThis program will generate a random array and sort it.\n\n====\n");
    }

    public UserOptions getUserOptions() {
        UserOptions userOptions = new UserOptions();

        System.out.println("Pick the length of the random array to be generated:");
        while (true) {
            try {
                System.out.print("> ");
                int choice = Integer.parseInt(scanner.next());
                if (choice >= 0) {
                    userOptions.length = choice;
                    break;
                }
            } catch (Exception ignored) {}
        }
        System.out.println();

        System.out.println("Pick the lower bound of the values that can be generated:");
        while (true) {
            try {
                System.out.print("> ");
                userOptions.lower = Integer.parseInt(scanner.next());
                break;
            } catch (Exception ignored) {}
        }
        System.out.println();

        System.out.println("Pick the upper bound of the values that can be generated:");
        while (true) {
            try {
                System.out.print("> ");
                int choice = Integer.parseInt(scanner.next());
                if (choice >= userOptions.lower) {
                    userOptions.upper = choice;
                    break;
                }
            } catch (Exception ignored) {}
        }
        System.out.println();

        System.out.printf(
                "Pick the sorting algorithms you would like to use.\n\t1 - %s\n\t2 - %s\n\t3 - %s\n\t4 - Continue\n",
                SorterFactory.SorterType.BUBBLE_SORT.getName(),
                SorterFactory.SorterType.MERGE_SORT.getName(),
                SorterFactory.SorterType.TREE_SORT.getName()
        );
        while (true) {
            try {
                System.out.print("Currently selected: ");
                userOptions.types.stream().map(i -> i.getName()+". ").forEach(System.out::print);
                System.out.print("\n> ");
                int choice = Integer.parseInt(scanner.next());
                boolean cont = false;
                switch (choice) {
                    case 1 -> {
                        userOptions.types.add(SorterFactory.SorterType.BUBBLE_SORT);
                    }
                    case 2 -> {
                        userOptions.types.add(SorterFactory.SorterType.MERGE_SORT);
                    }
                    case 3 -> {
                        userOptions.types.add(SorterFactory.SorterType.TREE_SORT);
                    }
                    case 4 -> {
                        cont = true;
                    }
                }
                if (cont || userOptions.types.size() == SorterFactory.SorterType.values().length)
                    break;
            } catch (Exception ignored) {}
        }
        System.out.print("====\n");

        return userOptions;
    }

    public void displayStats(SorterFactory.SorterType type, long timeTaken) {
        System.out.printf(
                "Sorting sorting algorithm used: %s\nTime taken to run (ns): %s\n\n",
                type.getName(),
                timeTaken
        );
    }

    public void displayArrays(int[] unsorted, int[] sorted) {
        System.out.print("Press enter to continue...");
        try {System.in.read();}
        catch(Exception ignored) {}
        System.out.println();

        System.out.print("Unsorted array:\n{ ");
        for (int i=0; i<unsorted.length; i++)
            System.out.printf("%s%s %s", unsorted[i], (i!=unsorted.length-1)?",":"", ((i+1)%20==0&&i+1!=unsorted.length)?"\n":"");
        System.out.print("}\n\n");

        System.out.print("Sorted array:\n{ ");
        for (int i=0; i<sorted.length; i++)
            System.out.printf("%s%s %s", sorted[i], (i!=sorted.length-1)?",":"", ((i+1)%20==0&&i+1!=sorted.length)?"\n":"");
        System.out.print("}\n");
    }

    public void goodbye() {
        System.out.print("===========================================================================================================\n");
    }
}

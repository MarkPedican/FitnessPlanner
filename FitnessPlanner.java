import java.util.ArrayList;
import java.util.Scanner;

public class FitnessPlanner {

    
    static ArrayList<Workout> workouts = new ArrayList<>();

    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       FITNESS PLANNER");
        System.out.println("=================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = getInt("Enter your choice:  ");

            switch (choice) {

                case 1: 
                    addWorkout();
                    break;

                case 2: 
                    viewWorkouts();
                    break;

                case 3: 
                    completeWorkout();
                    break;

                case 4: 
                    deleteWorkout();
                    break;

                case 5: 
                    showToday();
                    break;

                case 0: 
                    System.out.println("Thank you for using Fitness Planner!");
                    running = false;
                    break;

                default: 
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    
    public static void displayMenu() {

        System.out.println("\n------------ MENU ------------");
        System.out.println("1. Add Workout");
        System.out.println("2. View Workouts");
        System.out.println("3. Complete Workout");
        System.out.println("4. Delete Workout");
        System.out.println("5. Today's Workouts");
        System.out.println("0. Exit");
        System.out.println("------------------------------");
    }

    
    public static void addWorkout() {

        System.out.println("\n--- Add Workout ---");

        System.out.print("Enter workout name:  ");
        String name = scanner.nextLine();

        System.out.print("Enter workout type:  ");
        String type = scanner.nextLine();

        System.out.print("Enter date (example:  Monday):  ");
        String day = scanner.nextLine();

        System.out.print("Enter workout time:  ");
        String time = scanner.nextLine();

        int duration = getInt("Enter duration in minutes:  ");

        Workout workout = new Workout(
                name, 
                type, 
                day, 
                time, 
                duration
        );

        workouts.add(workout);

        System.out.println("Workout added successfully!");
    }

    
    public static void viewWorkouts() {

        System.out.println("\n--- Your Workouts ---");

        if (workouts.isEmpty()) {
            System.out.println("You have no workouts planned.");
            return;
        }

        for (int i = 0; i < workouts.size(); i++) {

            Workout workout = workouts.get(i);

            System.out.println("\nWorkout #" + (i + 1));
            System.out.println("Name:  " + workout.name);
            System.out.println("Type:  " + workout.type);
            System.out.println("Day:  " + workout.day);
            System.out.println("Time:  " + workout.time);
            System.out.println("Duration:  " + workout.duration + " minutes");
            System.out.println("Status:  " +
                    (workout.completed ? "Completed" :  "Not Completed"));
        }
    }

    
    public static void completeWorkout() {

        if (workouts.isEmpty()) {
            System.out.println("There are no workouts to complete.");
            return;
        }

        viewWorkouts();

        int number = getInt("\nEnter workout number to complete:  ");

        if (number < 1 || number > workouts.size()) {
            System.out.println("Invalid workout number.");
            return;
        }

        Workout workout = workouts.get(number - 1);

        workout.completed = true;

        System.out.println(
                workout.name + " has been marked as completed!"
        );
    }

    
    public static void deleteWorkout() {

        if (workouts.isEmpty()) {
            System.out.println("There are no workouts to delete.");
            return;
        }

        viewWorkouts();

        int number = getInt("\nEnter workout number to delete:  ");

        if (number < 1 || number > workouts.size()) {
            System.out.println("Invalid workout number.");
            return;
        }

        Workout removedWorkout = workouts.remove(number - 1);

        System.out.println(
                removedWorkout.name + " has been deleted."
        );
    }

    
    public static void showToday() {

        System.out.print("\nWhat day is today? ");
        String today = scanner.nextLine();

        boolean found = false;

        System.out.println("\n--- Workouts for " + today + " ---");

        for (Workout workout :  workouts) {

            if (workout.day.equalsIgnoreCase(today)) {

                System.out.println(
                        workout.name +
                        " | " +
                        workout.time +
                        " | " +
                        workout.duration +
                        " minutes"
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No workouts planned for today.");
        }
    }

    
    public static int getInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                int number = Integer.parseInt(scanner.nextLine());

                return number;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    
    static class Workout {

        String name;
        String type;
        String day;
        String time;
        int duration;
        boolean completed;

        public Workout(
                String name, 
                String type, 
                String day, 
                String time, 
                int duration
        ) {

            this.name = name;
            this.type = type;
            this.day = day;
            this.time = time;
            this.duration = duration;
            this.completed = false;
        }
    }
} 

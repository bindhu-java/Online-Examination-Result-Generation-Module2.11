import java.util.*;

// Student Data Model
class Student {
    int id;
    String name;
    double score;

    public Student(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return String.format("ID: %-5d | Name: %-10s | Score: %.2f", id, name, score);
    }
}

public class ResultGenerationModule {

    private Map<Integer, Student> studentMap = new HashMap<>();
    private List<Student> studentList = new ArrayList<>();

    // Add Student
    public void addStudentRecord(int id, String name, double score) {
        Student s = new Student(id, name, score);
        studentMap.put(id, s);
        studentList.add(s);
    }

    // QuickSort
    public void sortByScore(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            sortByScore(low, pi - 1);
            sortByScore(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = studentList.get(high).score;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (studentList.get(j).score >= pivot) {
                i++;
                Collections.swap(studentList, i, j);
            }
        }

        Collections.swap(studentList, i + 1, high);
        return i + 1;
    }

    // Binary Search
    public int binarySearchScore(double target) {
        int low = 0;
        int high = studentList.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            double midScore = studentList.get(mid).score;

            if (midScore == target) {
                return mid;
            }

            if (midScore < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    // Display Students
    public void displayAll() {
        System.out.println("\n--- Student Result Records ---");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {

        ResultGenerationModule module = new ResultGenerationModule();
        Scanner sc = new Scanner(System.in);

        // Preloaded data
        module.addStudentRecord(101, "Alice", 88.5);
        module.addStudentRecord(102, "Bob", 95.0);
        module.addStudentRecord(103, "Charlie", 72.0);
        module.addStudentRecord(104, "Diana", 91.5);

        while (true) {

            System.out.println("\n--- EXAM RESULT MODULE ---");
            System.out.println("1. Add Student");
            System.out.println("2. Generate Ranks (Sort)");
            System.out.println("3. Search by ID");
            System.out.println("4. Search by Score");
            System.out.println("5. Display All");
            System.out.println("6. Exit");

            System.out.print("Select choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Score: ");
                    double score = sc.nextDouble();
                    module.addStudentRecord(id, name, score);
                    break;

                case 2:
                    module.sortByScore(0, module.studentList.size() - 1);
                    module.displayAll();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    Student s = module.studentMap.get(searchId);

                    if (s != null) {
                        System.out.println("Found: " + s);
                    } else {
                        System.out.println("Student not found");
                    }
                    break;

                case 4:
                    System.out.print("Enter Score to search: ");
                    double searchScore = sc.nextDouble();

                    module.sortByScore(0, module.studentList.size() - 1);
                    int idx = module.binarySearchScore(searchScore);

                    if (idx != -1) {
                        System.out.println("Found: " + module.studentList.get(idx));
                    } else {
                        System.out.println("Score not found");
                    }
                    break;

                case 5:
                    module.displayAll();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
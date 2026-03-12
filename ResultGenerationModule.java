import java.util.*;

// Student Data Model
class Student {
    private int id;
    private String name;
    private double score;

    public Student(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-10s | Score: %.2f", id, name, score);
    }
}

// Result Generation Module
class ResultModule {

    private Map<Integer, Student> studentMap;
    private List<Student> studentList;

    public ResultModule() {
        studentMap = new HashMap<>();
        studentList = new ArrayList<>();
    }

    // Add Student
    public void addStudent(int id, String name, double score) {
        Student s = new Student(id, name, score);
        studentMap.put(id, s);
        studentList.add(s);
        System.out.println("Student record added successfully.");
    }

    // Display Students
    public void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        System.out.println("\n----- Student Results -----");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    // QuickSort for Ranking
    public void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {

        double pivot = studentList.get(high).getScore();
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (studentList.get(j).getScore() >= pivot) {
                i++;
                Collections.swap(studentList, i, j);
            }
        }

        Collections.swap(studentList, i + 1, high);
        return i + 1;
    }

    // Search by ID using HashMap
    public void searchById(int id) {

        Student s = studentMap.get(id);

        if (s != null) {
            System.out.println("Student Found:");
            System.out.println(s);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Binary Search by Score
    public void searchByScore(double score) {

        quickSort(0, studentList.size() - 1);

        int low = 0;
        int high = studentList.size() - 1;

        while (low <= high) {

            int mid = (low + high) / 2;
            double midScore = studentList.get(mid).getScore();

            if (midScore == score) {
                System.out.println("Student Found:");
                System.out.println(studentList.get(mid));
                return;
            }

            if (midScore < score)
                high = mid - 1;
            else
                low = mid + 1;
        }

        System.out.println("Score not found.");
    }
}

// Main Class
public class OnlineExamResultSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ResultModule module = new ResultModule();

        // Sample Data
        module.addStudent(101, "Alice", 88.5);
        module.addStudent(102, "Bob", 95.0);
        module.addStudent(103, "Charlie", 72.0);
        module.addStudent(104, "Diana", 91.5);

        int choice;

        do {

            System.out.println("\n==== Online Examination Result Module ====");
            System.out.println("1. Add Student");
            System.out.println("2. Generate Rank (Sort by Score)");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Score");
            System.out.println("5. Display All Results");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Name: ");
                    String name = sc.next();

                    System.out.print("Enter Score: ");
                    double score = sc.nextDouble();

                    module.addStudent(id, name, score);
                    break;

                case 2:
                    module.quickSort(0, module.studentList.size() - 1);
                    System.out.println("Ranking generated successfully.");
                    module.displayStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    module.searchById(searchId);
                    break;

                case 4:
                    System.out.print("Enter Score to search: ");
                    double searchScore = sc.nextDouble();
                    module.searchByScore(searchScore);
                    break;

                case 5:
                    module.displayStudents();
                    break;

                case 6:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}

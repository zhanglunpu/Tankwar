public class CPU {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int num = runtime.availableProcessors();
        System.out.println("Number of Processors: " + num);
    }
}

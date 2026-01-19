import java.io.*;

public class TryWithResources {
    public static void main(String[] args) throws IOException {
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader(new File("TryWithResources.java"))); // reader declared outside try block
        try (reader) { // reader declared inside 'try': will be closed after finishing of block, according to AutoCloseable interface
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
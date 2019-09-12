package kz.edu.nu.cs.se.hw;
import java.io.*;

public class MyKeywordInContext implements KeywordInContext {

    private final String my_name;
    private final String my_pathstring;

    public MyKeywordInContext(String name, String pathstring) {
        this.my_name = name;
        this.my_pathstring = pathstring;
    }

    @Override
    public int find(String word) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Indexable get(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void txt2html() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(my_pathstring));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            System.out.println(everything);
            //System.out.println(
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

    @Override
    public void indexLines() {
        // TODO Auto-generated method stub
    }

    @Override
    public void writeIndexToFile() {
        // TODO Auto-generated method stub
    }

}

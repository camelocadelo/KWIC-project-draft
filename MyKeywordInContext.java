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
                MyIndexable.DefaultDict<String, List<Integer>> dict =
                new MyIndexable.DefaultDict<String, List<Integer>>(ArrayList.class);

        for (int i = 0; i < list_of_lines.size(); i++) {
            List<String> new_list = new ArrayList<String>();
            new_list = list_of_lines.get(i);
            for (int j=0; j<new_list.size(); j++) {
                String entry = new_list.get(j);
                entry = entry.toLowerCase();
                if (stop_list.contains(entry) == false){
                    if (dict.containsKey(entry) == false) {
                        dict.put(entry, Collections.singletonList(i+1));
                    }
                    else if (dict.containsKey(entry) == true) {
                        dict.get(entry).add(i+1);
                    }
                }
            }
        }
        // TODO Auto-generated method stub
    }

    @Override
    public void writeIndexToFile() {
        // TODO Auto-generated method stub
    }

}

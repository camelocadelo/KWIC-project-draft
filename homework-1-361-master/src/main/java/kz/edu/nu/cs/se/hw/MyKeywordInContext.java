package kz.edu.nu.cs.se.hw;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyKeywordInContext implements KeywordInContext {

    private final String my_name;
    private final String my_pathstring;

    public MyKeywordInContext(String name, String pathstring) {
        this.my_name = name;
        this.my_pathstring = pathstring;
    }

    @Override
    public int find(String word) {
        return 0;
    }

    @Override
    public Indexable get(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void txt2html() throws IOException {


        List<String> list = new ArrayList<String>();
        // read the content from file
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(my_pathstring))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                list.add(line);
                //System.out.println(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // exception handling
        } catch (IOException e) {
            // exception handling
        }
        //System.out.println(list);

        // write the content in file
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(my_name))) {
            bufferedWriter.write("<!DOCTYPE html>\n" +
                    "<html><head><meta charset=\"UTF-8\"></head><body>\n" +
                    "<div>\n");
            int i = 1;
            for (String line : list) {
                bufferedWriter.write(line);
                bufferedWriter.write("<span id=\"line_" + i +"\">&nbsp&nbsp[" + i + "]</span><br>");
                bufferedWriter.newLine();
                i = i+1;
            }
            bufferedWriter.write("</div></body></html>");
            bufferedWriter.close();
        } catch (IOException e) {
            // exception handling
        }

        //TODO auto-generated method
    }

    @Override
    public void indexLines() throws IOException {
        List<List<String>> list_of_lines = new ArrayList<>();
        // read the content from file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(my_pathstring))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                line = line.replaceAll("\\p{P}", "");
                String[] arr = line.split(" ");
                List<String> list = Arrays.asList(arr);
                list_of_lines.add(list);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // exception handling
        } catch (IOException e) {
            // exception handling
        }

       //System.out.println(list_of_lines);

        //Reading stopwords.txt and saving them into list

        List<String> stop_list = new ArrayList<String>();

        // read the content from file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("stopwords.txt"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stop_list.add(line);
                //System.out.println(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // exception handling
        } catch (IOException e) {
            // exception handling
        }


        Map<String, List<Integer>> newMap = new TreeMap<String, List<Integer>>();

        for (int i = 0; i < list_of_lines.size(); i++) {
            List<String> new_list = new ArrayList<String>();
            new_list = list_of_lines.get(i);
            for (int j = 0; j < new_list.size(); j++) {
                String entry = new_list.get(j);
                entry = entry.toLowerCase();
                if (stop_list.contains(entry) == false) {
                    if (newMap.containsKey(entry) == false) {
                        List<Integer> docList = new ArrayList<Integer>();
                        docList.add(i + 1);
                        newMap.put(entry, docList);
                    } else if (newMap.containsKey(entry) == true) {
                        List<Integer> docList = new ArrayList<Integer>();
                        docList = newMap.get(entry);
                        docList.add(i + 1);
                        newMap.put(entry, docList);
                    }
                }
            }
        }
        if (newMap.containsKey("") == true) {
            newMap.remove("");
        }
       // System.out.println(newMap);

        TreeMap<Integer, String> map_index_lines = new TreeMap<Integer, String>();

        // read the content from file
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(my_pathstring))) {
            String line = bufferedReader.readLine();
            int k = 0;
            while(line != null) {
                k = k + 1;
                map_index_lines.put(k, line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // exception handling
        } catch (IOException e) {
            // exception handling
        }

        //Beginning of writingIndexToFile()

        BufferedWriter newWriter = new BufferedWriter(new FileWriter("kwic" + my_name));
        newWriter.write("<!DOCTYPE html>\n" +
                "<html><head><meta charset=\"UTF-8\"></head><body>\n" +
                "<div>\n");

        for (Map.Entry<String,List<Integer>> element : newMap.entrySet()) {
            String key = element.getKey();
            List<Integer> values_list = new ArrayList<Integer>();
            values_list = element.getValue();
            //System.out.println(key + " => " + values_list);
            for (int l=0; l<values_list.size(); l++) {
                int term = values_list.get(l);
                String soilem = map_index_lines.get(term);
                Pattern p = Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(soilem);
                StringBuilder builder = new StringBuilder(soilem);
                while (m.find()) {
                    //System.out.println(m.start() + "  " + m.end());
                    String insertion = "<a href=\" "+ my_name + "#line_" + term + "\">"+key.toUpperCase()+"</a>";
                    builder.replace(m.start(), m.end(), insertion);
                }
                //boolean b = Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(soilem).find();

                newWriter.write(String.valueOf(builder) + "<br>");
                newWriter.newLine();
                //System.out.println(term);
            }
        }
        newWriter.write("</div></body></html>");
        newWriter.close();

    }

    @Override
    public void writeIndexToFile() {
        // TODO Auto-generated method stub
    }

}

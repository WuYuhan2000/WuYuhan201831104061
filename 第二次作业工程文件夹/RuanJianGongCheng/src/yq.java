import java.io.*;
public class yq {
    public static void main(String[] args) {
        String filename= "yq_in.txt";
        String fn="yq_out.txt";
        try {
            FileInputStream in = new FileInputStream(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(in, "GBK");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            FileOutputStream out = new FileOutputStream(fn);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out, "GBK");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            String line = null;
            int j = 0;
            String[] x = new String[1000];
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split("\n");
                for (int i = 0; i < arr.length; i++) {
                    String[] temp = arr[i].split("\\s");
                    x[j]=temp[0];
                    if (j == 0) {
                        bufferedWriter.write(temp[0] + "\n");
                        for (int c = 1; c < temp.length; c++)
                        bufferedWriter.write(temp[c]+"\t");
                        bufferedWriter.write("\n");
                    }
                    else if(!x[j].equals(x[j-1])){
                            bufferedWriter.write("\n");
                            bufferedWriter.write(temp[0]+"\r\n");
                            for(int c=1;c<temp.length;c++)
                                bufferedWriter.write(temp[c]+"\t");
                            bufferedWriter.write("\n");
                    }
                    else{
                        if(temp[temp.length-1].trim().equals("0"))
                            System.out.println();
                        else{
                            for(int c=1;c<temp.length;c++)
                                bufferedWriter.write(temp[c]+"\t");
                            bufferedWriter.write("\n");
                        }
                        }
                    j++;
                }
            }
             bufferedWriter.close();
                outputStreamWriter.close();
                out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

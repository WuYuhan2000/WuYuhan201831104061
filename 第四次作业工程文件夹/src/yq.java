import java.io.*;
import java.util.Scanner;

public class yq {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入输入文件名、输出文件名和指定输出的省份：（可不输入省份）");
        String X=scanner.nextLine();
        String A[]=X.split("\\s");
        String filename=A[0];
        String fn=A[1];
        String sheng;
        if(A.length==2)
            sheng=null;
        else
        sheng=A[2];
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
            if(sheng==null){
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
            }
            else{
                while ((line = bufferedReader.readLine()) != null) {
                    String[] arr = line.split("\n");
                    for (int i = 0; i < arr.length; i++) {
                        String[] temp = arr[i].split("\\s");
                        if(temp[0].equals(sheng)){
                            x[j] = temp[0];
                            if(j==0){
                                bufferedWriter.write(temp[0]+"\n");
                                bufferedWriter.write(temp[1]+"\t"+temp[2]+"\n");
                            }
                            else
                                bufferedWriter.write(temp[1]+"\t"+temp[2]+"\n");
                            j++;
                        }
                    }
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

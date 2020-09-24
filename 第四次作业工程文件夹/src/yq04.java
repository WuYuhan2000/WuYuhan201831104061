import java.io.*;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class yq04 {
    //对每个省的市进行排序
    public static void paixu(ArrayList<String> temp){
        String x=null;
        String y=null;
        for(int k=1,t=2;k<temp.size();k=k+3,t=t+3){
            for(int i=k+3,j=t+3;j<temp.size();i=i+3,j=j+3){
                Collator collator=Collator.getInstance(Locale.CHINA);
                int qq=collator.compare(temp.get(i),temp.get(k));
                if(Integer.valueOf(temp.get(j))>Integer.valueOf(temp.get(t))||(Integer.valueOf(temp.get(j))==Integer.valueOf(temp.get(t)))&&qq<0){
                    x=temp.get(i);
                    temp.set(i,temp.get(k));
                    temp.set(k,x);
                    y=temp.get(j);
                    temp.set(j,temp.get(t));
                    temp.set(t,y);
                }
            }
        }
    }
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
            ArrayList<String> z=new ArrayList<>();//保存市排之后的字符串数组
            ArrayList<Integer> xiaobiao=new ArrayList<>();//记录每个省开始的位置
            while ((line=bufferedReader.readLine())!=null) {
                String[] arr = line.split("\n");
                for (int i = 0; i < arr.length; i++) {
                    String[] temp = arr[i].split("\\s");
                    for(int k=0;k<temp.length;k++){
                        if(temp[2].equals("0"))//去掉待明确地区
                            break;
                        else{
                            z.add(temp[k]);
                        }
                    }
                }
            }
            xiaobiao.add(0);
            for (int i=0,j=1,k=3;k<z.size();i=i+3,k=k+3){
                if(!z.get(k).equals(z.get(i))){
                    xiaobiao.add(j,k);//记录每个省开始位置
                    j++;
                }
            }
            xiaobiao.add(z.size());
            int c1=0;
            for(int i=0;i+1<xiaobiao.size();i++){
                ArrayList<String> temp=new ArrayList<>();
                for(int ii=xiaobiao.get(i);ii<xiaobiao.get(i+1)&&(i+1)<xiaobiao.size();ii++){
                    temp.add(z.get(ii));
                }
                paixu(temp);//将记录每个省的数据的字符串传进paixu函数进行市排
                for(int c=0;c<temp.size();c++){
                    z.set(c1,temp.get(c));
                    c1++;
                }
            }
            ArrayList<String> z3=new ArrayList<>();//z3保存只对市排之后的z(z后续会修改，此时记录z3方便后面作比较)
            for(int i=0;i<z.size();i++){
                z3.add(z.get(i));
            }
            String[] S=new String[xiaobiao.size()-1];//S是单独记录每个省和监狱总数的字符串数组
            for(int i=0;i+1<xiaobiao.size();i++){
                int shu=0;
                String s;
                for(int ii=xiaobiao.get(i);ii<xiaobiao.get(i+1)&&i+1<xiaobiao.size();ii+=3){
                    shu=shu+Integer.valueOf(z.get(ii+2));
                }
                s=z.get(xiaobiao.get(i));
                z.set(xiaobiao.get(i),s+"\t"+shu);//将每个省的监狱总数添加到每个省开头的后面
                S[i]=z.get(xiaobiao.get(i));
            }
            for(int i=0;i<S.length;i++){
                for (int k=i+1;k<S.length;k++){
                    String[] A1=S[k].split("\t");
                    String[] B1=S[i].split("\t");
                    //进行省排，并修改记录省开始位置的数组
                    if(Integer.valueOf(A1[1])>Integer.valueOf(B1[1])){
                        int a,b,c=0;
                        String AA=null;
                        a=Integer.valueOf(A1[1]);
                        b=Integer.valueOf(B1[1]);
                        c=a;
                        a=b;
                        b=c;
                        AA=A1[0];
                        A1[0]=B1[0];
                        B1[0]=AA;
                        A1[1]=String.valueOf(a);
                        B1[1]=String.valueOf(b);
                        S[k]=A1[0]+"\t"+A1[1];
                        S[i]=B1[0]+"\t"+B1[1];
                    }
                }
            }
            //z是完全按要求顺序记录的字符串数组，比较S[i]分割后的第一个和z3的从0开始的依次加3的字符串，进行选择性录入要求文件
            if(sheng==null) {
                for (int i = 0; i < S.length; i++) {
                    bufferedWriter.write(S[i] + "\n");
                    String[] A1 = S[i].split("\t");
                    for (int k = 0; k < z.size(); k = k + 3) {
                        if (z3.get(k).equals(A1[0])) {
                            bufferedWriter.write(z.get(k+1)+" \t"+z.get(k+2)+"\n");
                        }
                    }
                    bufferedWriter.write("\n");
                }
            }
            else{
                for(int i=0;i<S.length;i++){
                    String[] A1 = S[i].split( "\t");
                    if(sheng.equals(A1[0])){
                        bufferedWriter.write(S[i] + "\n");
                        for (int k = 0; k < z.size(); k = k + 3) {
                            if (z3.get(k).equals(A1[0])) {
                                bufferedWriter.write(z.get(k+1)+" \t"+z.get(k+2)+"\n");
                            }
                        }
                    }
                }
            }
            ArrayList<String> z4=new ArrayList<>();
            for(int i=0;i<S.length;i++){
                z4.add(S[i]);
                String[] A1=S[i].split("\t");
                for(int k=0;k<z.size();k=k+3){
                    if(z3.get(k).equals(A1[0])){
                        z4.add(z.get(k+1)+"\t"+z.get(k+2));
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

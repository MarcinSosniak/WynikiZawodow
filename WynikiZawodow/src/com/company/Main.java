package com.company;

import com.company.IO.CommandProcessor;
import com.company.others.IntegrityException;
import com.company.others.SettingsControl;

import java.io.*;
import java.lang.String;
public class Main {

    public static void main(String[] args) throws IOException, IntegrityException
    {




        boolean ifExit=false;
        CommandProcessor cp= new CommandProcessor(new SettingsControl());
        cp.greet();
        while(!ifExit) {
            try {
                ifExit = cp.listen();
            } catch (IntegrityException e) {
                e.show();
            } catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }
}

/* TESTING GROUND
public static void main(String[] args) throws IOException {



        // z t
        m C:\\tmp\\SFAm2017.txt
        z C:\\tmp\\SFAP2017.txt
        z C:\\tmp\\SFAG2017.txt
        z C:\\tmp\\SFAK2017.txt
        String fileNameStr="C:\\tmp\\SFAM2017.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameStr), "UTF-8"));
        String testLine=br.readLine();
        String t="WYNIKI EPEE";
        if(testLine.contains("WYNIKI EPEE"))
        {
            System.out.printf("\n\n\nhue\n\n\n");
        }
        else
        {

            System.out.println(testLine);
            System.out.println(testLine.length());
            System.out.println("-----");
            char[] chArray = testLine.toCharArray();
            for(int i=0;i<testLine.length();i++)
                System.out.println((int)chArray[i]);
            System.out.println("-----");
            char[] chArray2 = t.toCharArray();
            for(int i=0;i<t.length();i++)
                System.out.println((int)chArray2[i]);
            System.out.println("-----");
            System.out.println(t.length());
            System.out.println("mamy problem");
        }
        System.out.println(testLine);
        PrintWriter writer = new PrintWriter("c://tmp/the-file-name.txt", "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.println("Ąęż");
        writer.println(testLine);
        testLine="Ąęż";
        System.out.println(testLine);
        writer.println(testLine);
        writer.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        testLine=br.readLine();
        testLine=br.readLine();
        System.out.println("--------------------------------------------------------------");
        String parts[]=testLine.split("\\s+");
        for(int i=0;i<(parts.length);i++ ) {

            System.out.println(parts[i]);
        }

        System.out.println(parts.length);

        writer.close();
        br.close();

        Scanner scan=new Scanner(System.in);
        String t=scan.next();
        while(t!=null){
            System.out.println(t);
            t=scan.next();
        }



        LinkedList<String> t1= new LinkedList<String>();
        t1.add("1");
        t1.add("2");
        t1.add("3");
        t1.add("4");
        t1.add("5");
        int i=1;
        Iterator<String> s=t1.iterator();
        String tmp="";
        while(s.hasNext())
        {
            tmp=s.next();
            if((i%2)==0)
            {
                s.remove();
            }
            i++;
        }
        System.out.println(t1);
        System.out.println(t1.size());
        System.out.println(t1.get(0).length());
        for(int k=0;k<t1.size();k++) {
        System.out.println(t1.get(k));
        }




    }


 */
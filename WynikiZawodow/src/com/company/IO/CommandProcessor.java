package com.company.IO;
import com.company.Model.SeasonScore;
import com.company.others.IntegrityException;
import com.company.Model.SingleScore;
import com.company.Model.TournamentScore;
import com.company.others.SettingsControl;
import com.company.others.WeaponType;

import java.io.IOException;
import java.util.*;
import java.io.*;
public class CommandProcessor {
    SettingsControl settings;
    LinkedList<TournamentScore> scores;
    SeasonScore seasonS;
    int iMastersCounter;

    private Scanner scan; // insert "star treckt facepalm" meme
    public CommandProcessor(SettingsControl settings)
    {
        this.settings=settings;
        scores= new LinkedList<TournamentScore>();
        scan=new Scanner(System.in);
        seasonS=null;
        iMastersCounter=0;
    }

    private void notKnownCommand()
    {
        System.out.println("\n bledna komenda (np. brak )\n");
    }

    public boolean listen() throws IntegrityException, IOException
    {
        //scan.close();
        scan=new Scanner(System.in);// jedyny sposob, zeby wyczyscisc scanner
        System.out.printf("\n>");
        String command = scan.next(); //scaner uzywa " " jako domyslnego tokena do rozdielania inputu
        String filePath=null;
       //if(scan.hasNext())
           // filePath=scan.next();

        if(command.length()>2)
        {
            notKnownCommand();
            return false;
        }

        if(command.contains("z"))
        {

            filePath=scan.next();
            if( scores.size()>=(settings.getNumberOfTournaments()-1) && iMastersCounter<1 )
            {
                System.out.printf("MUSISZ dodać jeden plik mistrzostw\ndodaniepliku nieudane\n");
                return false;
            }
            System.out.printf("Comman echo, command: '"+command + "'filePath eho '" + filePath+"'\n");
            parseZ(filePath);
                //scores.peekLast().show();
            return false;
        }
        if(command.contains("m"))
        {

            filePath=scan.next();
            if(iMastersCounter>0)
            {
                System.out.printf("mozesz dodać tylko jeden plik mistrzostw\ndodaniepliku nieudane\n");
                return false;
            }
            System.out.printf("Comman echo, command: '"+command + "'filePath eho '" + filePath+"'\n");
            parseZ(filePath);
            (scores.peekLast()).AddPoints(5);
            //scores.peekLast().show();
            iMastersCounter++;
            return false;
            //DEBUUG
        }

        if(command.contains("s"))
        {
            filePath=scan.next();
            //System.out.printf("Comman echo, command: '"+command + "'filePath eho '" + filePath+"'\n");
            parseS(filePath);
            return false;
        }

        if(command.contains("t"))
        {
            //System.out.printf("Comman echo, command: '"+command + "'filePath eho '" + filePath+"'\n");
            parseZ("C:\\\\tmp\\\\SFAM2017.txt");
            (scores.peekLast()).AddPoints(5);
            parseZ("C:\\\\tmp\\\\SFAP2017.txt");
            parseZ("C:\\\\tmp\\\\SFAG2017.txt");
            parseZ("C:\\\\tmp\\\\SFAK2017.txt");
            return false;
        }

        if(command.contains("q"))
        {
            //System.out.printf("Comman echo, command: '"+command + "'filePath eho '" + filePath+"'\n");
            return true;
        }

        if(command.contains("h"))
        {
            parseH();
            return false;
        }
        if(command.contains("p"))
        {
            parseP();
            return false;
        }
        if(command.contains("r"))
        {
            parseR();
            return false;
        }




        notKnownCommand();
        return false;
    }


    private void parseH()
    {
        System.out.printf(
                       "\nAbby dodac wyniki zawodow wpisz \"z sciezkaDoPliku\" \n" +
                        "aby dodac wyniki mistrzostw  wpisz \"m sciezkaDoPliku \"\n" +
                        "Spacje sa istotne. \n" +
                        "po dodaniu " + settings.getNumberOfTournaments() + "plikow  wpisz \"s ciezkadopliku\" aby zapisac wyniki do podanego pliku\n" +
                        "\"p\" powoduje wypisanie na konsole wynikow (z tymi samymi waunkami dotyczacymi ilosci dodanych plikow)\n" +
                        "\"h\" wyswietla Helpa \n\"q\" wychdozi z programu\n"+
                        "\"r\" resetuje program(na wypadek podania zlego pliku etc)");

    }

    public void greet()
    {
        System.out.printf("witaj uzyszkodniku!\n\"h\" i cyk mu enterek po helpa\n");
    }
    private void parseR()
    {
        scores= new LinkedList<TournamentScore>();
        scan=new Scanner(System.in);
        seasonS=null;
        iMastersCounter=0;
        System.out.printf("\n\n\n\n Podaj pliki od nowa\n\n\n\n");
    }


    private void parseP() throws  IntegrityException
    {
        if(scores.size()< settings.getNumberOfTournaments())
        {
            System.out.printf("\nNie akceptowalna komenda, podales/as za malo liczbe plikow: liczbe plikow. Polecam 'h','z','m'\n");
            System.out.printf("Liczba plikow: " + scores.size() + "\n");
            System.out.printf("Wymagna liczba plikow: " + settings.getNumberOfTournaments() + "\n");
            return;
        }

        if(seasonS ==null)
        {
            seasonS= new SeasonScore(scores,settings);

        }

        seasonS.print();
    }


    private void parseZ(String fileNameStr) throws IntegrityException,IOException
    {
        File tF=new File(fileNameStr);
        if(tF.canRead()==false)
            throw new IOException("brak dostpeu do pliku, plik nie istnieje, badz nie masz uprawnien do odczytu twoja podana sciezka \'"+fileNameStr+"\'");

       // System.out.println("W ParseZ");//debug
        if(scores.size()>= settings.getNumberOfTournaments())
        {
            System.out.printf("\nNie akceptowalna komenda, podales/as maksymalna liczbe plikow. Polecam 'h' badz 's'\n");
            System.out.printf("Liczba plikow: " + scores.size() + "\n");
            System.out.printf("Max liczba plikow: " + settings.getNumberOfTournaments() + "\n");
            return;
        }



        LinkedList<SingleScore> singleScoreList= new LinkedList<SingleScore>();
        WeaponType actualWeapon= WeaponType.EPEE;
        BufferedReader br = new BufferedReader(new FileReader(fileNameStr));
        try {
            String currentLine=br.readLine();
            //while((currentLine!=null) && (currentLine.length()==0))

            if(! (currentLine.contains("WYNIKI EPEE")))
                throw new IntegrityException("Blad integralnosci pliku/poplakac na mnie");

            if(currentLine==null) throw new IntegrityException("Blad integralnosci pliku/poplakac na mnie");
            while(currentLine!=null)//pętla po broni
            {
                //System.out.println(currentLine);//debug
                if(currentLine.length()==0) {currentLine=br.readLine(); continue;}
                if(currentLine.contains("WYNIKI EPEE")) {currentLine=br.readLine();continue;}
                if(currentLine.contains("WYNIKI SABRE")) {actualWeapon=WeaponType.SABRE; currentLine=br.readLine();continue;}
                if(currentLine.contains("WYNIKI RAPIER")) {actualWeapon=WeaponType.RAPIER; currentLine=br.readLine(); continue;}
                if(currentLine.contains("WYNIKI ALL")) {break;} // ta czesc nas nie interesuje
                String parts[] = currentLine.split("\\s+");
                if(parts.length<4) throw new IntegrityException("Blad integralnosci pliku/poplakac na mnie");
                StringBuilder cityAndNameBuilder = new StringBuilder();
                for(int i=2;i<parts.length;i++)
                {
                    cityAndNameBuilder.append(parts[i]);
                    cityAndNameBuilder.append(" ");
                }
                String cityAndName = cityAndNameBuilder.toString(); //sklada reszte stringa do kupy
                SingleScore ssTmp=new SingleScore(actualWeapon,Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),cityAndName);
                singleScoreList.add(ssTmp);




                currentLine=br.readLine();
            }
            if (actualWeapon!=WeaponType.RAPIER) throw new IntegrityException("Blad integralnosci pliku/poplakac na mnie");
        }
        finally
        {
            br.close();
        }

        TournamentScore ts= new TournamentScore(singleScoreList);
        scores.add(ts);
        System.out.printf("\nDone\n");
    }// end ParseZ


    private void parseS(String filePath) throws IOException, IntegrityException
    {
        File tF=new File(filePath);
        if(tF.canWrite()==false && tF.exists())
            throw new IOException("brak uprawnieni do nadpisania pliku: \'"+filePath+"\'");
        if(scores.size()< settings.getNumberOfTournaments())
        {
            System.out.printf("\nNie akceptowalna komenda, podales/as za malo liczbe plikow: liczbe plikow. Polecam 'h','z','m'\n");
            System.out.printf("Liczba plikow: " + scores.size() + "\n");
            System.out.printf("Wymagna liczba plikow: " + settings.getNumberOfTournaments() + "\n");
            return;
        }

        if(seasonS ==null)
        {
            seasonS= new SeasonScore(scores,settings);

        }

        //seasonS.print();
        seasonS.saveFile(filePath);




        System.out.printf("\nDone\n");
    }




}

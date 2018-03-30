package com.company.Model;
import com.company.others.IntegrityException;
import com.company.others.SettingsControl;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class PersonalScoreSeason {
    private int epeeOutcome;
    private int sabreOutcome;
    private int rapierOutcome;
    private boolean ifCalc;
    private LinkedList<PersonalScoreSingle>  singleOutcomesList;
    private String nameAndCity;
    private SettingsControl settings;
    PersonalScoreSeason(SettingsControl settings)
    {
        ifCalc=false;
        epeeOutcome =0;
        sabreOutcome =0;
        rapierOutcome =0;
        nameAndCity="";
        this.settings= settings;
    }
    PersonalScoreSeason(PersonalScoreSingle pssIn,SettingsControl settings)
    {
        ifCalc=false;
        epeeOutcome =0;
        sabreOutcome =0;
        rapierOutcome =0;
        this.settings= settings;
        singleOutcomesList=new LinkedList<>();
        nameAndCity=pssIn.getNameAndCity();
        singleOutcomesList.add(pssIn);

    }


    public String getSurname() throws IntegrityException
    {
        String parts[]=this.nameAndCity.split("\\s+");
        if(parts.length<2) throw new IntegrityException("Osoba musi miec imie i nazwisko oddzielone spacja");
        return parts[0];
    }

    public String getName() throws IntegrityException
    {
        String parts[]=this.nameAndCity.split("\\s+");
        if(parts.length<2) throw new IntegrityException("Osoba musi miec imie i nazwisko oddzielone spacja");
        return parts[1];
    }

    public boolean ifBelongs(PersonalScoreSingle pssIn) throws IntegrityException
    {
        if(this.getName().equals(pssIn.getName()) && this.getSurname().equals(pssIn.getSurname()))
            return true;
        else
            return false;
    }

    public void add(PersonalScoreSingle pssIn) throws IntegrityException
    {
        if(ifBelongs(pssIn)) {
            singleOutcomesList.add(pssIn);
        }
        else
        {
            throw new IntegrityException("ktos probowal polaczyc wyniki dwoch rzonych soob  z roznych zawodow");
        }
    }

    private void addDontCheck(PersonalScoreSingle pssIn)
    {
        singleOutcomesList.add(pssIn);
    }




    public void calculateResults() throws IntegrityException
    {
        if(ifCalc) return;
        if(singleOutcomesList.size()>settings.getNumberOfTournaments())
            throw new IntegrityException("Za duzo wynikow dla dnaje osoby, czyzby duplikat imienia i nazwiska");
        if(singleOutcomesList.size()<settings.getNumberOfTournaments())
        {
            for(int i=singleOutcomesList.size();i<=settings.getNumberOfTournaments();i++)
            {
                addDontCheck(new PersonalScoreSingle());
            }
        }
        int relevantOutcomesNr=settings.getNumberOfTournaments()-settings.getFailuresAcceptableCount();
        int[] intTable= new int[relevantOutcomesNr];
        int iSum=0;
        for(int i=0;i<relevantOutcomesNr;i++) intTable[i]=0;
            //---------------------- podsumowanie szpady
        Iterator<PersonalScoreSingle> pssIter= singleOutcomesList.iterator();
        PersonalScoreSingle pssTmp=null;
        int minVal=0;// no need for more
        int minId=0;
        while(pssIter.hasNext())
        {
            minVal=intTable[0];
            minId=0;
            for(int i=0;i<relevantOutcomesNr;i++)
            {
                if(intTable[i]<minVal)
                {
                    minVal=intTable[i];
                    minId=i;
                }
            }
            pssTmp=pssIter.next();
            if(intTable[minId]<pssTmp.getEpeeOutcome())// jesli napotkamy na wynik wiekszy niz te ktore juz znalezlismy
            {
                intTable[minId]=pssTmp.getEpeeOutcome();
            }
        }
        for(int i=0;i<relevantOutcomesNr;i++) iSum+= intTable[i];
        epeeOutcome +=iSum;
        iSum=0;
        for(int i=0;i<relevantOutcomesNr;i++) intTable[i]=0;

        //----------------------------podsumowanie szabli
        pssIter= singleOutcomesList.iterator();
        while(pssIter.hasNext())
        {
            minVal=intTable[0];
            minId=0;
            for(int i=0;i<relevantOutcomesNr;i++)
            {
                if(intTable[i]<minVal)
                {
                    minVal=intTable[i];
                    minId=i;
                }
            }
            pssTmp=pssIter.next();
            if(intTable[minId]<pssTmp.getSabreOutcome())// jesli napotkamy na wynik wiekszy niz te ktore juz znalezlismy
            {
                intTable[minId]=pssTmp.getSabreOutcome();
            }
        }
        for(int i=0;i<relevantOutcomesNr;i++) iSum+= intTable[i];
        sabreOutcome+=iSum;
        for(int i=0;i<relevantOutcomesNr;i++) intTable[i]=0;
        iSum=0;
        //----------------------------podsumowanie rapiera
        pssIter= singleOutcomesList.iterator();
        while(pssIter.hasNext())
        {
            minVal=intTable[0];
            minId=0;
            for(int i=0;i<relevantOutcomesNr;i++)
            {
                if(intTable[i]<minVal)
                {
                    minVal=intTable[i];
                    minId=i;
                }
            }
            pssTmp=pssIter.next();
            if(intTable[minId]<pssTmp.getRapierOutcome())// jesli napotkamy na wynik wiekszy niz te ktore juz znalezlismy
            {
                intTable[minId]=pssTmp.getRapierOutcome();
            }
        }
        for(int i=0;i<relevantOutcomesNr;i++) iSum+= intTable[i];
        rapierOutcome+=iSum;
        for(int i=0;i<relevantOutcomesNr;i++) intTable[i]=0;

        ifCalc=true;
    }//end calc Results





    public void show() //debug method
    {
        System.out.println(nameAndCity + " " + "epee:" + epeeOutcome + "  sabre:" + sabreOutcome + "  rapier:"+ rapierOutcome);
    }

    public String toStringEpee()
    {

        StringBuilder strB= new StringBuilder(Integer.toString(epeeOutcome) );
        strB.append("  ");
        strB.append(nameAndCity);
        return strB.toString();
    }
    public String toStringSabre()
    {

        StringBuilder strB= new StringBuilder(Integer.toString(sabreOutcome) );
        strB.append("  ");
        strB.append(nameAndCity);
        return strB.toString();
    }
    public String toStringRapier()
    {

        StringBuilder strB= new StringBuilder(Integer.toString(rapierOutcome) );
        strB.append("  ");
        strB.append(nameAndCity);
        return strB.toString();
    }
    public String toStringAll()
    {

        StringBuilder strB= new StringBuilder(Integer.toString(getTotalOutcome()) );
        strB.append("  ");
        strB.append(nameAndCity);
        return strB.toString();
    }






    public int getEpeeOutcome() {
        return epeeOutcome;
    }

    public int getSabreOutcome() {
        return sabreOutcome;
    }

    public int getRapierOutcome() {
        return rapierOutcome;
    }

    public int getTotalOutcome()
    {
        return epeeOutcome+sabreOutcome+rapierOutcome;
    }
}




class PssEpeeComparator  implements Comparator<PersonalScoreSeason>
{
    public int compare(PersonalScoreSeason pss1,PersonalScoreSeason pss2)
    {
        return pss2.getEpeeOutcome()-pss1.getEpeeOutcome();
    }
}


class PssSabreComparator  implements Comparator<PersonalScoreSeason>
{
    public int compare(PersonalScoreSeason pss1,PersonalScoreSeason pss2)
    {
        return pss2.getSabreOutcome()-pss1.getSabreOutcome();
    }
}



class PssRapierComparator  implements Comparator<PersonalScoreSeason>
{
    public int compare(PersonalScoreSeason pss1,PersonalScoreSeason pss2)
    {
        return pss2.getRapierOutcome()-pss1.getRapierOutcome();
    }
}


class PssAllComparator  implements Comparator<PersonalScoreSeason>
{
    public int compare(PersonalScoreSeason pss1,PersonalScoreSeason pss2)
    {
        return pss2.getTotalOutcome()-pss1.getTotalOutcome();
    }
}



















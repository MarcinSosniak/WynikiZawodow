package com.company.Model;
import com.company.others.IntegrityException;
import com.company.others.SettingsControl;

import java.util.Iterator;
import java.util.LinkedList;
import java.io.*;

public class SeasonScore {
    public LinkedList<PersonalScoreSeason> outcome;
    private SettingsControl settings;
    SeasonScore(SettingsControl settings)
    {
        this.settings=settings;
        outcome= new LinkedList<PersonalScoreSeason>();
    }


    public SeasonScore(LinkedList<TournamentScore> tsList, SettingsControl settings) throws IntegrityException
    {
        this.settings=settings;
        int currentFirst=0;
        int currentlyAsSecondId=1;
        int tourScoresCount=tsList.size();
        outcome= new LinkedList<PersonalScoreSeason>();
        PersonalScoreSeason pSseason=null;
        PersonalScoreSingle pSsingle=null;
        Iterator<PersonalScoreSingle> firstScoreIter = tsList.get(currentFirst).iterator();
        Iterator<PersonalScoreSingle> tempScoreIter=null;


        while(firstScoreIter.hasNext())// tworzy seazon score z pierwszego napotkanego wystapienia zawodnika
        {
            pSsingle=firstScoreIter.next();
            firstScoreIter.remove();
            pSseason= new PersonalScoreSeason(pSsingle,settings);

            currentlyAsSecondId=currentFirst+1;
            if(currentlyAsSecondId<tourScoresCount)
                tempScoreIter=tsList.get(currentlyAsSecondId).iterator();


            while(tempScoreIter.hasNext() && currentlyAsSecondId<tourScoresCount)
            {
                pSsingle=tempScoreIter.next();
                if(pSseason.ifBelongs(pSsingle))
                {
                    pSseason.add(pSsingle);
                    tempScoreIter.remove();
                }
                if( !tempScoreIter.hasNext() && currentlyAsSecondId<(tourScoresCount-1)) // jezeli rpzeszedl jedna lista liste, to leci z kolejna
                {
                    currentlyAsSecondId++;
                    tempScoreIter=tsList.get(currentlyAsSecondId).iterator();
                }
            }// jak skoncza sie listy

            outcome.add(pSseason);//save

            if( !firstScoreIter.hasNext() && currentFirst!=(tourScoresCount-1) ) // kiedy pierwsza lsita sie skonczy, moga pozostac niedodane osoby z innych list (nie wysepowaly w pierwszych zawodach wpisanych do programu)
            {
                currentFirst++;
                firstScoreIter=tsList.get(currentFirst).iterator();
            }


        }













        //TournamentScore firstTourScore=tsList.removeFirst();
        //LinkedList<PersonalScoreSingle>
        //Iterator<TournamentScore>= firstTourScore.






    }

    public void print() throws IntegrityException
    {
        int currentPosition=1;
        int noEqualsPos=1;
        int lastOutcome=-1;
        //debug
        for(PersonalScoreSeason p: outcome)
        {
            p.calculateResults();// important
            //p.show();//debug
        }
        System.out.printf("\n\n\n\n\n\n\n\n\n-----------------------------------------------------------\n\n\n");
        System.out.printf("WYNIKI SZPADA\n\n");
        outcome.sort(new PssEpeeComparator());

        for(PersonalScoreSeason p: outcome)
        {
            if(p.getEpeeOutcome()!=lastOutcome)
            {
                currentPosition=noEqualsPos;
            }
            lastOutcome=p.getEpeeOutcome();
            System.out.println(currentPosition +" "+ p.toStringEpee() );
            //p.show();// debug
            noEqualsPos++;

        }

        System.out.printf("WYNIKI SZABLA\n\n");
        outcome.sort(new PssSabreComparator());
        noEqualsPos=1;
        lastOutcome=-1;
        for(PersonalScoreSeason p: outcome)
        {
            if(p.getSabreOutcome()!=lastOutcome)
            {
                currentPosition=noEqualsPos;
            }
            lastOutcome=p.getSabreOutcome();
            System.out.println(currentPosition +" "+ p.toStringSabre() );

            //p.show();// debug
            noEqualsPos++;

        }
        System.out.printf("WYNIKI RAPIER\n\n");
        outcome.sort(new PssRapierComparator());

        noEqualsPos=1;
        lastOutcome=-1;
        for(PersonalScoreSeason p: outcome)
        {
            if(p.getRapierOutcome()!=lastOutcome)
            {
                currentPosition=noEqualsPos;
            }
            lastOutcome=p.getRapierOutcome();
            System.out.println(currentPosition +" "+ p.toStringRapier() );

            //p.show();// debug
            noEqualsPos++;

        }
        System.out.printf("WYNIKI ALL\n\n");
        outcome.sort(new PssAllComparator());
        noEqualsPos=1;
        lastOutcome=-1;
        for(PersonalScoreSeason p: outcome)
        {
            if(p.getTotalOutcome()!=lastOutcome)
            {
                currentPosition=noEqualsPos;
            }
            lastOutcome=p.getTotalOutcome();
            System.out.println(currentPosition +" "+ p.toStringAll() );

            //p.show();// debug
            noEqualsPos++;


        }

    }

    public void saveFile(String Path) throws IOException, IntegrityException
    {

        PrintWriter writer = new PrintWriter(Path, "UTF-8");


        int currentPosition=1;
        int noEqualsPos=1;
        int lastOutcome=-1;
        //debug
        for(PersonalScoreSeason p: outcome)
        {
            p.calculateResults();// important
            //p.show();//debug
        }

        writer.println("WYNIKI SZPADA");
        writer.println("");
        outcome.sort(new PssEpeeComparator());

        for(PersonalScoreSeason p: outcome)
        {
            if(p.getEpeeOutcome()!=lastOutcome)
            {
                currentPosition=noEqualsPos;
            }
            lastOutcome=p.getEpeeOutcome();
            writer.println(currentPosition +" "+ p.toStringEpee() );
            //p.show();// debug
            noEqualsPos++;

        }

        writer.println("");
        writer.println("WYNIKI SZABLA");
        writer.println("");
        outcome.sort(new PssSabreComparator());
        noEqualsPos=1;
        lastOutcome=-1;
        for(PersonalScoreSeason p: outcome)
        {
            if(p.getSabreOutcome()!=lastOutcome)
            {
                currentPosition=noEqualsPos;
            }
            lastOutcome=p.getSabreOutcome();
            writer.println(currentPosition +" "+ p.toStringSabre() );

            //p.show();// debug
            noEqualsPos++;

        }
        writer.println("");
        writer.println("WYNIKI RAPIER");
        writer.println("");
        outcome.sort(new PssRapierComparator());

        noEqualsPos=1;
        lastOutcome=-1;
        for(PersonalScoreSeason p: outcome)
        {
            if(p.getRapierOutcome()!=lastOutcome)
            {
                currentPosition=noEqualsPos;
            }
            lastOutcome=p.getRapierOutcome();
            writer.println(currentPosition +" "+ p.toStringRapier() );

            //p.show();// debug
            noEqualsPos++;

        }
        writer.println("");
        writer.println("WYNIKI ALL");
        writer.println("");
        outcome.sort(new PssAllComparator());
        noEqualsPos=1;
        lastOutcome=-1;
        for(PersonalScoreSeason p: outcome)
        {
            if(p.getTotalOutcome()!=lastOutcome)
            {
                currentPosition=noEqualsPos;
            }
            lastOutcome=p.getTotalOutcome();
            writer.println(currentPosition +" "+ p.toStringAll() );

            //p.show();// debug
            noEqualsPos++;


        }

        writer.close();
    }//end saveFile

}

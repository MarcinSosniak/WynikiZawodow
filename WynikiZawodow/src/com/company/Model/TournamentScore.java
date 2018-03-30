package com.company.Model;

import com.company.others.IntegrityException;

import java.util.LinkedList;
import java.util.Iterator;
public class TournamentScore implements Iterable<PersonalScoreSingle>
{
    private LinkedList<PersonalScoreSingle> outcome;

    public TournamentScore(LinkedList<SingleScore> inptList) throws IntegrityException
    {
        outcome= new LinkedList<PersonalScoreSingle>();
        //int iListSize=outcome.size();

        PersonalScoreSingle newPSS=null;
        SingleScore sscpy=null;

        Iterator<SingleScore> iter=  inptList.iterator();
        while(inptList.size()>0) // dodaje n osob
        {
            newPSS =new PersonalScoreSingle(iter.next());
            iter.remove();
            while(iter.hasNext()) // dodaje wyniki danej osoby
            {
                sscpy=iter.next();
                if(newPSS.ifBelongs(sscpy))
                {
                    newPSS.add(sscpy);
                    iter.remove();
                }

            }
            outcome.add(newPSS);

            iter=inptList.iterator(); // reest iteratora, juz po wyruceniu przetworzonych wynikow
        }







    }//TournamentScore

    public  Iterator<PersonalScoreSingle> iterator()
    {
        return outcome.iterator();
    }


    public void AddPoints(int val)
    {
        for(PersonalScoreSingle e : outcome)
            e.addPoints(val);
    }

    public void AddScore(PersonalScoreSingle newScore)
    {
        ;
    }
    public void show ()
    {
        for(PersonalScoreSingle t: outcome)
        {
            t.show();
        }
    }





}

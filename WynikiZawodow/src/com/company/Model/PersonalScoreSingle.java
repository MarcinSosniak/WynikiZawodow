package com.company.Model;

import com.company.others.IntegrityException;

public class PersonalScoreSingle {
    private int epeeOutcome;
    private int sabreOutcome;
    private int rapierOutcome;
    private String nameAndCity;

    PersonalScoreSingle()
    {
        epeeOutcome =0;
        sabreOutcome =0;
        rapierOutcome =0;
        nameAndCity="";
    }

    public PersonalScoreSingle(SingleScore score) throws IntegrityException {
        epeeOutcome =0;
        sabreOutcome =0;
        rapierOutcome =0;
        nameAndCity="";
        add(score);
    }


    public void add(SingleScore score) throws IntegrityException
    {
        if(this.nameAndCity.length() ==0)
        {
            //       System.out.println("<<<<<<<<<<proba dodania nazwy>>>>>>>>>>>>>>>");
            this.nameAndCity = score.nameAndCity;
        }
        else
        {
            String parts1[] = this.nameAndCity.split("\\s+");
            String parts2[] = score.nameAndCity.split("\\s+");
            if ((parts1.length < 2) || (parts2.length < 2))
                throw new IntegrityException("Osoba musi miec imie i nazwisko oddzielone spacja");

            if (parts1[0].equals(parts2[0]) && parts1[1].equals(parts2[1]))
                ;
            else
                throw new IntegrityException("Proba pomieszania wynikow roznych osob");
        }//end else

        switch (score.weapon) {
            case EPEE:
                epeeOutcome = score.score;
                break;
            case SABRE:
                sabreOutcome = score.score;
                break;
            case RAPIER:
                rapierOutcome = score.score;
        }



    }

    public boolean ifBelongs(SingleScore score) throws IntegrityException
    {
        if(this.nameAndCity.length() ==0)
            return true;
        else
        {
            if(  (this.getName()).equals(score.getName()) && (this.getSurname()).equals(score.getSurname())   )
            {
                return true;
            }
            else
                return false;
        }//end else
    }

    public boolean ifSamePerson(Object second) throws IntegrityException
    {
        if(second instanceof PersonalScoreSingle)
        {
            String parts1[] = this.nameAndCity.split("\\s+");
            String parts2[] = ((PersonalScoreSingle)second).nameAndCity.split("\\s+");
            if( (parts1.length <2) || (parts2.length < 2) )
                throw new IntegrityException("Osoba musi miec imie i nazwisko oddzielone spacja");

            if ( parts1[0].equals(parts2[0]) && parts1[1].equals(parts2[1]) )
                return true;
            else
                return false;
        }
        else
            return false;
    }


    public void addPoints(int value)
    {
        epeeOutcome +=value;
        sabreOutcome +=value;
        rapierOutcome +=value;
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

    public String getNameAndCity() {
        return nameAndCity;
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

    public void show() //debug method
    {
        System.out.println(nameAndCity + " " + "epee:" + epeeOutcome + "  sabre:" + sabreOutcome + "  rapier:"+ rapierOutcome);
    }


}
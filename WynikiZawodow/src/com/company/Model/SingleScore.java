package com.company.Model;
import com.company.others.IntegrityException;
import com.company.others.WeaponType;

import java.lang.*;



public class SingleScore {
    public final WeaponType weapon;
    public final int score;
    public final String nameAndCity;
    public SingleScore(WeaponType weapon,int position, int score,String nameAndCity)
    {
        this.weapon=weapon;
        if(position==0)
            this.score=0; // file containes such mistake
        else
            this.score=score;
        this.nameAndCity=nameAndCity;
    }

    public boolean equals(SingleScore second)
    {

            if(
                    this.weapon==second.weapon &&
                    this.score==second.score &&
                    this.nameAndCity.equals(second.nameAndCity)
               )
                return true;
            else
                return false;

    }


    public boolean ifSamePerson(SingleScore second) throws IntegrityException
    {
        if(  (this.getName()).equals(second.getName()) && (this.getSurname()).equals(second.getSurname())   )
        {
           return true;
        }
        else
            return false;
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

    public void show()  // debug function
    {
        System.out.println(weapon.name() + " " + score + " " + nameAndCity );
    }

}
















































/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class DvdDaoImpl implements DvdDao {

    private List<Dvd> dvds = new ArrayList();
    private int nextId;
    private String readWriteFile;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

//    public DvdLibraryDaoImpl() throws FileNotFoundException {
//        this(DEFAULT_WRITE_FILE);
//    }
    public DvdDaoImpl() {
        readWriteFile = "default.txt";
        dvds = decode(readWriteFile);

    }

    public DvdDaoImpl(String readWriteFile) {
        this.readWriteFile = readWriteFile;
        dvds = decode(readWriteFile);

    }

    @Override
    public Dvd create(Dvd dvd) {

        int highestId = 0;

        for (Dvd myDvd : dvds) {
            if (myDvd.getId() > highestId) {
                highestId = myDvd.getId();
            }
        }

        nextId = highestId + 1;

        dvd.setId(nextId);

        dvds.add(dvd);

        encode(readWriteFile);

        return dvd;

    }

    @Override
    public Dvd get(Integer id) {

        for (Dvd dvd : dvds) {
            if (dvd.getId() == id) {
                return dvd;
            }
        }
        return null; //if get returns null, student is not in the database
    }

    @Override
    public List<Dvd> getList() {
        return dvds;
    }

    @Override
    public void update(Dvd dvd) {

        Dvd found = new Dvd();

        for (Dvd myDvd : dvds) {

            if (myDvd.getId() == dvd.getId()) {
                found = myDvd;

            }
        }
        dvds.remove(found);
        dvds.add(dvd);
        encode(readWriteFile);
    }

    @Override
    public void delete(Integer id) {

        Dvd found = null;

        for (Dvd myDvd : dvds) {

            if (myDvd.getId() == id) {
                found = myDvd;
                break;
            }
        }
        dvds.remove(found);

        encode(readWriteFile);

    }

    @Override
    public List<Dvd> searchByDate(Date date) {

//        Calendar cal = Calendar.getInstance();
////        Date today = cal.getTime();
//        cal.add(Calendar.YEAR, -years);
//        Date rangeDate = cal.getTime();
        List<Dvd> returnDvds = new ArrayList<>();

        for (Dvd dvd : dvds) {
            if (dvd.getReleaseDate().after(date)) {
                returnDvds.add(dvd);
            }
        }

        return returnDvds;

    }

    @Override
    public double calculateAverageDvdAge() {

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        Date releaseDate;
        int diffInDays;
        int daysOldTotal = 0;

        for (Dvd dvd : dvds) {
            releaseDate = dvd.getReleaseDate();
            long duration = today.getTime() - releaseDate.getTime();
            diffInDays = (int) TimeUnit.MILLISECONDS.toDays(duration);
            daysOldTotal += diffInDays;
        }

        if (dvds.isEmpty()) {
            return 0d;
        } else {

            int aveDayAge = daysOldTotal / dvds.size();
            double aveYearsAge = aveDayAge / 365d;

            return aveYearsAge;
        }
    }

    @Override
    public Dvd getNewestDvd() {

        Dvd newestDvd = null;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -100);
        Date youngestDate = cal.getTime();

        for (Dvd dvd : dvds) {
            if (dvd.getReleaseDate().after(youngestDate)) {
                youngestDate = dvd.getReleaseDate();
                newestDvd = dvd;
            }
        }
        return newestDvd;
    }

    @Override
    public Dvd getOldestDvd() {

        Calendar cal = Calendar.getInstance();
        Date oldestDate = cal.getTime();
        Dvd oldestDvd = null;

        for (Dvd dvd : dvds) {
            if (dvd.getReleaseDate().before(oldestDate)) {
                oldestDate = dvd.getReleaseDate();
                oldestDvd = dvd;
            }
        }
        return oldestDvd;

    }

    @Override
    public List<Dvd> searchBy(String attributeType, String attributeValue) {

        List<Dvd> returnDvds = new ArrayList<>();
        for (Dvd dvd : dvds) {
            if (dvd.searchBy(attributeType).equalsIgnoreCase(attributeValue)) {
                returnDvds.add(dvd);
            }
        }
        return returnDvds;

    }

    @Override
    public Map<String, List<Dvd>> sortByDirectorRating(String director) {

        Map<String, List<Dvd>> dvdsByDirectorByRating = new HashMap();

        List<Dvd> dvdsByDirector = new ArrayList<>();

        Set<String> ratings = new HashSet();

        for (Dvd dvd : dvds) {
            if (director.equalsIgnoreCase(dvd.getDirector())) {
                dvdsByDirector.add(dvd);
            }
        }

        for (Dvd dvd : dvdsByDirector) {
            ratings.add(dvd.getDirector());
        }

        for (String rating : ratings) {
            List<Dvd> dvdsByRating = new ArrayList<>();
            for (Dvd dvd : dvdsByDirector) {
                if (rating.equals(dvd.getRating())) {
                    dvdsByRating.add(dvd);
                }
            }
            dvdsByDirectorByRating.put(rating, dvdsByRating);
        }
        return dvdsByDirectorByRating;

    }

    private void encode(String file) {

        final String TOKEN = "::";

        try {
            PrintWriter out = new PrintWriter(new FileWriter(file));

            for (Dvd myDvd : dvds) {

                out.print(myDvd.getId());
                out.print(TOKEN);

                out.print(myDvd.getTitle());
                out.print(TOKEN);

                if (myDvd.getReleaseDate() != null) {
                    out.print(dateFormat.format(myDvd.getReleaseDate()));
                } else {
                    out.print("");
                }
                out.print(TOKEN);

                out.print(myDvd.getRating());
                out.print(TOKEN);

                out.print(myDvd.getDirector());
                out.print(TOKEN);

                out.print(myDvd.getStudio());
                out.print(TOKEN);

                out.print(myDvd.getUserNote());
                out.print(TOKEN);

                out.println("");
            }

            out.flush();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(DvdDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Dvd> decode(String file) {


        List<Dvd> dvdList = new ArrayList();
        Date releaseDate = null;

        try {

            Scanner sc = new Scanner(new BufferedReader(new FileReader(file)));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split("::");

                Dvd myDvd = new Dvd();

                int id = Integer.parseInt(stringParts[0]);
                myDvd.setId(id);                myDvd.setTitle(stringParts[1]);
                if (!stringParts[2].equals("")) {

                    try {
                        releaseDate = dateFormat.parse(stringParts[2]);
                    } catch (ParseException ex) {
                        Logger.getLogger(DvdDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    myDvd.setReleaseDate(releaseDate);
                }
                myDvd.setRating(stringParts[3]);
                myDvd.setDirector(stringParts[4]);
                myDvd.setStudio(stringParts[5]);
                if (stringParts.length < 7) {
                    myDvd.setUserNote("");
                } else if (stringParts.length == 7) {
                    myDvd.setUserNote(stringParts[6]);
                }
                dvdList.add(myDvd);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DvdDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dvdList;
    }

}

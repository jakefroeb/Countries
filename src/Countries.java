import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Countries {
    public static void main(String[] args) throws FileNotFoundException {
        List<Country> countries = new ArrayList<Country>();
        Map<String, ArrayList<Country>> countriesSortedByFirstLetter = new HashMap<>();
        Scanner consoleScanner = new Scanner(System.in);

        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Country country = new Country(columns[1], columns[0]);
            countries.add(country);
        }

        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            String firstLetter = String.format("%s", alphabet);
            ArrayList<Country> countryByFirstLetter = new ArrayList<Country>();
            for (Country country : countries) {
                if (country.getName().startsWith((firstLetter))) {
                    countryByFirstLetter.add(country);
                }
            }
            countriesSortedByFirstLetter.put(firstLetter, countryByFirstLetter);
        }

        System.out.print("Enter a letter: ");
        String letter = consoleScanner.nextLine().toLowerCase();
        ArrayList<Country> countriesReadyToSave = countriesSortedByFirstLetter.get(letter);
        try {
            testInput(countriesReadyToSave, letter);
        } catch (Exception e) {
            System.out.println("No countries start with that letter");
        }
        System.out.println(countriesReadyToSave.toString());

    }


    private static void testInput(ArrayList<Country> countriesReadyToSave, String input) throws Exception {
        if (!countriesReadyToSave.isEmpty()) {
            for (Country country : countriesReadyToSave) {
                try {
                    saveCountries(countriesReadyToSave, input);
                } catch (IOException e) {
                    System.out.println("couldnt save");
                }
            }
        } else {
            throw new Exception("your letter did not match any ");
        }
    }

    private static void saveCountries(ArrayList<Country> countriesReadyToSave, String input) throws IOException {
        JsonSerializer s = new JsonSerializer();
        String json = s.serialize(countriesReadyToSave);
        File f = new File(input + "_Countries.json");
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }
}

package bg.smg;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File input = new File("resources/testData_Apartments.txt");
        String output = "output.txt";

        Map<String, Integer> cityApCount = new HashMap<>();
        List<String> citiesMostApartments = new ArrayList<>();
        List<String> brokerNumbers = new ArrayList<>();
        int mostApartments =0;

        try(Scanner sc = new Scanner(input))
        {
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",\\s*");
                if (parts.length == 5) {
                    String city = parts[0];
                    int rooms = Integer.parseInt(parts[1]);
                    int area = Integer.parseInt(parts[2]);
                    int price = Integer.parseInt(parts[3]);
                    String phoneNumber = parts[4];
                    cityApCount.put(city, cityApCount.getOrDefault(city,0) + 1);

                    Apartment apartment = new Apartment(city, phoneNumber, rooms, area, price);
                    if(apartment.getArea()>=100 && apartment.getRooms()==3 && (city.equals("София") || city.equals("Варна") || city.equals("Бургас")))
                        brokerNumbers.add(apartment.getPhoneNumber());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + input);
            return;
        }

        List<Map.Entry<String, Integer>> cityApList = new ArrayList<>(cityApCount.entrySet());
        cityApList.sort(Map.Entry.comparingByValue());

        for (Map.Entry<String, Integer> entry : cityApList) {
            String grad = entry.getKey();
            int br = entry.getValue();

            if (br > mostApartments) {
                mostApartments = br;
                citiesMostApartments.clear();
                citiesMostApartments.add(grad);
            } else if (br == mostApartments) citiesMostApartments.add(grad);
        }

        try (PrintWriter wr = new PrintWriter(new FileWriter(output))) {
            if (brokerNumbers.isEmpty()) throw new UnsuitableApartmentsException("Няма такива апартаменти!");

            Set<String> numbersSet = new HashSet<>(brokerNumbers);
            for (String phoneNumber : numbersSet) wr.println(phoneNumber);
            wr.println("Градове с най-много апартаменти:");
            for (String city : citiesMostApartments) wr.println(city);
            System.out.println("Изходен файл:" + output);
        } catch (UnsuitableApartmentsException | IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}

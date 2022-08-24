package com.thewhitevillager.Utilities;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.checkerframework.checker.units.qual.K;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    // function to sort hashmap by values
    public static <K> LinkedHashMap<K, Double> sortByValueDouble(LinkedHashMap<K, Double> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<K, Double> > list =
                new LinkedList<Map.Entry<K, Double> >(hm.entrySet());

        // Sort the list
        list.sort(new Comparator<Map.Entry<K, Double>>() {
            public int compare(Map.Entry<K, Double> o1,
                               Map.Entry<K, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        LinkedHashMap<K, Double> temp = new LinkedHashMap<K, Double>();
        for (Map.Entry<K, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return invertMap(temp);
    }
    public static <K> LinkedHashMap<K, Integer> sortByValueInt(LinkedHashMap<K, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<K, Integer> > list =
                new LinkedList<Map.Entry<K, Integer> >(hm.entrySet());

        // Sort the list
        list.sort(new Comparator<Map.Entry<K, Integer>>() {
            public int compare(Map.Entry<K, Integer> o1,
                               Map.Entry<K, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        LinkedHashMap<K, Integer> temp = new LinkedHashMap<K, Integer>();
        for (Map.Entry<K, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return invertMap(temp);
    }

    public static <K, V> LinkedHashMap<K, V> invertMap(LinkedHashMap<K, V> map) {
        LinkedHashMap<K, V> inverted = new LinkedHashMap<>();
        ArrayList<K> keys = new ArrayList<>();
        ArrayList<V> values = new ArrayList<>();

        for (Map.Entry<K, V> entry:
                map.entrySet()) {
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }

        Collections.reverse(keys);
        Collections.reverse(values);

        for (int i = 0; i < map.size(); i++) {
            inverted.put(keys.get(i), values.get(i));
        }

        return inverted;
    }

    public static String formatList(List<Object> list) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Object element = list.get(i);

            if (i == list.size()-1) {
                formatted.append(element);
            } else {
                formatted.append(element).append(", ");
            }
        }

        return formatted.toString();
    }

//    public static int getRandomNumber(int min, int max) {
//        return (int) ((Math.random() * (max - min)) + min);
//    }

    public static int getRandomNumber(int min, int max) {
        return getRandomNumber(min, max, 0);
//        return (int) ((Math.random() * (max - min)) + min);
    }

    public static int getRandomNumber(int min, int max, double luck) {
        /*SecureRandom sr = new SecureRandom();
        int randomNumber = (int) (Math.random() + (luck - 0.5f) * (Math.random()*//*sr.nextGaussian()*//* * (max - min) + min));
//        if (randomNumber < min) {
//            randomNumber = min;
//        }
//        randomNumber = Math.min(randomNumber, max);
//        randomNumber = Math.max(randomNumber, min);
        return randomNumber;*/

//        return (int) ((Math.random() * (max - min)) + min);
        return (int) Math.round(getRandomNumber((double) min, max, luck));
    }



    public static double getRandomNumber(double min, double max) {
        return getRandomNumber(min, max, 0);
//        return ((Math.random() * (max - min)) + min);
    }

    /**
     *
     * @param min Minimum
     * @param max Maximum
     * @param luck Luck Modifier, double between -.1 and .1
     * @return
     */
    public static double getRandomNumber(double min, double max, double luck) {
        Validate.isTrue(-.1 <= luck && luck <= .1);
        double random = Math.random();
        double randomWithLuck = random+luck;

        if (Math.min(randomWithLuck, 0) != 0) {
            randomWithLuck = 0;
        }
        if (Math.max(randomWithLuck, 1) != 1) {
            randomWithLuck = 1;
        }

        return (double) ((randomWithLuck * (max - min)) + min);
    }

//    public static void launchFirework(Location location, FireworkMeta meta)

}

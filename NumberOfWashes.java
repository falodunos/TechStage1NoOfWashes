/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.decagon.stageone.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Olugbenga.Falodun
 */
public class NumberOfWashes {

    public static void main(String[] args) {
        Integer[] cleanPile = {1, 2, 1,1};
        Integer[] dirtyPile = {1, 4, 3, 2, 4};
        System.out.println("numberOfWashes :: " + numberOfWashes(2, cleanPile, dirtyPile));
    }

    private static int numberOfWashes(int noOfWashes, Integer[] cleanPile, Integer[] dirtyPile) {

        boolean constraint = (0 <= noOfWashes && noOfWashes <= 50)
                && (1 <= cleanPile.length && cleanPile.length <= 50)
                && (1 <= dirtyPile.length && dirtyPile.length <= 50);

        int pairsCount = 0;
        int pairsFromCleanPile = 0;
        if (constraint) {
            if (noOfWashes > 0) {
                HashMap<Integer, Integer> cleanMap = new HashMap();

                for (int i = 0; i < cleanPile.length; i++) {
                    int key = cleanPile[i];
                    if (cleanMap.containsKey(key)) {
                        int value = cleanMap.get(key) + 1;
                        cleanMap.put(key, value);
                    } else {
                        cleanMap.put(key, 1);
                    }
                }

                ArrayList<Integer> singgleSocksInCleanPile = new ArrayList<>();

                for (Map.Entry<Integer, Integer> entry : cleanMap.entrySet()) {
                    int pair = entry.getValue() / 2;
                    pairsFromCleanPile += pair;

                    if (entry.getValue() % 2 == 1) {
                        singgleSocksInCleanPile.add(entry.getKey());
                    }
                }

                boolean isMaxPairs = true;
                Set<Integer> dirtySet = new HashSet<>(Arrays.asList(dirtyPile));

                List<Integer> dirtyPileList = Arrays.asList(dirtyPile);

                if (singgleSocksInCleanPile.size() == noOfWashes) {
                    for (Integer sock : singgleSocksInCleanPile) {
                        if (!dirtySet.contains(sock)) {
                            isMaxPairs = false;
                        } else {
                            dirtySet.remove(sock);
                        }
                    }
                    if (isMaxPairs) {
                        pairsCount = pairsFromCleanPile + noOfWashes;
                    }
                } else {
                    for (int i = 0; i < noOfWashes; i++) {
                        List<Integer> copyList = new ArrayList(dirtyPileList);
                        
                        if (noOfWashes < dirtyPileList.size()) {
                            if (dirtyPileList.contains(singgleSocksInCleanPile.get(i))) {
                                pairsCount += 1;

                                Iterator<Integer> itr = dirtyPileList.iterator();
                                while (itr.hasNext()) {
                                    Integer item = itr.next();
                                    if (item.equals(dirtyPileList.get(i))) {
                                        copyList.remove(i);
                                    }
                                }
                                dirtyPileList = copyList;
                            }
                        }
                    }

                    pairsCount += pairsFromCleanPile;
                }
            }
        }
        return pairsCount;
    }
}

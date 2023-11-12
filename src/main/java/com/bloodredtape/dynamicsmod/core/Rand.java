package com.bloodredtape.dynamicsmod.core;

import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class Rand <T>{
    private final ArrayList<Pair<Float, T>> chanceMap = new ArrayList<>();
    private float chanceSum = 0.f;
    private static Random rand = new Random();

    public Rand<T> With(float chance, T value){
        chanceSum += chance;
        chanceMap.add(new Pair<>(chance, value));
        return this;
    }

    public T Get(){
        float seed = rand.nextFloat(1.f);

        float it = 0.f;

        for(var pair: chanceMap){
            it += pair.getFirst() * (1.f/chanceSum);

            if(it >= seed)
                return pair.getSecond();
        }

        assert false;
        return null;
    }
}

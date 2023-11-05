package com.bloodredtape.dynamicsmod.utils;

public class Math {
    static boolean isPrime(long n)
    {
        // Corner case
        if (n <= 1)
            return false;

        for (long i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}

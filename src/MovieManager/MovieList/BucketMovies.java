/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MovieManager.MovieList;

import MovieManager.DataTree.Tree;

/**
 *
 * @author joshdelcid
 */
public class BucketMovies {
    private final int BUCKET_SIZE = 13;
    private final Tree[] Bucket;
    
    public BucketMovies()
    {
        Bucket = new Tree[BUCKET_SIZE];
        for (int i = 0; i < BUCKET_SIZE; i++) {
            Bucket[i] = new Tree();
        }
    }
    
    public boolean add(String movieName, String description, int Rating)
    {
        int index = hash(movieName);
        int Code = generateCode(index, movieName);
        return Bucket[index].add(movieName, description, Rating, Code);
    }
    
    private int generateCode(final int BucketIndex, final String movieName)
    {
        String code = String.format("%02d", BucketIndex);

        final int nameInt = multCharName(movieName) ^ BucketIndex;

        int c1 = nameInt % 10;
        int c2 = ((nameInt) ^ 13) % 10;
        int c3 = (((c1 + 1) * (c2 + 1)) ^ (nameInt + BucketIndex)) % 10;
        int c4 = (c1 > 1) ? (nameInt / 2) % (c1) : 0;
        int c5 = ((c1 * BucketIndex) * (c2 * BucketIndex) * (c3 * BucketIndex) * (c4 * BucketIndex)) % 10;

        code += String.format("%d%d%d%d%d", Math.abs(c1), Math.abs(c2), Math.abs(c3), Math.abs(c4), Math.abs(c5));
        return Integer.parseInt(code);
    }
    
    private int multCharName(final String movieName)
    {
        int result = 0;
        for (int i = 0; i < movieName.length(); i++) {
            result += (int) movieName.charAt(i);
        }
        return result;
    }
    
    private int hash(String movieName)
    {
        int Hash = 1;
        for (int i = 0; i < movieName.length(); i++) {
            Hash += ((Hash * (int) movieName.charAt(i))^7);
        }
        return java.lang.Math.abs(Hash % BUCKET_SIZE);
    }

    public Movies searchFor(String MovieName)
    {
        if (MovieName.isBlank()) return null;

        int index = hash(MovieName);
        try {
            return Bucket[index].search(generateCode(index, MovieName));
        } catch (NullPointerException e) {
            return null;
        }
    }

    public boolean remove(String MovieName)
    {
        if (MovieName.isBlank()) return false;

        int index = hash(MovieName);
        try {
            return Bucket[index].remove(generateCode(index, MovieName));
        } catch (NullPointerException e) {
            return false;
        }
    }
    
}

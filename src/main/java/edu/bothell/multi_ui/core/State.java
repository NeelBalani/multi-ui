package edu.bothell.multi_ui.core;
import java.util.Arrays;
public class State implements Statable<int[]> {

    public final char[][] S = new char[16][16];

    public State(){
        System.out.println("CREATING THE STATE...");
        for(char[] row: S)
        for(int x = 0; x < row.length; x++)
        row[x] = ' ';
    }

    public int getSize(){
        return S.length * S[0].length;
    }

    public void setIt(char it, int x, int y){
        this.S[y][x] = it;
        System.out.println(Arrays.deepToString(S));
    }

    public char getIt(int x, int y){
        return S[y][x];
    }

    public char[][] getIt(){
        return S;
    }

    @Override
    public Object getIt(int[] pos){
        return S[pos[1]][pos[0]];
    }

    @Override
    public boolean isOpen(int[] pos){
        return S[pos[1]][pos[0]] == ' ';
    }

    @Override
    public int[] getAdj(int x, int y, Directions d) {
        return new int[]{x + d.dX(), y + d.dY() };
    }

    public int getVal(boolean x){
        if(x) return S[0].length;
        else return S.length;
    }

    public char getChar(int x, int y){
        return S[y][x];
    }

}
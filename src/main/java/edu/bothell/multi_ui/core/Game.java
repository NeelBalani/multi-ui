package edu.bothell.multi_ui.core;

import java.util.ArrayList;


public class Game {
    private final int                  MAX_PLAYERS = 3;
    private final ArrayList<Player>    p;
    private final State                s;
    private int                        turn;
    private Player                     active;

    public Game(Control c){
        this.turn = 0;
        this.s = (State) new World();
        this.p = new ArrayList<>();
    }
    
    public Player addPlayer(Player p){
        this.p.add(p);
        if(this.active == null) active = p;

        return p;
    }

    public Player addPlayer(char c, String sId){
        Player p = new Player(c);
        p.setSId(sId);
        return addPlayer(p);
    }

    public char[] getPlayersChar(){
        char[] pcs = new char[p.size()];
        for(int i = 0; i < pcs.length; i++) 
            pcs[i] = p.get(i).getChar();
        
        return pcs;
    }
    
    public boolean isValid(int[] pos, String sId){
        System.out.println("isVAlid?"+s.getIt(pos)+"|" + sId+"|" + active.getSId()+"|");
        return s.isOpen(pos) && active.getSId().equals(sId);
    }

    public char play(int[] pos, String sId){
        if(!isValid(pos, sId)) return ' ';
        turn++;
        this.s.setIt(active.getChar(), pos[0], pos[1]);
        this.active = p.get( turn % p.size() );

        return active.getChar();
    }

    public Player getActive() {
        return this.active;
    }

    public State getState() {
        return this.s;
    }

    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    public int getPlayerCount() {
        return p.size();
    }

    public ArrayList<Player> getPlayers(){
        return this.p;
    }

    public int getTurn(){
        return this.turn;
    }


}
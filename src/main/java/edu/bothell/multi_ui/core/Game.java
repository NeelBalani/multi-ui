package edu.bothell.multi_ui.core;
import java.util.ArrayList;

public class Game {

    private final int MAX_PLAYERS = 3;
    private final ArrayList<Player> p;
    private final State s;
    private int turn;
    private Player active;
    public Game(Control c){
        this.turn = 0;
        this.s = new World();
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
        if(!isValid(pos, sId)) {
            return ' ';
        }
        turn++;
        this.s.setIt(active.getChar(), pos[0], pos[1]);
        this.active = p.get( turn % p.size() );
        checkWin();
        return active.getChar();
    }

    public Player getActive() {
        return this.active;
    }

    public State getState() {
        return this.s;
    }

    public Location getLocation(int x, int y) {
        return ((World)s).getLocation(x, y);
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

    public void checkWin(){
        System.out.println(checkHor());
        System.out.println(checkVert());
        System.out.println(checkTie());
        System.out.println(checkDiagN());
        System.out.println(checkDiagP());
        if(checkVert() || checkHor()||checkTie()||checkDiagN()||checkDiagP()){
        System.exit(0);}
    }


    public boolean checkDiagP(){
        boolean checky = false;
        int count = 0;

        for(int i = 0; i < s.getVal(false); i++){
            if(s.getChar(i, s.getVal(true) - 1 - i) == active.getChar()){
                count++;
                if(count == 4){
                    checky = true;
                    break;
                }
            }
            else{
                count = 0;
                checky = false;
            }
        }
        return checky;
    }


    public boolean checkDiagN(){

        boolean checky = false;
        int count = 0;

        for(int i = 0; i < s.getVal(true); i++){
                    if(s.getChar(i,i) == active.getChar()){
                        count++;
                        if(count == 4){
                            checky = true;
                            break;
                        }
                    }
                    else{
                        count = 0;
                        checky = false;
                    }
                }
        return checky;
    }

    public boolean checkVert(){
        boolean checky = false;
        int count = 0;
        for(int i = 0; i < s.getVal(true); i++){
            checky = true;
            for(int o = 0; o < s.getVal(false); o++){
                if(s.getChar(i, o) == active.getChar()){
                    count++;
                    if(count == 4){
                        checky = true;
                        break;
                    }
                }
                else{
                    count = 0;
                    checky = false;
                    }
            }
            if(checky){
                return true;
            }
        }
        return checky;
    }

    public boolean checkHor(){
        boolean checky = false;
        int count = 0;
        for(int i = 0; i < s.getVal(false); i++){
            checky = true;
            for(int o = 0; o < s.getVal(true); o++){
                if(s.getChar(o, i) == active.getChar()){
                    count++;
                    if(count == 4){ 
                        checky = true;
                        break;
                    }
                }
                else{
                    count = 0;
                    checky = false;
                }
            }
            if(checky){
                return true;
            }
        }
        return checky;
    }

    public boolean checkTie(){
        if(turn == s.getSize()){
            return true;
        }
        return false;
    }
}
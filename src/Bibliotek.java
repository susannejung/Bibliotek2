import java.util.ArrayList;

public class Bibliotek {
    private ArrayList<Bog> bogListe=new ArrayList<>();
    private ArrayList<Laener> laenerListe=new ArrayList<>();

    public Bibliotek() {
    }

    public Bibliotek(ArrayList<Bog> bogListe, ArrayList<Laener> laenerListe) {
        this.bogListe = bogListe;
        this.laenerListe = laenerListe;
    }

    public ArrayList<Bog> getBogListe() {
        return bogListe;
    }

    public void setBogListe(ArrayList<Bog> bogListe) {
        this.bogListe = bogListe;
    }

    public ArrayList<Laener> getLaenerListe() {
        return laenerListe;
    }

    public void setLaenerListe(ArrayList<Laener> laenerListe) {
        this.laenerListe = laenerListe;
    }

    public void tilfoejBog(Bog b){
        this.bogListe.add(b);
    }

    public void tilfoejBog(int bogId, String t,String f){
        this.bogListe.add(new Bog(bogId,t,f));
    }


    public void tilfoejLaener(Laener l){
        this.laenerListe.add(l);
    }

    public void tilfoejLaener(int lId,String fnavn, String enavn){
        this.laenerListe.add(new Laener(lId,fnavn,enavn));
    }

    public void udlaenBog(int bogId,int lId,String dato){
        int indexBog=getIndexBogListe(bogId);
        int indexLaener=getIndexLaenerListe(lId);
        bogListe.get(indexBog).udlaenBog(lId,dato);
        laenerListe.get(indexLaener).laenBog(bogId);
    }

    private int getIndexBogListe(int bogId){
        for(int i=0;i<bogListe.size();i++) {
            if (bogListe.get(i).getBogId() == bogId) {
                return i;
            }
        }
        return -1;
    }

    private int getIndexLaenerListe(int laenerNr){
        for(int i=0;i<laenerListe.size();i++) {
            if (laenerListe.get(i).getlNr() == laenerNr) {
                return i;
            }
        }
        return -1;
    }

    public boolean erBogUdlant(int bogId){
        int index=getIndexBogListe(bogId);
        if(bogListe.get(index).isUdlant())
            return true;
        else
            return false;
    }

    public void afleverBog(int bogId){
        int index=getIndexBogListe(bogId);
        int lNr=bogListe.get(index).getLaener();
        bogListe.get(index).afleverBog();
        index=getIndexLaenerListe(lNr);
        laenerListe.get(index).afleverBog(bogId);

    }

    public ArrayList<Bog> udlanteBoeger(){
        ArrayList<Bog> udlante=new ArrayList<>();
        for(int i=0;i<bogListe.size();i++){
            if(bogListe.get(i).isUdlant()){
                udlante.add(bogListe.get(i));
            }
        }
        return udlante;
    }

    public Bog getBog(int bogId){
        for(int i=0;i<bogListe.size();i++){
            if(bogListe.get(i).getBogId()==bogId){
                return bogListe.get(i);
            }
        }
        return null;
    }

    public Laener getLaener(int lId){
        for(int i=0;i<laenerListe.size();i++){
            if(laenerListe.get(i).getlNr()==lId){
                return laenerListe.get(i);
            }
        }
        return null;
    }

    public Laener bogUdlantTil(int bogId){
        int index=getIndexBogListe(bogId);
        int lId=bogListe.get(index).getLaener();
        return getLaener(lId);
    }

}


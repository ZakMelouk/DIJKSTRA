package main.java.graphe.core;
public class Arc {
    private String source;
    private String dest;
    private int valuation;

    
    public Arc(String noeud_source,String noeud_dest,int value){ 
        this.source=noeud_source;
        this.dest=noeud_dest;
        this.valuation=value;
    }
    
    public String get_source(){
        return this.source;
    }
    
    public String get_dest(){
        return this.dest;
    }
    
    public int get_valu(){
        return this.valuation;
    }
 
    public String toString(){
        return this.source+"-"+this.get_dest()+"("+this.valuation +")";
    }
    public Boolean is_equal(Arc a){
        return this.source.equals(a.source) && this.dest.equals(a.dest);
    }
    @Override
    public boolean equals(Object arc) {
        if(arc == this) return true; 

        if(!(arc instanceof Arc)) return false; 

        Arc arc2 = (Arc) arc; 

        return this.source.compareTo(arc2.get_source()) == 0 && this.dest.compareTo(arc2.get_dest())==0;
    }
    
}
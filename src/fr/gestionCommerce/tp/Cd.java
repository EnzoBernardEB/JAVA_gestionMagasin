package fr.gestionCommerce.tp;

public class Cd extends Produit{
    private String auteur;
    private String interprete;
    private String[] titres;

    public Cd(String nom, float prixAchat, float prixVente, String auteur, String interprete) {
        super(nom, prixAchat, prixVente);
        this.auteur = auteur;
        this.interprete = interprete;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public String[] getTitres() {
        return titres;
    }

    public void setTitres(String[] titres) {
        this.titres = titres;
    }

}

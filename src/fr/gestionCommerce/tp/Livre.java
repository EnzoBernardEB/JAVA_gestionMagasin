package fr.gestionCommerce.tp;

public class Livre extends Produit{
    private String auteur;
    private String editeur;
    public Livre(String nom, float prixAchat, float prixVente, String auteur, String editeur) {
        super(nom, prixAchat, prixVente);
        this.auteur = auteur;
        this.editeur = editeur;
    }

    @Override
    public void afficherBilanProduit() {
        super.afficherBilanProduit();
        System.out.println("Auteur : "+this.auteur);
        System.out.println("Editeur : "+this.editeur);
    }
}

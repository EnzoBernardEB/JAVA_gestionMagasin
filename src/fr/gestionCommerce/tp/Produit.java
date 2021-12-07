package fr.gestionCommerce.tp;

public class Produit {
    private String nom;
    private float prixAchat;
    private float prixVente;
    private int quantite;
    private String description;

    public Produit(String nom, float prixAchat, float prixVente) {
        this.nom = nom;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.description = "Pas de description";
        this.quantite = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void afficherDescription() {
        System.out.println(this.description);
    }

    public void modifierDescription(String description) {
        this.description = description;
        System.out.println("Description modifié :");
        System.out.println(this.description);
    }

    public void modifierStock(int quantite) {
        this.quantite += quantite;
    }

    public void afficherBilanProduit() {
        System.out.println("Nom : "+this.nom);
        System.out.println("Prix d'achat : "+this.prixAchat);
        System.out.println("Prix de vente : "+ this.prixVente);
        System.out.println("Quantité : "+ this.quantite);
        System.out.println("Description : "+this.description);
    }
}

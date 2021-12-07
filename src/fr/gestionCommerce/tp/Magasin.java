package fr.gestionCommerce.tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Magasin {
    private List<Produit> stock = new ArrayList<Produit>();

    public List<Produit> getStock() {
        return stock;
    }

    public void setStock(List<Produit> stock) {
        this.stock = stock;
    }

    private void ajouterProduit(String nom, float prixAchat, float prixVente) {
        Produit nouveauProduit = new Produit(nom, prixAchat, prixVente);
        stock.add(nouveauProduit);
        System.out.println("Produit bien ajouté ! Reference : " + stock.indexOf(nouveauProduit));
    }

    private void ajouterLivre(String nom, float prixAchat, float prixVente, String auteur, String editeur) {
        Produit nouveauProduit = new Livre(nom, prixAchat, prixVente, auteur, editeur);
        stock.add(nouveauProduit);
        System.out.println("Produit bien ajouté ! Reference : " + stock.indexOf(nouveauProduit));
    }

    private void ajouterCd(String nom, float prixAchat, float prixVente, String auteur, String interprete) {
        Produit nouveauProduit = new Cd(nom, prixAchat, prixVente, auteur, interprete);
        stock.add(nouveauProduit);
        System.out.println("Produit bien ajouté ! Reference : " + stock.indexOf(nouveauProduit));
    }


    private void acheterProduit(int referenceProduit, int quantite) {
        Produit produit = stock.get(referenceProduit);
        produit.modifierStock(quantite);
        System.out.println(quantite + " " + produit.getNom() + " acheté ! Nouveau stock :" + produit.getQuantite() + " " + produit.getNom());
    }

    private void vendreProduit(int referenceProduit, int quantite) {
        Produit produit = stock.get(referenceProduit);
        if (quantite <= produit.getQuantite()) {
            produit.modifierStock((quantite * -1));
            System.out.println(quantite + " " + produit.getNom() + " vendu ! Nouveau stock :" + produit.getQuantite() + " " + produit.getNom());
        } else {
            System.out.println("Pas assez de stock de ce produit pour en vendre autant ! stock actuelle : " + produit.getQuantite());
        }
    }

    private void bilanMagasin() {
        for (Produit produit : stock) {
            produit.afficherBilanProduit();
            System.out.println("");
        }
    }

    public void interaction() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> choixPossible = new ArrayList<String>();
        choixPossible.add("Ajouter un produit");
        choixPossible.add("Acheter un produit");
        choixPossible.add("Vendre un produit");
        choixPossible.add("Modifier un produit");
        choixPossible.add("Bilan");
        choixPossible.add("Fin");
        int reponse = choixMultiple("Gestion du magasin, que voulez vous faire ?", choixPossible, scanner);

        switch (reponse) {
            case 1:
                TypeProduit produit = choixProduit(scanner);
                ajouterProduitDansStock(produit, scanner);
                interaction();
                break;
            case 2:
                achatVente(scanner,"achat");
                interaction();
                break;
            case 3:
                achatVente(scanner,"vente");
                interaction();
                break;
            case 4:
                modifierDescription(scanner);
                interaction();
                break;
            case 5:
                bilanMagasin();
                interaction();
                break;
            case 6:
                scanner.close();
                return;
        }

    }

    private ArrayList<String> afficherStock() {
        ArrayList<String> choixPossible = new ArrayList<String>();
        for (Produit produitEnStock : this.stock) {
            choixPossible.add(produitEnStock.getNom());
        }
        return choixPossible;
    }
    private void modifierDescription(Scanner scanner) {
        ArrayList<String> choixPossible = afficherStock();
        int reponse = choixMultiple("Quel produit voulez vous modifier ?",choixPossible,scanner);
        Produit produitAModifier = this.stock.get((reponse-1));
        System.out.println("Description actuelle : ");
        produitAModifier.afficherDescription();
        System.out.println("Nouvelle description :");
        scanner.nextLine();
        produitAModifier.modifierDescription(scanner.nextLine());
    }

    private void achatVente(Scanner scanner, String contexte) {
        ArrayList<String> choixPossible = afficherStock();
        if(contexte.equals("achat")) {
            int produitAAcheter = choixMultiple("Quel produit voulez acheter ?", choixPossible, scanner);
            int quantite = choixInt(scanner,"En quel quantité ?");
            this.acheterProduit((produitAAcheter - 1), quantite);
        } else {
            int produitAAcheter = choixMultiple("Quel produit voulez vendre ?", choixPossible, scanner);
            int quantite = choixInt(scanner,"En quel quantité ?");
            this.vendreProduit((produitAAcheter - 1), quantite);
        }

    }

    private int choixInt(Scanner scanner, String question) {
        int retour = -1;
        do {
            System.out.println(question);
            while (!scanner.hasNextInt()) {
                System.out.println("Ce n'est pas valide!");
                scanner.next();
            }
            retour = scanner.nextInt();
        } while (retour <0);

        return retour;
    }

    private TypeProduit choixProduit(Scanner scanner) {
        ArrayList<String> choixPossible = new ArrayList<>();
        choixPossible.add("Produit standard");
        choixPossible.add("Livre");
        choixPossible.add("Cd");
        int reponse = choixMultiple("Quel type de produit ?", choixPossible, scanner);
        TypeProduit retour = null;
        switch (reponse) {
            case 1:
                retour = TypeProduit.Standard;
                break;
            case 2:
                retour = TypeProduit.Livre;
                break;
            case 3:
                retour = TypeProduit.Cd;
                break;
        }
        return retour;
    }

    private void ajouterProduitDansStock(TypeProduit type, Scanner scanner) {
        ArrayList<String> questions = new ArrayList<String>();
        questions.add("Quel est le nom ?");
        questions.add("Quel est le prix d'achat ?");
        questions.add("Quel est le prix de vente ?");
        ArrayList<String> reponses = new ArrayList<String>();
        float prixAchat;
        float prixVente;

        switch (type) {
            case Standard:
                reponses = questionReponse(scanner, questions);
                prixAchat = verifierPrix(reponses.get(1), type, scanner);
                prixVente = verifierPrix(reponses.get(2), type, scanner);
                this.ajouterProduit(reponses.get(0), prixAchat, prixVente);
                break;
            case Livre:
                questions.add("Quel est l'auteur ?");
                questions.add("Quel est l'éditeur ?");
                reponses = questionReponse(scanner, questions);
                prixAchat = verifierPrix(reponses.get(1), type, scanner);
                prixVente = verifierPrix(reponses.get(2), type, scanner);
                this.ajouterLivre(reponses.get(0), prixAchat, prixVente, reponses.get(3), reponses.get(4));
                break;
            case Cd:
                questions.add("Quel est l'auteur ?");
                questions.add("Quel est l'interprete ?");
                reponses = questionReponse(scanner, questions);
                prixAchat = verifierPrix(reponses.get(1), type, scanner);
                prixVente = verifierPrix(reponses.get(2), type, scanner);
                this.ajouterCd(reponses.get(0), prixAchat, prixVente, reponses.get(3), reponses.get(4));
                break;
        }
    }

    private float verifierPrix(String prix, TypeProduit type, Scanner scanner) {
        float prixConverti = 0;
        try {
            prixConverti = Float.parseFloat(prix);
        } catch (Exception e) {
            System.out.println("Veullez rentrer des prix valides");
            ajouterProduitDansStock(type, scanner);
        }
        return prixConverti;
    }

    private ArrayList<String> questionReponse(Scanner scanner, ArrayList<String> questions) {
        ArrayList<String> reponses = new ArrayList<String>();
        for (String question : questions) {
            System.out.println(" - " + question);
            reponses.add(scanner.next());
        }
        return reponses;
    }


    private int choixMultiple(String question, ArrayList<String> choix, Scanner scanner) {
        System.out.println(question);
        for (int i = 0; i < choix.size(); i++) {
            System.out.println((i + 1) + ") " + choix.get(i));
        }
        int reponse = 0;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Réponse non valide");
                scanner.next();
            }
            reponse = scanner.nextInt();
            if(reponse> choix.size()) {
                System.out.println("Réponse invalide");
            }

        } while (reponse <= 0 || reponse > choix.size());
        System.out.println(reponse + " - " + choix.get(reponse - 1));
        return reponse;
    }

}

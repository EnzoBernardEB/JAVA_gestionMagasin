package fr.gestionCommerce.tp;

public enum TypeProduit {
    Standard(1),
    Livre(2),
    Cd(3);

    public int type;

    TypeProduit(int i) {
        this.type = i;
    }
}

class AlertBoxCreator {
    static void displayNotFound(Name name) {
        String title = "Erreur";
        String message = name.getNameTag() + " n'existe pas!";
        new AlertBox(title, message);
    }

    static void displayDel(Name name) {
        String title = "Client suprimer";
        String message = name.getNameTag() + " a été supprimer!";
        new AlertBox(title, message);
    }

    static void displayAdd(Name name) {
        String title = "Client ajouter";
        String message = name.getNameTag() + " a été ajouter!";
        new AlertBox(title, message);
    }

    static void displayAlreadyExists(Name name) {
        String title = "Erreur";
        String message = name.getNameTag() + " existe déja!";
        new AlertBox(title, message);
    }

    static void displayInvalidName() {
        String title = "Erreur";
        String message = "SVP entrer un nom valide (Sans espace)";
        new AlertBox(title, message);
    }

    static void displayInvalidMilles(int index) {
        new AlertBox("Erreur",
                "SVP entrer un entier positive pour la semaine " + index
        );
    }
}

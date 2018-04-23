class AlertBoxCreator {
    static void displayNotFound(String nameTag) {
        String title = "Erreur";
        String message = nameTag + " n'existe pas!";
        new AlertBox(title, message);
    }

    static void displayDel(String nameTag) {
        String title = "Client suprimer";
        String message = nameTag + " a été supprimer!";
        new AlertBox(title, message);
    }

    static void displayAdd(String nameTag) {
        String title = "Client ajouter";
        String message = nameTag + " a été ajouter!";
        new AlertBox(title, message);
    }

    static void displayAlreadyExists(String nameTag) {
        String title = "Erreur";
        String message = nameTag + " existe déja!";
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

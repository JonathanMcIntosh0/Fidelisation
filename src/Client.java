class Client {
    private final String firstName;
    private final String lastName;

    private final int[] milles;
    private final int totalMilles;
    private final int extraMilles;

    private final String clientTag;
    private final String milleTag;
    private final String nameTag;

    public Client(String firstName, String lastName, int[] milles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.milles = milles;

        this.clientTag = createClientTag();

        this.totalMilles = getTotalMilles();
        this.extraMilles = getExtraMilles();
        this.milleTag = createMilleTag();

        this.nameTag = createNameTag();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getClientTag() {
        return clientTag;
    }

    public String getMilleTag() {
        return milleTag;
    }

    public String getNameTag() {
        return nameTag;
    }

    private int getTotalMilles() {
        int total = 0;
        for (int i = 0; i < 4; i++) total += milles[i];
        return total;
    }

    private int getExtraMilles() {
        int extraMilles = 0;
        if (totalMilles > 5000) extraMilles = 1000;
        return extraMilles;
    }

    private String createClientTag() {
        String clientTag = firstName + " " + lastName + " :";
        for (int mille : milles) clientTag = clientTag.concat(" " + mille);

        return clientTag;
    }

    private String createMilleTag() {
        return firstName + " " + lastName + " a accumulé " + (totalMilles + extraMilles) + ".\n" +
                "Il a obtenue " + extraMilles + " mille(s) en prime.";
    }

    private String createNameTag() {
        return "Le client " + firstName + " " + lastName;
    }

    public static String createNameTag(String firstName, String lastName) {
        return "Le client " + firstName + " " + lastName;
    }
}

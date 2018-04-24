import org.jetbrains.annotations.NotNull;

class Client {
    private Name name;

    private final int[] milles;
    private final int totalMilles;
    private final int extraMilles;

    @NotNull
    private final String clientTag;
    @NotNull
    private final String milleTag;

    Client(@NotNull Name name, int[] milles) {
        this.name = name;

        this.milles = milles;
        this.clientTag = createClientTag();

        this.totalMilles = getTotalMilles();
        this.extraMilles = getExtraMilles();
        this.milleTag = createMilleTag();

    }

    Name getName() {
        return name;
    }

    @NotNull String getClientTag() {
        return clientTag;
    }

    @NotNull String getMilleTag() {
        return milleTag;
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

    @NotNull
    private String createClientTag() {
        String clientTag = name.firstName + " " + name.lastName + " :";
        for (int mille : milles) clientTag = clientTag.concat(" " + mille);

        return clientTag;
    }

    private String createMilleTag() {
        return name.firstName + " " + name.lastName + " a accumulé " + (totalMilles + extraMilles) + ".\n" +
                "Il a obtenue " + extraMilles + " mille(s) en prime.";
    }
}

public class Name {
    final String firstName;
    final String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String getNameTag() {
        return "Le client " + firstName + " " + lastName;
    }

    boolean equals(Name name) {
        return name.firstName.equals(firstName) && name.lastName.equals(lastName);
    }
}

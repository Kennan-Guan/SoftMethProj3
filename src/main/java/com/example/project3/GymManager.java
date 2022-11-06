package com.example.project3;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
/**
 * This is the User Interface class that processes command line inputs from user.
 * Accepts input as single command line or batch.
 * Terminates only when "Q" is typed.
 * @author Kennan Guan, Adwait Ganguly
 */
public class GymManager {
    private MemberDatabase database = new MemberDatabase();
    private ClassSchedule listOfClasses = new ClassSchedule();
    private static final int STANDARDEXPIRATION = 4;

    /**
     * Runs the Gym Manager and accepts input from command line.
     * The Gym Manager only terminates upon typing "Q".
     */
    public void run() {
        System.out.println("Gym Manager running...");
        Scanner keyboard = new Scanner(System.in);
        while (keyboard.hasNextLine()) {
            String inputLine = keyboard.nextLine();
            String[] inputs = inputLine.split(" ", 2);
            if (inputs[0].equals("Q")) {
                break;
            } else if (inputs[0].equals("A")) {
                addStandardMember(inputs[1]);
            } else if (inputs[0].equals("AF")) {
                addFamilyMember(inputs[1]);
            } else if (inputs[0].equals("AP")) {
                addPremiumMember(inputs[1]);
            } else if (inputs[0].equals("R")) {
                cancelMembership(inputs[1]);
            } else if (inputs[0].equals("P")) {
                if (database.isEmpty()) {
                    database.print();
                } else {
                    System.out.println("-list of members-");
                    database.print();
                    System.out.println("-end of list-");
                    System.out.println();
                }
            } else {
                run2(inputs);
            }
        }
        System.out.println("Gym Manager terminated.");
    }

    /**
     * Helper method for running the GymManager class and handling command line inputs.
     * @param commandInputs is the current command line separated into an array of two elements, the command letter and the subsequent information.
     */
    public void run2(String[] commandInputs) {
        if (commandInputs[0].equals("PC")) {
            database.printByCounty();
        } else if (commandInputs[0].equals("PN")) {
            database.printByName();
        } else if (commandInputs[0].equals("PD")) {
            database.printByExpirationDate();
        } else if (commandInputs[0].equals("PF")) {
            database.printWithMembershipFee();
        } else if (commandInputs[0].equals("S")) {
            listOfClasses.printClasses();
        } else if (commandInputs[0].equals("C")) {
            checkIn(commandInputs[1]);
        } else if (commandInputs[0].equals("CG")) {
            checkInGuest(commandInputs[1]);
        } else if (commandInputs[0].equals("D")) {
            dropMember(commandInputs[1]);
        } else if (commandInputs[0].equals("DG")) {
            dropGuest(commandInputs[1]);
        } else if (commandInputs[0].equals("LS")) {
            loadClasses();
        } else if (commandInputs[0].equals("LM")) {
            bulkLoad();
        } else if (commandInputs[0].equals("")) {
            System.out.println();
        } else {
            System.out.println(commandInputs[0] + " is an invalid command!");
        }
    }

    /**
     * Helper method that adds a new member into the gym database.
     * Will also check for valid date of birth and expiration date.
     *
     * @param input the customer data: first name, last name, date of birth, and gym location.
     */
    private void addStandardMember(String input) {
        String[] inputs = input.split(" ");
        Date dateOfBirth = new Date(input.split(" ")[2]);
        String city = input.split(" ")[3];
        boolean validCity = false;

        for (Location location : Location.values()) {
            if (location.name().equals(city.toUpperCase())) {
                validCity = true;
            }
        }
        if (!validCity) {
            System.out.println(city + ": invalid location!");
        } else if (!dateOfBirth.isValid()) {
            System.out.println("DOB " + dateOfBirth + ": invalid calendar date!");
        } else if (!dateOfBirth.isFuture(dateOfBirth)) {
            System.out.println("DOB " + dateOfBirth + ": cannot be today or a future date!");
        } else if (!dateOfBirth.isEighteen(dateOfBirth)) {
            System.out.println("DOB " + dateOfBirth + ": must be 18 or older to join!");
        } else {
            Member newEntry = new Member(inputs[0], inputs[1], inputs[2], inputs[3], STANDARDEXPIRATION);
            if (database.add(newEntry)) {
                System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " added.");
            } else {
                System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " is already in the database.");
            }
        }
    }

    /**
     * Helper method to add a family membership into the database.
     *
     * @param input the customer data: first name, last name, date of birth, and membership location.
     */
    private void addFamilyMember(String input) {
        Date dateOfBirth = new Date(input.split(" ")[2]);
        String city = input.split(" ")[3];

        boolean validCity = false;

        for (Location location : Location.values()) {
            if (location.name().equals(city.toUpperCase())) {
                validCity = true;
            }
        }

        if (!validCity) {
            System.out.println(city + ": invalid location!");
        } else if (!dateOfBirth.isValid()) {
            System.out.println("DOB " + dateOfBirth + ": invalid calendar date!");
        } else if (!dateOfBirth.isFuture(dateOfBirth)) {
            System.out.println("DOB " + dateOfBirth + ": cannot be today or a future date!");
        } else if (!dateOfBirth.isEighteen(dateOfBirth)) {
            System.out.println("DOB " + dateOfBirth + ": must be 18 or older to join!");
        } else {
            Member newEntry = new Family(input);
            if (database.add(newEntry)) {
                System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " added.");
            } else {
                System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " is already in the database.");
            }
        }
    }

    /**
     * Helper method to add a premium membership into the database.
     *
     * @param input the customer data: first name, last name, date of birth, and membership location.
     */
    private void addPremiumMember(String input) {
        Date dateOfBirth = new Date(input.split(" ")[2]);
        String city = input.split(" ")[3];

        boolean validCity = false;

        for (Location location : Location.values()) {
            if (location.name().equals(city.toUpperCase())) {
                validCity = true;
            }
        }

        if (!validCity) {
            System.out.println(city + ": invalid location!");
        } else if (!dateOfBirth.isValid()) {
            System.out.println("DOB " + dateOfBirth + ": invalid calendar date!");
        } else if (!dateOfBirth.isFuture(dateOfBirth)) {
            System.out.println("DOB " + dateOfBirth + ": cannot be today or a future date!");
        } else if (!dateOfBirth.isEighteen(dateOfBirth)) {
            System.out.println("DOB " + dateOfBirth + ": must be 18 or older to join!");
        } else {
            Member newEntry = new Premium(input);
            if (database.add(newEntry)) {
                System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " added.");
            } else {
                System.out.println(newEntry.getFname() + " " + newEntry.getLname() + " is already in the database.");
            }
        }
    }

    /**
     * Helper method that cancels and removes a member from the member database.
     *
     * @param input the command line inputs: first name, last name, date of birth, membership expiration date, location.
     */
    private void cancelMembership(String input) {
        String[] inputs = input.split(" ");
        Member entry = new Member(inputs[0], inputs[1], inputs[2]);
        if (database.remove(entry)) {
            System.out.println(inputs[0] + " " + inputs[1] + " removed.");
            System.out.println();
        } else {
            System.out.println(inputs[0] + " " + inputs[1] + " is not in the database.");
        }
    }

    /**
     * Helper method that adds a new member into a specified fitness class.
     *
     * @param input the command line inputs: fitness class name, instructor, location, first name, last name, date of birth.
     */
    private void checkIn(String input) {
        String[] split = input.split(" ");
        Member entry = new Member(split[3], split[4], split[5]);
        Member storedEntry = database.isMemberInArray(entry);
        Date today = new Date();
        Date DOB = new Date(split[5]);

        if (validClass(split[0]) && validGym(split[2]) && validInstructor(split[1])) {
            if (!DOB.isValid()) {
                System.out.println("DOB " + DOB + ": invalid calendar date!");
            } else if (storedEntry == null) {
                System.out.println(split[3] + " " + split[4] + " " + split[5] + " is not in the database.");
            } else if (today.compareTo(storedEntry.getExpire()) >= 0) {
                System.out.println(split[3] + " " + split[4] + " " + split[5] + " membership expired.");
            } else {
                FitnessClass checkInClass = findClass(listOfClasses.getClasses(), split[2], split[1], split[0]);
                if (checkInClass == null) {
                    System.out.println(split[0] + " by " + split[1] + " does not exist at " + split[2]);
                } else if (findConflict(checkInClass.getTime(), storedEntry, split[1])) {
                    System.out.println("Time conflict - " + checkInClass + ", " + checkInClass.getLocation().getStringZip() + ", " + checkInClass.getLocation().getCounty());
                } else if (Location.valueOf(split[2].toUpperCase()) != storedEntry.getLocation() && !(storedEntry instanceof Family)) {
                    System.out.println(storedEntry.getFname() + " " + storedEntry.getLname() + " checking in " + checkInClass.getLocation() + " - standard membership location restriction.");
                } else if (checkInClass.addMember(storedEntry)) {
                    System.out.println(storedEntry.getFname() + " " + storedEntry.getLname() + " checked in " + checkInClass);
                    System.out.println("- Participants -");
                    for (int i = 0; i < checkInClass.getAttendance().size(); i++) {
                        System.out.print("   ");
                        System.out.println(checkInClass.getAttendance().get(i));
                    }
                    System.out.println();
                } else {
                    System.out.println(storedEntry.getFname() + " " + storedEntry.getLname() + " already checked in.");
                }
            }
        }
    }

    /**
     * Helper method to check in a guest for a fitness class.
     * Will keep track of guest passes for the member.
     *
     * @param input is the information that follows the CG command from the user input, describing the location, class name, instructor, and the member whose guest is checking in.
     */
    private void checkInGuest(String input) {
        String[] split = input.split(" ");
        Member entry = new Member(split[3], split[4], split[5]);
        Member storedEntry = database.isMemberInArray(entry);
        Date today = new Date();
        Date DOB = new Date(split[5]);
        FitnessClass checkInClass = findClass(listOfClasses.getClasses(), split[2], split[1], split[0]);

        if (validClass(split[0]) && validGym(split[2]) && validInstructor(split[1])) {
            if (!DOB.isValid()) {
                System.out.println("DOB " + DOB + ": invalid calendar date!");
            } else if (storedEntry == null) {
                System.out.println(split[3] + " " + split[4] + " " + split[5] + " is not in the database.");
            } else if (today.compareTo(storedEntry.getExpire()) >= 0) {
                System.out.println(split[3] + " " + split[4] + " " + split[5] + " membership expired.");
            } else if (!(storedEntry instanceof Family)) {
                System.out.println("Standard membership - guest check-in is not allowed.");
            } else if (storedEntry.getLocation() != Location.valueOf(split[2].toUpperCase())) {
                System.out.println(split[3] + " " + split[4] + " Guest checking in " + Location.valueOf(split[2].toUpperCase()) + " - guest location restriction.");
            } else if (!(storedEntry instanceof Premium)) {
                checkInFamily(storedEntry, checkInClass);
            } else {
                checkInPremium(storedEntry, checkInClass);
            }
        }
    }

    /**
     * Helper method to check in a guest with a family guest pass.
     * Will also print out the participants and guests in the specified class
     *
     * @param member       the owner of the membership
     * @param checkInClass the desired class to check into
     */
    private void checkInFamily(Member member, FitnessClass checkInClass) {
        if (((Family) member).getGuestPasses() <= 0) {
            System.out.println(member.getFname() + " " + member.getLname() + " ran out of guest pass.");
        } else {
            ((Family) member).useGuestPass();
            checkInClass.addGuest(member);
            System.out.println(member.getFname() + " " + member.getLname() + " (guest) checked in " + checkInClass);
            if (checkInClass.getAttendance().size() != 0) {
                System.out.println("- Participants -");
                for (int i = 0; i < checkInClass.getAttendance().size(); i++) {
                    System.out.print("   ");
                    System.out.println(checkInClass.getAttendance().get(i));
                }
            }
            System.out.println("- Guests -");
            for (int i = 0; i < checkInClass.getGuests().size(); i++) {
                System.out.print("   ");
                System.out.println(checkInClass.getGuests().get(i));
            }
            System.out.println();
        }
    }

    /**
     * Helper method to check in a guest with a premium guest pass.
     * This method will also print out the participants and guests in the specified class.
     *
     * @param member       the owner of the membership
     * @param checkInClass the desired class to check into
     */
    private void checkInPremium(Member member, FitnessClass checkInClass) {
        if (((Premium) member).getGuestPasses() <= 0) {
            System.out.println(member.getFname() + " " + member.getLname() + " ran out of guest pass.");
        } else {
            ((Premium) member).useGuestPass();
            checkInClass.addGuest(member);
            System.out.println(member.getFname() + " " + member.getLname() + " (guest) checked in " + checkInClass);
            if (checkInClass.getAttendance().size() != 0) {
                System.out.println("- Participants -");
                for (int i = 0; i < checkInClass.getAttendance().size(); i++) {
                    System.out.print("   ");
                    System.out.println(checkInClass.getAttendance().get(i));
                }
            }
            System.out.println("- Guests -");
            for (int i = 0; i < checkInClass.getGuests().size(); i++) {
                System.out.print("   ");
                System.out.println(checkInClass.getGuests().get(i));
            }
            System.out.println();
        }
    }

    /**
     * Helper method to find if the given member has a class at a given time.
     *
     * @param time       the time object of the fitness class
     * @param member     the member object of the member
     * @param instructor the instructor of the class
     * @return true if a conflict exists, false otherwise
     */
    private boolean findConflict(Time time, Member member, String instructor) {
        for (int i = 0; i < listOfClasses.getSize(); i++) {
            FitnessClass fitness = listOfClasses.getClasses()[i];
            if (fitness.getTime() == time && fitness.getAttendance().contains(member) && !fitness.getInstructor().equalsIgnoreCase(instructor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to find the correct fitness class based on location, instructor name, and class name.
     * Will return null if a class is not found or the inputted information is invalid and print an error statement.
     *
     * @param fitnessClasses an array of the fitness classes
     * @param location       the location of the fitness class
     * @param instructor     the name of the instructor of the fitness class
     * @param session        the class name
     * @return the desired fitness class, otherwise null
     */
    private FitnessClass findClass(FitnessClass[] fitnessClasses, String location, String instructor, String session) {
        for (int i = 0; i < fitnessClasses.length - 1; i++) {
            String fitnessName = fitnessClasses[i].getName();
            String fitnessInstructor = fitnessClasses[i].getInstructor();
            Location fitnessLocation = fitnessClasses[i].getLocation();
            if (fitnessName.equalsIgnoreCase(session) && fitnessLocation == Location.valueOf(location.toUpperCase()) && fitnessInstructor.equalsIgnoreCase(instructor)) {
                return fitnessClasses[i];
            }
        }
        return null;
    }

    /**
     * Helper method to determine if the given gym location is valid.
     * Will print an error statement if location is invalid.
     *
     * @param gymLocation the gym location.
     * @return true if the gym is valid, false otherwise.
     */
    private boolean validGym(String gymLocation) {
        for (Location gym : Location.values()) {
            if (gym.name().equalsIgnoreCase(gymLocation)) {
                return true;
            }
        }
        System.out.println(gymLocation + " - invalid location.");
        return false;
    }

    /**
     * Helper method to determine if the given instructor name is valid.
     *
     * @param name the name of the instructor.
     * @return true if the instructor is valid, false otherwise.
     */
    private boolean validInstructor(String name) {
        for (int i = 0; i < listOfClasses.getSize(); i++) {
            if (name.equalsIgnoreCase(listOfClasses.getClasses()[i].getInstructor())) {
                return true;
            }
        }
        System.out.println(name + " - instructor does not exist.");
        return false;
    }

    /**
     * Helper method to determine if the given class name is valid.
     *
     * @param name the name of the class
     * @return true if the class is valid, false otherwise.
     */
    private boolean validClass(String name) {
        for (int i = 0; i < listOfClasses.getSize(); i++) {
            if (name.equalsIgnoreCase(listOfClasses.getClasses()[i].getName())) {
                return true;
            }
        }
        System.out.println(name + " - class does not exist.");
        return false;
    }


    /**
     * Helper method that drops a given member from a specified fitness class.
     *
     * @param input the command line inputs: fitness class name, instructor, location, first name, last name, date of birth.
     */
    private void dropMember(String input) {
        String[] dropMemberInput = input.split(" ");
        Member storedEntry = database.isMemberInArray(new Member(dropMemberInput[3], dropMemberInput[4], dropMemberInput[5]));
        Date DOB = new Date(dropMemberInput[5]);

        if (validClass(dropMemberInput[0]) && validGym(dropMemberInput[2]) && validInstructor(dropMemberInput[1])) {
            if (!DOB.isValid()) {
                System.out.println("DOB " + DOB + ": invalid calendar date!");
            } else if (storedEntry == null) {
                System.out.println(dropMemberInput[3] + " " + dropMemberInput[4] + " " + dropMemberInput[5] + " is not in the database.");
            } else {
                FitnessClass checkInClass = findClass(listOfClasses.getClasses(), dropMemberInput[2], dropMemberInput[1], dropMemberInput[0]);
                if (checkInClass == null) {
                    System.out.println(dropMemberInput[0] + " by " + dropMemberInput[1] + " does not exist at " + dropMemberInput[2]);
                } else if (checkInClass.removeMember(storedEntry)) {
                    System.out.println(storedEntry.getFname() + " " + storedEntry.getLname() + " done with the class.");
                } else {
                    System.out.println(storedEntry.getFname() + " " + storedEntry.getLname() + " did not check in.");
                }
            }
        }
    }

    /**
     * Helper method to check out a guest from a fitness class.
     * Will keep track of remaining guest passes.
     *
     * @param input is a string following the DG command that contains information such as the class name, location, instructor, and the member whose guest is checking out.
     */
    private void dropGuest(String input) {
        String[] dropMemberInput = input.split(" ");
        Member storedEntry = database.isMemberInArray(new Member(dropMemberInput[3], dropMemberInput[4], dropMemberInput[5]));
        Date DOB = new Date(dropMemberInput[5]);

        if (!DOB.isValid()) {
            System.out.println(DOB + ": invalid calendar date!");
        } else if (storedEntry == null) {
            System.out.println(dropMemberInput[3] + " " + dropMemberInput[4] + " " + dropMemberInput[5] + " is not in the database.");
        } else {
            FitnessClass checkInClass = findClass(listOfClasses.getClasses(), dropMemberInput[2], dropMemberInput[1], dropMemberInput[0]);
            if (checkInClass != null) {
                checkInClass.removeGuest(storedEntry);
            }
            System.out.println(storedEntry.getFname() + " " + storedEntry.getLname() + " Guest done with the class");
            if (storedEntry instanceof Family && !(storedEntry instanceof Premium)) {
                ((Family) storedEntry).returnGuestPasses();
            } else if (storedEntry instanceof Premium) {
                ((Premium) storedEntry).returnGuestPasses();
            }
        }
    }

    /**
     * This loads the fitness classes from a text file.
     */
    private void loadClasses() {
        try {
            Scanner sc = new Scanner(new File("src/classSchedule.txt"));

            while (sc.hasNextLine()) {
                String[] inputs = sc.nextLine().split("\\s+");
                String classType = inputs[0].toUpperCase();
                String instructor = inputs[1].toUpperCase();
                Time time = Time.valueOf(inputs[2].toUpperCase());
                Location gymLocation = Location.valueOf(inputs[3].toUpperCase());

                listOfClasses.addClass(new FitnessClass(time, instructor, classType, gymLocation));
            }
            System.out.println("-Fitness classes loaded-");
            for (int i = 0; i < listOfClasses.getSize(); i++) {
                System.out.println(listOfClasses.getClasses()[i].toString());
            }
            System.out.println("-end of class list.");
        } catch (FileNotFoundException e) {
            System.out.println("classSchedule.txt file not found.");
        }
    }

    /**
     * This method loads the member list from a text file.
     */
    private void bulkLoad() {
        try {
            Scanner readMem = new Scanner(new File("src/memberList.txt"));
            while (readMem.hasNextLine()) {
                Member newMem = new Member(readMem.nextLine());
                database.add(newMem);
            }
            System.out.println("-list of members loaded-");
            database.print();
            System.out.println("-end of list-");
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("memberList.txt file not found");
        }
    }
}


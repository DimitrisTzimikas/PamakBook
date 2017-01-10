
public class ClosedGroup extends Group {

    public ClosedGroup(String name, String description) {
        super(name, description);
    }

    public void printInfo(GUIConsole console) {
        super.printInfo(console);
    }

    /**
     * Επικαλύπτει την συνάρτηση της κλάσης Group και την προσαρμώζει έτσι ώστε να μπορεί να μπει κάποιος
     * σε μια κλειστή ομάδα μόνο εάν η ομάδα αρχικά δεν έχει κανένα μέλος και μετέπειτα εάν έχει
     * κάποιον κοινό φίλο με το μέλος της κρυφής ομάδας.
     */
    public void addToGroup(User aUser, GUIConsole console) {

        boolean isHeInGroupFlag = false;
        User tempUser1;

        //Ελέγχει αν ο χρήστης που πάει να κάνει εγγραφή στο γρούπ υπάρχει ήδη
        for (int i = 0; i < this.arrayGroup.size(); i++) {
            tempUser1 = this.arrayGroup.get(i);

            if (tempUser1.getName().equals(aUser.getName())) {
                console.setTextArea(aUser.getName() + " is already enrolled in the Exam Solutions group.");
                isHeInGroupFlag = true;
            }
        }

        //Εαν το γρούπ είναι άδειο ο χρήστης μπαίνει στο γρούπ όπως και να έχει
        if (this.arrayGroup.size() == 0) {
            this.arrayGroup.add(aUser);
            console.setTextArea(aUser.getName() + " has successfully enrolled in group " + this.name);
        }
        else {
            //Ελέγχει εαν ο χρήστης που πάει να γραφτεί στο γρούπ υπάρχει ήδη
            if (isHeInGroupFlag)
                return;

            User tempUser2;
            boolean mutualFriendFlag = false;
            int sizeOfArray = this.arrayGroup.size();

            //Ελέγχει αν ο χρήστης που πάει να κάνει εγγραφή στο γρούπ έχει κοινό φίλο με κάποιον
            //που είναι ήδη εγγεγραμένος
            for (int i = 0; i < sizeOfArray; i++) {
                tempUser2 = arrayGroup.get(i);
                if (mutualFriendFlag)
                    break;
                if (tempUser2.isHeInMyFriendList(aUser)) {
                    this.arrayGroup.add(aUser);
                    console.setTextArea(aUser.getName() + " has successfully enrolled in group " + this.name);
                    mutualFriendFlag = true;
                }
            }

            if (!mutualFriendFlag)
                console.setTextArea(aUser.getName() + " don't have a mutual friend, so she can't join the Exam Solution group.");
        }
    }
}
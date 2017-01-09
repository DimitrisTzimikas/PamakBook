
public class ClosedGroup extends Group {

    public ClosedGroup(String name, String description) {
        super(name, description);
    }

    public void printInfo(Console console) {
        String text = "";
        text += ("Members of group " + this.name + "\n");
        int count = 0;

        for(User u : arrayGroup) {
            count ++;
            text += count + ": " + u.getName() + "\n";
        }
        console.setTextArea(text);
    }

    /**
     * Επικαλύπτει την συνάρτηση της κλάσης Group και την προσαρμώζει έτσι ώστε να μπορεί να μπει κάποιος
     * σε μια κλειστή ομάδα μόνο εάν η ομάδα αρχικά δεν έχει κανένα μέλος και μετέπειτα εάν έχει
     * κάποιον κοινό φίλο με το μέλος της κρυφής ομάδας.
     */
    public void addToGroup(User aUser, Console console) {

        boolean flag1 = false;
        User temp1;

        //Ελέγχει αν ο χρήστης που πάει να κάνει εγγραφή στο γρούπ υπάρχει ήδη
        for (int i = 0; i < this.arrayGroup.size(); i++) {
            temp1 = this.arrayGroup.get(i);

            if (temp1.getName().equals(aUser.getName())) {
                console.setTextArea(aUser.getName() + " is already enrolled in the Exam Solutions group.");
                flag1 = true;
            }
        }

        //Εαν το γρούπ είναι άδειο ο χρήστης μπαίνει στο γρούπ όπως και να έχει
        if (this.arrayGroup.size() == 0) {
            this.arrayGroup.add(aUser);
            console.setTextArea(aUser.getName() + " has successfully enrolled in group " + this.name);
        }
        else {
            //Ελέγχει εαν ο χρήστης που πάει να γραφτεί στο γρούπ υπάρχει ήδη
            if (flag1) return;

            User temp2;
            boolean flag2 = false;
            int sizeOfArray = this.arrayGroup.size();

            //Ελέγχει αν ο χρήστης που πάει να κάνει εγγραφή στο γρούπ έχει κοινό φίλο με κάποιον
            //που είναι ήδη εγγεγραμένος
            for (int i = 0; i < sizeOfArray; i++) {
                temp2 = arrayGroup.get(i);
                if (flag2)
                    break;
                if (temp2.isHeInMyFriendList(aUser)) {
                    this.arrayGroup.add(aUser);
                    console.setTextArea(aUser.getName() + " has successfully enrolled in group " + this.name);
                    flag2 = true;
                }
            }

            if (!flag2)
                console.setTextArea(aUser.getName() + " don't have a mutual friend, so she can't join the Exam Solution group.");
        }
    }
}
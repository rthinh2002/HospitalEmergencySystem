public class WaitingList {
    protected Node head;
    protected int numNodes;
    protected int length;

    public WaitingList(){
        this.head = null;
        this.numNodes = 0;
    }

    /**
     * This method will pop the first patient from the waiting list
     * <!-- @return --> the patient popped from the waiting list
     */
    public Patient popPatient() {
        if (this.head == null) {
            return null;
        } else {
            Node save = this.head;
            Node next = this.head.getNext();
            this.head =next;
            this.length--;
            return save.getPatient();
        }
    }

    public void popPatientById(int id){
        boolean check = false;
        if(this.head == null){
            System.out.println("There is no patient in the waiting list!");
        } else if(this.head.getPatient().getId() == id){
            this.popPatient();
            System.out.println("\nSuccess! An ambulance as assigned for patient "+ id);
        } else {
            Node tmp = this.head;
            while(tmp.getNext() != null){
                if(tmp.getNext().getPatient().getId() == id){
                    tmp.setNext(tmp.getNext().getNext());
                    check = true;
                } else{
                    tmp = tmp.getNext();
                }
            }
            //printing
            if(check){
                System.out.println("\nSuccess! An ambulance as assigned for patient "+ id);
            } else{
                System.out.println("Patient not found!");
            }
        }
    }

    /**
     * This method will add patient into the waiting list according to the triage level
     * @param patient patient's data
     */
    public void addToList(Patient patient) {
        Node node = new Node(patient);
        if(this.head == null){ //if the waiting list is empty, add normally
            this.head = node;
            this.length++;
        } 
        //if the patient level is larger than the top patient 
        else if(node.getPatient().getTriageLevel() > this.head.getPatient().getTriageLevel()){
            Node temp = this.head;
            this.head = node;
            this.head.setNext(temp);
            this.length++;
        } else{
            Node previous = this.head;
            Node current = this.head.getNext();
            //traverse through all the patient to check
            while(current != null){
                if(node.getPatient().getTriageLevel() > current.getPatient().getTriageLevel()){
                    previous.setNext(node);
                    node.setNext(current);
                    return;
                } else if(current.getNext() == null){
                    current.setNext(node);
                    return;
                } else{
                    previous = previous.getNext();
                    current = current.getNext();
                }
            } //end while loop
            this.length++;
        }
    }

    //return length
    public int size(){        
        Node tmp = this.head.getNext();
        int count = 1;
        while(tmp != null){
            count++;
            tmp = tmp.getNext();
        }
        return count;       
    }

    /**
     * print out the information for each patient in waiting list
     */
    public void printList() {
        System.out.print("ID      Name       Triage Level\n");
        System.out.println("------------------------------------------");
        if (this.head == null) {
            System.out.println("The waiting list is empty!");
        } else {
            Node tmp = this.head;

            while(tmp != null) {
                System.out.print(tmp.getPatient().getId()+ "\t"+ tmp.getPatient().getName() + "\t\t"+ tmp.getPatient().getTriageLevel());
                System.out.println();
                tmp = tmp.getNext();
            }
        }
    }

    /**
     * Check whether the patient is in this list or not
     * @return
     */
    public boolean isInList(Patient patient) {
        if (this.head == null) {
            return false;
        }
        Node temp = this.head;
        while(temp != null) {
            if(temp.getPatient().getName().equals(patient.getName())
                    && temp.getPatient().getPhoneNumber().equals(patient.getPhoneNumber())){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public void getPosition(int id){
        if(this.head == null){
            System.out.println("\nWaiting list is empty! ");
            return;
        } else if(this.head.getPatient().getId() == id){
            System.out.println("\nThe patient " + id +" is first in the list! ");
            return;                
        } else if(this.head.getPatient().getId() != id){
            Node tmp = this.head.getNext();
            int count = 1;
            boolean check = false;

            if(tmp.getPatient().getId() == id){ //if the next elements is equal to id number
                check = true;
            }

            while(tmp.getPatient().getId() != id && tmp.getNext() != null){ //traverse through the list
                count++; //adding count

                if(tmp.getNext().getPatient().getId() == id){
                    check =true;
                }

                tmp = tmp.getNext(); //if not, set the tmp to the next element
            }

            //printing
            if(check){
                System.out.println("\nThere are "+count+ " patients before patient "+ id);
            } else{
                System.out.println("\nPatient not found!");
            }
        }
    }

    public boolean isEmpty(){
        if(this.head == null){
            return true;
        } else{
            return false;
        }
    }
}

public class ServiceCenter {
    private WaitingList awl;

    public ServiceCenter() {
        this.awl = new WaitingList();
    }

    /**
     * Record patient's data and add the patient into waiting list
     * @param name
     * @param phoneNumber
     * @param triageLevel
     * @param location
     */
    public void addPatientIntoList(String name, String phoneNumber, int triageLevel, String location) {
        Patient patient = new Patient(name, phoneNumber, triageLevel, location);
        if(this.awl.isInList(patient)){
            System.out.println(patient.getName() + " is in waiting list. ");
        }else{
            this.awl.addToList(patient);
            System.out.println("Add " + patient.getName() + " into waiting list. ");
        }
    }

    /**
     * Pop out the first patient in the waiting list and assign an Ambulance for him/her
     * @return the patient object
     */
    public Patient assignAmbulanceForPatient() {
        // TODO: The tester report that the system will crash when waiting list is empty
        if(this.awl.isEmpty()){
            System.out.println("Error! The waiting list is empty.");
            return null;
        } else{
            Patient patient = this.awl.popPatient();
            System.out.println(" Success! An ambulance as assigned for patient " + patient.getId());
            return patient;
        }
    }

    /**
     * Print out the waiting list
     */
    public void printWaitingList(){
        this.awl.printList();
    }

    //check to position by id method
    public void checkPositionById(int id){
        this.awl.getPosition(id);
    }

    public void assignAmbulanceById(int id){
        if(this.awl.isEmpty()){
            System.out.println("Error! The waiting list is empty!");
        } else{
            this.awl.popPatientById(id);
        }    
    }
}

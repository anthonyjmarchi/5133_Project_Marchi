public class OtherImport {

    public OtherImport() {
    }

    /**

    while ((line = bufferreader.readLine()) != null) {
        String[] treatment = line.split(",");
        if (treatment.length == 5) {
            if (!(treatment[0].contains("1") || treatment[0].contains("2") || treatment[0].contains("3") ||
                    treatment[0].contains("4") || treatment[0].contains("5") || treatment[0].contains("6") ||
                    treatment[0].contains("7") || treatment[0].contains("8") || treatment[0].contains("9") ||
                    treatment[0].contains("0"))) {
                lastName = treatment[0].toLowerCase();
            }
            if (!(treatment[1].contains("1") || treatment[1].contains("2") || treatment[1].contains("3") ||
                    treatment[1].contains("4") || treatment[1].contains("5") || treatment[1].contains("6") ||
                    treatment[1].contains("7") || treatment[1].contains("8") || treatment[1].contains("9") ||
                    treatment[1].contains("0"))) {
                doctorLastName = treatment[1].toLowerCase();
            }
            treatmentType = treatment[2].toLowerCase();
            if (!(treatment[3].contains("1") || treatment[3].contains("2") || treatment[3].contains("3") ||
                    treatment[3].contains("4") || treatment[3].contains("5") || treatment[3].contains("6") ||
                    treatment[3].contains("7") || treatment[3].contains("8") || treatment[3].contains("9") ||
                    treatment[3].contains("0"))) {
                patientTreatment = treatment[3].toLowerCase();
            }
            boolean valueTen = true;
            for (char letter : alphabet) {
                if (Character.toString(letter).equals(treatment[4])) {
                    valueTen = false;
                }
            }
            if (valueTen) {
                timestamp = treatment[4];
            }
        }
        Treatment newTreatment = new Treatment(lastName, doctorLastName, treatmentType, patientTreatment, timestamp);
        treatmentList.add(newTreatment);
    }

     **/
}

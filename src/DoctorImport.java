public class DoctorImport {

    public DoctorImport() {
    }

    /**

      if (singlePerson.length == 14) {
        if (singlePerson[0].equals("V") || singlePerson[0].equals("A")
                || singlePerson[0].equals("N") || singlePerson[0].equals("T")) {
            type = singlePerson[0].toLowerCase();
        }
        if (singlePerson[0].equals("D")) {
            type = singlePerson[0].toLowerCase();
            if (!(singlePerson[1].contains("1") || singlePerson[1].contains("2") || singlePerson[1].contains("3") ||
                    singlePerson[1].contains("4") || singlePerson[1].contains("5") || singlePerson[1].contains("6") ||
                    singlePerson[1].contains("7") || singlePerson[1].contains("8") || singlePerson[1].contains("9") ||
                    singlePerson[1].contains("0"))) {
                firstName = singlePerson[1].toLowerCase();
            }
            if (!(singlePerson[2].contains("1") || singlePerson[2].contains("2") || singlePerson[2].contains("3") ||
                    singlePerson[2].contains("4") || singlePerson[2].contains("5") || singlePerson[2].contains("6") ||
                    singlePerson[2].contains("7") || singlePerson[2].contains("8") || singlePerson[2].contains("9") ||
                    singlePerson[2].contains("0"))) {
                lastName = singlePerson[2].toLowerCase();
            }
            privileges = singlePerson[3].toLowerCase();
            Doctor newDoc = new Doctor(type, firstName, lastName, privileges);
            doctorList.add(newDoc);
        }

        while ((line = bufferreader.readLine()) != null) {
            String[] additionalDoctor = line.split(",");
            if (additionalDoctor.length == 2) {
                if (!(additionalDoctor[0].contains("1") || additionalDoctor[0].contains("2") || additionalDoctor[0].contains("3") ||
                        additionalDoctor[0].contains("4") || additionalDoctor[0].contains("5") || additionalDoctor[0].contains("6") ||
                        additionalDoctor[0].contains("7") || additionalDoctor[0].contains("8") || additionalDoctor[0].contains("9") ||
                        additionalDoctor[0].contains("0"))) {
                    lastName = additionalDoctor[0].toLowerCase();
                }
                if (!(additionalDoctor[1].contains("1") || additionalDoctor[1].contains("2") || additionalDoctor[1].contains("3") ||
                        additionalDoctor[1].contains("4") || additionalDoctor[1].contains("5") || additionalDoctor[1].contains("6") ||
                        additionalDoctor[1].contains("7") || additionalDoctor[1].contains("8") || additionalDoctor[1].contains("9") ||
                        additionalDoctor[1].contains("0"))) {
                    firstName = additionalDoctor[1].toLowerCase();
                }
            }
            type = "Additional Doctor";
            privileges = "C";
            Doctor newAdditionalDoctor = new Doctor(type, firstName, lastName, privileges);
            doctorList.add(newAdditionalDoctor);
            boolean check = true;
            for (Doctor z : doctorList) {
                if (z.getLastName().equals(newAdditionalDoctor.getLastName())) {
                    check = true;
                }
            }
            if (check) {
                doctorList.add(newAdditionalDoctor);
            }
        }
        for (Doctor d : doctorList) {
            personList.add(d);
        }

     **/
}
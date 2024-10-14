package za.co.droppa.dto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum CompanyName {
    DSV, COURIER_GUY, DPD_LASER, DROPPA, SKYNET, UBER, BRIMA, FIRST_FREIGHT, PUDO;

    public static Set<CompanyName> valuesSet() {
        return new HashSet<>(Arrays.asList(CompanyName.values()));
    }

    public static boolean canNotifyWaybill(String companyName){
        return companyName.equals(DSV.name()) || companyName.equals(BRIMA.name()) ||
                companyName.equals(FIRST_FREIGHT.name()) || companyName.equals(DPD_LASER.name());
    }

    public static CompanyName findByName(String name){
        return Arrays.stream(CompanyName.values()).filter(company-> company.name().equals(name)).findFirst().orElse(null);
    }


}

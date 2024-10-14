package za.co.droppa.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkynetInsurance
{
    private String insuranceType;
    public Double InsuranceAmount;
    private double droppaCommission;
    private double sendToSkynet;

    public double clientPaid;

    public SkynetInsurance(double declaredAmount, double chargedPercentage, double companyPercentage) {
        this.insuranceType = "3";
        this.InsuranceAmount = declaredAmount;
        this.clientPaid = declaredAmount * (chargedPercentage / 100);
        this.sendToSkynet = declaredAmount * (companyPercentage / 100);
        this.droppaCommission = declaredAmount * ((chargedPercentage - companyPercentage) / 100);
    }

}

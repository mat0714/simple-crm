package pl.mateuszkolodziejczyk.simplecrm.company.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyRequest {

    private String name;
    private String streetNumber;
    private String streetName;
    private String city;
    private String voivodeship;
    private String zipCode;
}
